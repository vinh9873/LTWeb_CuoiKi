package vn.ute.dto;

import org.springframework.security.core.GrantedAuthority;

import vn.ute.entity.Role;

public class UserRole implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	private Role role;

    public UserRole(Role role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return this.role.getName();
    }

    @Override
    public String toString() {
        return "UserRole [role=" + role + ", getAuthority()=" + getAuthority() + "]";
    }

}