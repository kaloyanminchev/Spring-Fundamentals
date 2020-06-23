package workshopspringmvc.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import workshopspringmvc.model.binding.UserAddBindingModel;
import workshopspringmvc.model.binding.UserLoginBindingModel;
import workshopspringmvc.model.service.UserServiceModel;
import workshopspringmvc.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public ModelAndView login(@Valid @ModelAttribute("userLoginBindingModel")
                                      UserLoginBindingModel userLoginBindingModel,
                              BindingResult bindingResult, ModelAndView modelAndView) {

        modelAndView.addObject("userLoginBindingModel", userLoginBindingModel);
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView loginConfirm(@Valid @ModelAttribute("userLoginBindingModel")
                                             UserLoginBindingModel userLoginBindingModel,
                                     BindingResult bindingResult, ModelAndView modelAndView,
                                     HttpSession httpSession, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            modelAndView.setViewName("redirect:/users/login");

        } else {
            UserServiceModel userServiceModel =
                    this.userService.findByUsername(userLoginBindingModel.getUsername());

            if (userServiceModel == null || !userServiceModel.getPassword()
                    .equals(userLoginBindingModel.getPassword()) ||
                    !userServiceModel.getUsername().equals(userLoginBindingModel.getUsername())) {

                redirectAttributes.addFlashAttribute("notFound", true);
//                redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
                modelAndView.setViewName("redirect:/users/login");

            } else {
                httpSession.setAttribute("user", userServiceModel);
                httpSession.setAttribute("id", userServiceModel.getId());
                httpSession.setAttribute("role", userServiceModel.getRole().getName());
                modelAndView.setViewName("redirect:/");
            }
        }

        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute("userAddBindingModel")
                                         UserAddBindingModel userAddBindingModel,
                                 BindingResult bindingResult, ModelAndView modelAndView) {

        modelAndView.addObject("userAddBindingModel", userAddBindingModel);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@Valid @ModelAttribute("userAddBindingModel")
                                                UserAddBindingModel userAddBindingModel,
                                        BindingResult bindingResult, ModelAndView modelAndView,
                                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userAddBindingModel", userAddBindingModel);
            modelAndView.setViewName("redirect:/users/register");
        } else {

            if (!userAddBindingModel.getPassword()
                    .equals(userAddBindingModel.getConfirmPassword())) {

                redirectAttributes.addFlashAttribute("mismatch", true);
                redirectAttributes.addFlashAttribute("userRegisterBindingModel",
                        userAddBindingModel);
                modelAndView.setViewName("redirect:/users/register");

            } else {
                this.userService.registerUser(this.modelMapper
                        .map(userAddBindingModel, UserServiceModel.class));
                modelAndView.setViewName("redirect:/users/login");
            }
        }

        return modelAndView;
    }
}
