package vn.ute.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category") // Tên bảng trong cơ sở dữ liệu
public class Category implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Sử dụng auto_increment cho trường id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "code", nullable = false, length = 50)
    private String code;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "active_flag", nullable = false)
    private int activeFlag;

    @Temporal(TemporalType.TIMESTAMP) // Kiểu dữ liệu thời gian
    @Column(name = "create_date", nullable = false, updatable = false)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date", nullable = false)
    private Date updateDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category") // Mối quan hệ OneToMany với ProductInfo
    private Set<ProductInfo> productInfos = new HashSet<>(0);

    // Constructors
    public Category() {
    }

    public Category(String name, String code, int activeFlag, Date createDate, Date updateDate) {
        this.name = name;
        this.code = code;
        this.activeFlag = activeFlag;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Category(String name, String code, String description, int activeFlag, Date createDate, Date updateDate,
                    Set<ProductInfo> productInfos) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.activeFlag = activeFlag;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.productInfos = productInfos;
    }

    // Getters and Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Set<ProductInfo> getProductInfos() {
        return this.productInfos;
    }

    public void setProductInfos(Set<ProductInfo> productInfos) {
        this.productInfos = productInfos;
    }

}
