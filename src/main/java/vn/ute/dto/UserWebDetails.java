package vn.ute.dto;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserWebDetails implements UserDetails {

    private int id;
    private String username;
    private String password;
    private Collection<UserRole> roles;

    public UserWebDetails(Integer id, String username, String password, Collection<UserRole> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public int getId() {
        return id;
    }

}