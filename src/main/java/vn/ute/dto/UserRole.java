package vn.ute.dto;

import vn.ute.entity.Role;
import org.springframework.security.core.GrantedAuthority;

public class UserRole implements GrantedAuthority {

    private Role role;

    public UserRole(Role role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return this.role.getRoleName();
    }

    @Override
    public String toString() {
        return "UserRole [role=" + role + ", getAuthority()=" + getAuthority() + "]";
    }

}