package softuni.heroes.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.heroes.model.binding.HeroCreateBindingModel;
import softuni.heroes.model.service.HeroServiceModel;
import softuni.heroes.service.HeroService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/heroes")
public class HeroController {

    private final HeroService heroService;
    private final ModelMapper mapper;

    @Autowired
    public HeroController(HeroService heroService, ModelMapper mapper) {
        this.heroService = heroService;
        this.mapper = mapper;
    }

    @GetMapping("/create")
    public String create(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/users/login";
        } else {
            if (!model.containsAttribute("heroCreateBindingModel")) {
                model.addAttribute("heroCreateBindingModel", new HeroCreateBindingModel());
            }
            return "create-hero";
        }
    }

    @PostMapping("/create")
    public String createConfirm(@Valid @ModelAttribute("heroCreateBindingModel")
                                        HeroCreateBindingModel heroCreateBindingModel,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("heroCreateBindingModel", heroCreateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.heroCreateBindingModel", bindingResult);
            return "redirect:create";
        }

        this.heroService.createHero(this.mapper.map(heroCreateBindingModel, HeroServiceModel.class));
        return "redirect:/";
    }

    @GetMapping("/details")
    public ModelAndView details(@RequestParam("id") String id,
                                ModelAndView modelAndView, HttpSession httpSession) {

        if (httpSession.getAttribute("user") == null) {
            modelAndView.setViewName("redirect:/users/login");
        } else {
            modelAndView.addObject("hero", this.heroService.findHeroById(id));
            modelAndView.setViewName("details-hero");
        }
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/users/login";
        } else {
            this.heroService.deleteHero(id);
            return "redirect:/";
        }
    }
}
