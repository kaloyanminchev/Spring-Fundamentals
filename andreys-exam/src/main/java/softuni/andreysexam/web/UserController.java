package softuni.andreysexam.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.andreysexam.model.binding.UserAddBindingModel;
import softuni.andreysexam.model.binding.UserLoginBindingModel;
import softuni.andreysexam.model.service.UserServiceModel;
import softuni.andreysexam.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper mapper;

    @Autowired
    public UserController(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userAddBindingModel")) {
            model.addAttribute("userAddBindingModel", new UserAddBindingModel());
            model.addAttribute("passwordMismatch", false);
            model.addAttribute("userExists", false);
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid @ModelAttribute("userAddBindingModel")
                                          UserAddBindingModel userAddBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userAddBindingModel", userAddBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userAddBindingModel", bindingResult);
            return "redirect:register";
        }
        UserServiceModel userServiceModel =
                this.mapper.map(userAddBindingModel, UserServiceModel.class);

        if (!userAddBindingModel.getPassword().equals(userAddBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("passwordMismatch", true);
            redirectAttributes.addFlashAttribute("userAddBindingModel", userAddBindingModel);
            return "redirect:register";
        }

        if (this.userService.findByUsername(userAddBindingModel.getUsername()) != null) {
            redirectAttributes.addFlashAttribute("userExists", true);
            redirectAttributes.addFlashAttribute("userAddBindingModel", userAddBindingModel);
            return "redirect:register";
        }

        this.userService.register(userServiceModel);
        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
            model.addAttribute("notFound", false);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid @ModelAttribute("userLoginBindingModel")
                                       UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "redirect:login";
        }

        UserServiceModel userServiceModel =
                this.userService.findByUsername(userLoginBindingModel.getUsername());

        if (userServiceModel == null || !userServiceModel.getPassword().equals(userLoginBindingModel.getPassword())
                || !userServiceModel.getUsername().equals(userLoginBindingModel.getUsername())) {

            redirectAttributes.addFlashAttribute("notFound", true);
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            return "redirect:login";
        }

        httpSession.setAttribute("user", userServiceModel);
        httpSession.setAttribute("id", userServiceModel.getId());
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            return "redirect:/users/login";
        } else {
            httpSession.invalidate();
            return "redirect:/";
        }
    }
}
