package vn.ute.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * History entity class for JPA
 */
@Entity
@Table(name = "history") // Tên bảng trong cơ sở dữ liệu
public class History implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tạo id (sử dụng auto_increment trong DB)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY) // Mối quan hệ ManyToOne với ProductInfo
    @JoinColumn(name = "product_info_id", referencedColumnName = "id") // Chỉ định khóa ngoại
    private ProductInfo productInfo;

    @Column(name = "action_name", nullable = false, length = 255)
    private String actionName;

    @Column(name = "type", nullable = false)
    private int type;

    @Column(name = "qty", nullable = false)
    private int qty;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "active_flag", nullable = false)
    private int activeFlag;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false, updatable = false)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date", nullable = false)
    private Date updateDate;

    // Constructors
    public History() {
    }

    public History(ProductInfo productInfo, String actionName, int type, int qty, BigDecimal price, int activeFlag,
                   Date createDate, Date updateDate) {
        this.productInfo = productInfo;
        this.actionName = actionName;
        this.type = type;
        this.qty = qty;
        this.price = price;
        this.activeFlag = activeFlag;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    // Getters and Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductInfo getProductInfo() {
        return this.productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    public String getActionName() {
        return this.actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getQty() {
        return this.qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

}
