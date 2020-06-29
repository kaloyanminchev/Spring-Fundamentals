package softuni.exam.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.service.ProductService;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final ProductService productService;

    @Autowired
    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ModelAndView index(HttpSession session, ModelAndView modelAndView) {
        if (session.getAttribute("user") == null) {
            modelAndView.setViewName("index");
        } else {
            modelAndView.addObject("totalPrice", this.productService.getTotalPrice());
            modelAndView.addObject("foodProducts", this.productService.findAllProductsByFood());
            modelAndView.addObject("drinkProducts", this.productService.findAllProductsByDrinks());
            modelAndView.addObject("householdProducts", this.productService.findAllProductsByHouseholds());
            modelAndView.addObject("otherProducts", this.productService.findAllProductsByOthers());
            modelAndView.setViewName("home");
        }
        return modelAndView;
    }
}
