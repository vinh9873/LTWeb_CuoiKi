package vn.ute.util;

import vn.ute.dto.UserWebDetails;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecCtxHolderUtils {

    private SecCtxHolderUtils() {}

    public static Object getUserDetails() {
        var ctx = SecurityContextHolder.getContext();
        return ctx.getAuthentication().getPrincipal();
    }

    public static boolean isLoggedIn() {
        var userDetails = getUserDetails();
        if (userDetails instanceof UserWebDetails) {
            return true;
        }
        return false;
    }
    
    public static Integer getUserId() {
        var userDetails = getUserDetails();
        if (userDetails instanceof UserWebDetails user) {
            return user.getId();
        }
        throw new AuthenticationServiceException("Could not get UserWeb id");
    }
}