package vn.ute.dto;

import vn.ute.entity.Role;
import vn.ute.entity.UserWeb;

public class UserWebDto {

    private Integer id;
    private String name;
    private String phoneNumber;
    private String emailAddress;
    private String password;
    private Integer roleId;

    public UserWeb toEntity(Role role) {
        var entity = new UserWeb();
        entity.setId(id);
        entity.setName(name);
        entity.setPhoneNumber(phoneNumber);
        entity.setEmailAddress(emailAddress);
        entity.setPassword(password);
        entity.setRole(role);
        return entity;
    }

    public static UserWebDto fromEntity(UserWeb entity) {
        var dto = new UserWebDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setEmailAddress(entity.getEmailAddress());
        dto.setPassword(entity.getPassword());
        dto.setRoleId(entity.getRole().getId());
        return dto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}