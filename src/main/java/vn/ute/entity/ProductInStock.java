package vn.ute.entity;

import jakarta.persistence.*;
import java.util.Date;

/**
 * ProductInStock entity class for JPA
 */
@Entity
@Table(name = "product_in_stock") // Tên bảng trong cơ sở dữ liệu
public class ProductInStock implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tạo id (sử dụng auto_increment trong DB)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_info_id", nullable = false) // Thiết lập quan hệ với bảng ProductInfo
    private ProductInfo productInfo;

    @Column(name = "qty", nullable = false)
    private int qty;

    @Column(name = "active_flag", nullable = false)
    private int activeFlag;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false, updatable = false)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date", nullable = false)
    private Date updateDate;

    // Constructors
    public ProductInStock() {
    }

    public ProductInStock(ProductInfo productInfo, int qty, int activeFlag, Date createDate, Date updateDate) {
        this.productInfo = productInfo;
        this.qty = qty;
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

    public int getQty() {
        return this.qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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
