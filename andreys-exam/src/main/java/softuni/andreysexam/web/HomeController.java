package softuni.andreysexam.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.andreysexam.service.ItemService;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final ItemService itemService;

    @Autowired
    public HomeController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/")
    public ModelAndView index(HttpSession httpSession, ModelAndView modelAndView) {

        if (httpSession.getAttribute("user") == null) {
            modelAndView.setViewName("index");
        } else {
            modelAndView.setViewName("home");
            modelAndView.addObject("items", this.itemService.findAllItems());
        }

        return modelAndView;
    }
}
