package vn.ute.controller;
import vn.ute.service.UserWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class PageController {
    @Autowired
    UserWebService userService;
    @RequestMapping("/")
    public String homePage() {
        return "index";
    }
    @RequestMapping("/register")
    public String register() {
        return "register";
    }
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    @RequestMapping("/user-profile")
    public String userProfile(Model m) {
        var user = userService.findCurrentUser();
        m.addAttribute("email", user.getEmail());
        m.addAttribute("password", user.getPassword());
        m.addAttribute("fullName", user.getName());
        m.addAttribute("phone", user.getPhoneNumber());
        m.addAttribute("role", user.getRole().getRoleName());
        m.addAttribute("userId", user.getId());
        return "user-profile";
    }
    @RequestMapping("/403")
    public String accessDenied() {
        return "403";
    }
}