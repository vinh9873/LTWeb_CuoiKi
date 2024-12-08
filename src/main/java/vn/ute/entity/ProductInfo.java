package vn.ute.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

/**
 * ProductInfo entity class for JPA
 */
@Entity
@Table(name = "product_info") // Tên bảng trong cơ sở dữ liệu
public class ProductInfo implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tạo id (sử dụng auto_increment trong DB)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false) // Thiết lập quan hệ với bảng Category
    private Category category;

    @Column(name = "code", nullable = false, length = 50)
    private String code;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "img_url", length = 255)
    private String imgUrl;

    @Column(name = "active_flag", nullable = false)
    private int activeFlag;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false, updatable = false)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date", nullable = false)
    private Date updateDate;

    @OneToMany(mappedBy = "productInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<History> histories = new HashSet<>(0);

    @OneToMany(mappedBy = "productInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProductInStock> productInStocks = new HashSet<>(0);

    @OneToMany(mappedBy = "productInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Invoice> invoices = new HashSet<>(0);

    // Constructors
    public ProductInfo() {
    }

    public ProductInfo(Category category, String code, String name, String imgUrl, int activeFlag, Date createDate,
                       Date updateDate) {
        this.category = category;
        this.code = code;
        this.name = name;
        this.imgUrl = imgUrl;
        this.activeFlag = activeFlag;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public ProductInfo(Category category, String code, String name, String description, String imgUrl, int activeFlag,
                       Date createDate, Date updateDate, Set<History> histories, Set<ProductInStock> productInStocks,
                       Set<Invoice> invoices) {
        this.category = category;
        this.code = code;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.activeFlag = activeFlag;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.histories = histories;
        this.productInStocks = productInStocks;
        this.invoices = invoices;
    }

    // Getters and Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public Set<History> getHistories() {
        return this.histories;
    }

    public void setHistories(Set<History> histories) {
        this.histories = histories;
    }

    public Set<ProductInStock> getProductInStocks() {
        return this.productInStocks;
    }

    public void setProductInStocks(Set<ProductInStock> productInStocks) {
        this.productInStocks = productInStocks;
    }

    public Set<Invoice> getInvoices() {
        return this.invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

	public MultipartFile getMultipartFile() {
		// TODO Auto-generated method stub
		return null;
	}



}
