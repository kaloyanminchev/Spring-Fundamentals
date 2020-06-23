package softuni.andreysexam.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.andreysexam.model.binding.ItemAddBindingModel;
import softuni.andreysexam.model.entity.Gender;
import softuni.andreysexam.model.service.ItemServiceModel;
import softuni.andreysexam.service.ItemService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final ModelMapper mapper;

    @Autowired
    public ItemController(ItemService itemService, ModelMapper mapper) {
        this.itemService = itemService;
        this.mapper = mapper;
    }

    @GetMapping("/add")
    public String add(Model model, HttpSession httpSession) {

        if (!model.containsAttribute("itemAddBindingModel")) {
            model.addAttribute("itemAddBindingModel", new ItemAddBindingModel());
            model.addAttribute("gender", Gender.values());
        }
        return httpSession.getAttribute("user") == null ? "redirect:/users/login" : "add-item";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("itemAddBindingModel")
                                     ItemAddBindingModel itemAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("itemAddBindingModel", itemAddBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.itemAddBindingModel", bindingResult);
            return "redirect:add";
        }

        ItemServiceModel itemServiceModel = this.mapper.map(itemAddBindingModel, ItemServiceModel.class);
        this.itemService.addItem(itemServiceModel);
        return "redirect:/";
    }

    @GetMapping("/details")
    public ModelAndView details(@RequestParam("id") String id,
                                ModelAndView modelAndView, HttpSession httpSession) {

        if (httpSession.getAttribute("user") == null) {
            modelAndView.setViewName("redirect:/users/login");
        } else {
            modelAndView.addObject("item", this.itemService.getById(id));
            modelAndView.setViewName("details-item");
        }
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id, HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            return "redirect:/users/login";
        } else {
            this.itemService.delete(id);
            return "redirect:/";
        }
    }
}
