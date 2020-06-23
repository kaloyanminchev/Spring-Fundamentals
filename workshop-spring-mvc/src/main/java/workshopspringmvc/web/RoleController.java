package workshopspringmvc.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import workshopspringmvc.model.binding.RoleAddBindingModel;
import workshopspringmvc.service.RoleService;
import workshopspringmvc.service.UserService;

@Controller
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;
    private final UserService userService;
    private final ModelMapper mapper;

    public RoleController(RoleService roleService, UserService userService, ModelMapper mapper) {
        this.roleService = roleService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/add")
    public ModelAndView add(ModelAndView modelAndView) {

        modelAndView.addObject("usernames", this.userService.findAllUsernames());
        modelAndView.setViewName("role-add");
        return modelAndView;
    }

    @PostMapping("/add")
    public String addConfirm(@ModelAttribute("roleAddBindingModel")
                                         RoleAddBindingModel roleAddBindingModel) {

        //todo validate
        this.userService
                .addRoleToUser(roleAddBindingModel.getUsername(),
                        roleAddBindingModel.getRole());
        return "redirect:/";
    }
}
