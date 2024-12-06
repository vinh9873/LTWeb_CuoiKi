package vn.ute.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Menu entity class for JPA
 */
@Entity
@Table(name = "menu") // Tên bảng trong cơ sở dữ liệu
public class Menu implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tạo id (sử dụng auto_increment trong DB)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "parent_id", nullable = false)
    private int parentId;

    @Column(name = "url", nullable = false, length = 255)
    private String url;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "order_index", nullable = false)
    private int orderIndex;

    @Column(name = "active_flag", nullable = false)
    private int activeFlag;

    @Temporal(TemporalType.TIMESTAMP) // Kiểu dữ liệu thời gian
    @Column(name = "create_date", nullable = false, updatable = false)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date", nullable = false)
    private Date updateDate;

    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Auth> auths = new HashSet<>(0);

    // Constructors
    public Menu() {
    }

    public Menu(int parentId, String url, String name, int orderIndex, int activeFlag, Date createDate,
                Date updateDate) {
        this.parentId = parentId;
        this.url = url;
        this.name = name;
        this.orderIndex = orderIndex;
        this.activeFlag = activeFlag;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Menu(int parentId, String url, String name, int orderIndex, int activeFlag, Date createDate, Date updateDate,
                Set<Auth> auths) {
        this.parentId = parentId;
        this.url = url;
        this.name = name;
        this.orderIndex = orderIndex;
        this.activeFlag = activeFlag;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.auths = auths;
    }

    // Getters and Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getParentId() {
        return this.parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrderIndex() {
        return this.orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public int getActiveFlag() {
        return this.activeFlag;
    }

    public void setActiveFlag(int activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Set<Auth> getAuths() {
        return this.auths;
    }

    public void setAuths(Set<Auth> auths) {
        this.auths = auths;
    }
}
