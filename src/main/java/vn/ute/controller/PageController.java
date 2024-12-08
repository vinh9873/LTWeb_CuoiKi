package vn.ute.controller;

import vn.ute.dto.UserWebDto;
import vn.ute.service.ProductService;
import vn.ute.service.UserWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    @Autowired
    UserWebService userService;

    @Autowired
    ProductService prodService;

    @GetMapping("/")
    public String homePage(Model m) {
        var products = prodService.findAllProducts();
        m.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/register")
    public String getRegisterPage(@ModelAttribute("user") UserWebDto user) {
        return "register";
    }

    @PostMapping("/register/submit")
    public String register(UserWebDto user, BindingResult binding, Model m) {
        if (binding.hasErrors()) {
            return "error";
        }
       
        // prevent Duplicate Email Address
        if (userService.isEmailAlreadyRegistered(user.getEmailAddress())) {
            m.addAttribute("duplicatedEmail", true);
            m.addAttribute("user", new UserWebDto());
            return "register";
        }
        var role = userService.findRole(user.getRoleId());
        userService.createUser(user.toEntity(role));
        return "please-check-email";
       
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/verify")
    public String verify(@RequestParam("code") String code) {
        userService.verifyUser(code);
        return "verified";
    }

    @GetMapping("/user-profile")
    public String userProfile(Model m) {
        var user = userService.findCurrentUser();
        var maskedPassword = user.getPassword().replaceAll(".", "*");
        user.setPassword(maskedPassword);
        m.addAttribute("user", user);
        return "user-profile";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }
}