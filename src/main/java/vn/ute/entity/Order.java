package vn.ute.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Khóa chính

    @ManyToOne // Liên kết với người dùng
    @JoinColumn(name = "user_id")
    private UserWeb user; // Liên kết với người dùng

    @OneToMany(mappedBy = "order") // Mối quan hệ một đối với nhiều (OrderItem)
    private List<OrderItem> orderItems; // Các mục trong đơn hàng

    @Column
    private Float totalAmount; // Tổng giá trị đơn hàng

    @Column
    private String status; // Trạng thái đơn hàng

    @Column
    private String shippingAddress; // Địa chỉ giao hàng

    @Column
    private java.time.LocalDateTime createDate; // Ngày tạo đơn hàng

    @Column
    private java.time.LocalDateTime updateDate; // Ngày cập nhật đơn hàng

    // Getter và setter cho id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter và setter cho user
    public UserWeb getUser() {
        return user;
    }

    public void setUser(UserWeb user) {
        this.user = user;
    }

    // Getter và setter cho totalAmount
    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    // Getter và setter cho status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getter và setter cho shippingAddress
    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    // Getter và setter cho createDate
    public java.time.LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(java.time.LocalDateTime createDate) {
        this.createDate = createDate;
    }

    // Getter và setter cho updateDate
    public java.time.LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(java.time.LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    // Getter và setter cho orderItems
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
