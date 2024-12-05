package vn.ute.controller;

import vn.ute.service.UserWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.annotation.security.RolesAllowed;

@RolesAllowed({"admin"})
@Controller
public class UserPageController {

    @Autowired
    UserWebService userService;

    @RequestMapping("/users")
    public String userPage(Model m) {
        var users = userService.findAll();
        m.addAttribute("message", "admin");
        m.addAttribute("users", users);
        return "user";
    }

    @PostMapping("/users/{id}/delete")
    public String deleteUserById(@PathVariable Integer id, Model m) {
        userService.deleteUserById(id);
        return userPage(m);
    }

}
