package vn.ute.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "auth") // Tên bảng trong cơ sở dữ liệu
public class Auth implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Sử dụng AUTO_INCREMENT trong DB
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY) // Nhiều Auth có thể liên kết đến một Menu
    @JoinColumn(name = "menu_id", nullable = false) // Khóa ngoại menu_id
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY) // Nhiều Auth có thể liên kết đến một Role
    @JoinColumn(name = "role_id", nullable = false) // Khóa ngoại role_id
    private Role role;

    @Column(name = "permission", nullable = false)
    private int permission;

    @Column(name = "active_flag", nullable = false)
    private int activeFlag;

    @Temporal(TemporalType.TIMESTAMP) // Định nghĩa kiểu dữ liệu thời gian
    @Column(name = "create_date", nullable = false, updatable = false)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date", nullable = false)
    private Date updateDate;

    public Auth() {
    }

    public Auth(Menu menu, Role role, int permission, int activeFlag, Date createDate, Date updateDate) {
        this.menu = menu;
        this.role = role;
        this.permission = permission;
        this.activeFlag = activeFlag;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    // Getters và Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public int getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(int activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
