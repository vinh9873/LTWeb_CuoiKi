package vn.ute.controller;

import vn.ute.dto.UserWebDto;
import vn.ute.service.ProductService;
import vn.ute.service.UserWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    private static final int PAGE_SIZE = 8;

    @Autowired
    UserWebService userService;

    @Autowired
    ProductService prodService;

    @GetMapping("/")
    public String homePage(@RequestParam(value = "page", required = false) Integer page, Model m) {
        int pageNumber = 0;
        if (page != null) {
            pageNumber = page;
        }
        var pageRequest = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("soldNumber").descending());
        var products = prodService.findAllProducts(pageRequest);
        m.addAttribute("products", products);
        m.addAttribute("totalPages", products.getTotalPages());
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

    @GetMapping("/products-bought")
    public String getProductsBoughtByCurrentUser(Model m) {
        var user = userService.findCurrentUserEntity();
        var productIds = user.getProductIdsBought();
        var products = prodService.findAllProducts(productIds);
        m.addAttribute("products", products);
        return "products-bought";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }
}
