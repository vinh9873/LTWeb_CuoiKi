package vn.ute.controller;
import vn.ute.dto.UserWebDetails;
import vn.ute.entity.Role;
import vn.ute.service.UserWebService;
import vn.ute.util.SecCtxHolderUtils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class PreModelAttributeAdvice {

    @Autowired
    UserWebService userService;

    @ModelAttribute
    public void addCommonAttributes(Model m, HttpServletRequest req) {
        var userDetails = SecCtxHolderUtils.getUserDetails();
        var isLogin = false;
        if (userDetails instanceof UserWebDetails user) {
            var role = user.getAuthorities().iterator().next().getAuthority();
            isLogin = true;
            m.addAttribute("role", role);
            var currentUser = userService.findCurrentUser();
            m.addAttribute("userName", currentUser.getName());
        }
        m.addAttribute("isLogin", isLogin);
        m.addAttribute("currentContext", req.getRequestURI());
        m.addAttribute("hasItemInCart", true); // TODO implement this
    }

    @ModelAttribute("allRoles")
    public List<Role> getAllRoles() {
        return userService.findAllUserRoles();
    }

}