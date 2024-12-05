//package vn.ute.entity;
//
//import jakarta.persistence.*;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Table(name = "users")
//public class Users implements java.io.Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tạo ID tự động
//    private Integer id;
//
//    @Column(name = "user_name", nullable = false, unique = true)
//    private String userName;
//
//    @Column(name = "password", nullable = false)
//    private String password;
//
//    @Column(name = "email", nullable = false, unique = true)
//    private String email;
//
//    @Column(name = "name", nullable = false)
//    private String name;
//
//    @Column(name = "active_flag", nullable = false)
//    private int activeFlag;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "create_date", nullable = false)
//    private Date createDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "update_date", nullable = false)
//    private Date updateDate;
//
//    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<UserRole> userRoles = new HashSet<>();
//
//    // Constructors
//    public Users() {
//    }
//
//    public Users(String userName, String password, String name, int activeFlag, Date createDate, Date updateDate) {
//        this.userName = userName;
//        this.password = password;
//        this.name = name;
//        this.activeFlag = activeFlag;
//        this.createDate = createDate;
//        this.updateDate = updateDate;
//    }
//
//    public Users(String userName, String password, String email, String name, int activeFlag, Date createDate,
//                 Date updateDate, Set<UserRole> userRoles) {
//        this.userName = userName;
//        this.password = password;
//        this.email = email;
//        this.name = name;
//        this.activeFlag = activeFlag;
//        this.createDate = createDate;
//        this.updateDate = updateDate;
//        this.userRoles = userRoles;
//    }
//
//    // Getters and Setters
//    public Integer getId() {
//        return this.id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getUserName() {
//        return this.userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getPassword() {
//        return this.password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getEmail() {
//        return this.email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getName() {
//        return this.name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getActiveFlag() {
//        return this.activeFlag;
//    }
//
//    public void setActiveFlag(int activeFlag) {
//        this.activeFlag = activeFlag;
//    }
//
//    public Date getCreateDate() {
//        return this.createDate;
//    }
//
//    public void setCreateDate(Date createDate) {
//        this.createDate = createDate;
//    }
//
//    public Date getUpdateDate() {
//        return this.updateDate;
//    }
//
//    public void setUpdateDate(Date updateDate) {
//        this.updateDate = updateDate;
//    }
//
//    public Set<UserRole> getUserRoles() {
//        return this.userRoles;
//    }
//
//    public void setUserRoles(Set<UserRole> userRoles) {
//        this.userRoles = userRoles;
//    }
//
//	public Role getRole() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
