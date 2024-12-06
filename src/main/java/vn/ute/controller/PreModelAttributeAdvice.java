package vn.ute.controller;
import vn.ute.dto.UserWebDetails;
import vn.ute.util.SecCtxHolderUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import jakarta.servlet.http.HttpServletRequest;
@ControllerAdvice
public class PreModelAttributeAdvice {
    @ModelAttribute
    public void addCommonAttributes(Model m, HttpServletRequest req) {
        var userDetails = SecCtxHolderUtils.getUserDetails();
        var isLogin = false;
        if (userDetails instanceof UserWebDetails user) {
            var role = user.getAuthorities().iterator().next().getAuthority();
            isLogin = true;
            m.addAttribute("userName", user.getUsername());
            m.addAttribute("role", role);
        }
        m.addAttribute("isLogin", isLogin);
        m.addAttribute("currentContext", req.getRequestURI());
        m.addAttribute("hasItemInCart", true); // TODO implement this
    }
}