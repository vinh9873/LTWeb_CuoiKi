package vn.ute.controller;

import vn.ute.dto.UserWebDto;
import vn.ute.service.UserWebService;
import vn.ute.util.SecCtxHolderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.annotation.security.RolesAllowed;

@RolesAllowed({"admin"})
@Controller
public class UserPageController {

    @Autowired
    UserWebService userService;

    @RequestMapping("/users")
    public String userPage(@RequestParam(value = "search", required = false) String search, Model m) {
        var users = userService.findAll(search);
        m.addAttribute("users", users);
        return "users";
    }

    @PostMapping("/users/{id}/delete")
    public String deleteUserById(@PathVariable Integer id, Model m) {
        userService.deleteUserById(id);
        return userPage(null, m);
    }

    @RequestMapping("/users/{id}")
    public String getUserDetails(@PathVariable Integer id, Model m) {
        var user = userService.findUserById(id);
        m.addAttribute("user", user);
        return "user-profile";
    }

    @RolesAllowed({"admin", "user", "vendor"})
    @PostMapping("/users/{id}/update")
    public String updateUser(@PathVariable Integer id, UserWebDto user, BindingResult binding, Model m) {
        if (binding.hasErrors()) {
            return "error";
        }
        var role = userService.findRole(user.getRoleId());
        var updatedUser = userService.updateUser(user.toEntity(role));
        m.addAttribute("user", updatedUser);

        if (id.equals(SecCtxHolderUtils.getUserId())) {
            return "redirect:/user-profile?success";
        }
        return "redirect:/users";
    }

}