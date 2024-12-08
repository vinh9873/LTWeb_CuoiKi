package vn.ute.dto;

import lombok.Data;
import vn.ute.entity.Role;
import vn.ute.entity.UserWeb;

@Data
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
        entity.setIsActive(true);
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
}