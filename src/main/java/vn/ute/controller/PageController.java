package vn.ute.controller;

import vn.ute.dto.UserWebDto;
import vn.ute.service.UserWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String getRegisterPage(@ModelAttribute("user") UserWebDto user) {
        return "register";
    }

    @PostMapping("/register/submit")
    public String register(UserWebDto user, BindingResult binding) {
        if (binding.hasErrors()) {
            return "error";
        }
        var role = userService.findRole(user.getRoleId());
        userService.createUser(user.toEntity(role));
        return "login";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/user-profile")
    public String userProfile(Model m) {
        var user = userService.findCurrentUser();
        var maskedPassword = user.getPassword().replaceAll(".", "*");
        user.setPassword(maskedPassword);
        m.addAttribute("user", user);
        return "user-profile";
    }

    @RequestMapping("/403")
    public String accessDenied() {
        return "403";
    }
}