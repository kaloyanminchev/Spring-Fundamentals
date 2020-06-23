package softuni.heroes.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.heroes.service.HeroService;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final HeroService heroService;

    @Autowired
    public HomeController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping("/")
    public ModelAndView index(HttpSession session, ModelAndView modelAndView) {
        if (session.getAttribute("user") == null) {
            modelAndView.setViewName("index");
        } else {
            modelAndView.addObject("heroes", this.heroService.findAllHeroes());
            modelAndView.setViewName("home");
        }
        return modelAndView;
    }
}
