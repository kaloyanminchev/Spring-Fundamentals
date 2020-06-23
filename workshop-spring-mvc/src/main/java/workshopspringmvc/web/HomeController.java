package workshopspringmvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(HttpSession session) {
        return session.getAttribute("user") == null ? "index" : "home";
    }
}
