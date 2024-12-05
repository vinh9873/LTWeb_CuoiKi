package vn.ute.controller;

import java.util.List;
import vn.ute.entity.UserWeb;
import vn.ute.service.UserWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserWebController {

    @Autowired
    UserWebService userService;

    @PostMapping("/register")
    public UserWeb register(@RequestBody UserWeb user) {
        return userService.createUser(user);
    }

    @PutMapping
    public UserWeb update(@RequestBody UserWeb user) {
        return userService.updateUser(user);
    }

    @GetMapping
    public List<UserWeb> getUsers() {
        return userService.findAll();
    }

}
