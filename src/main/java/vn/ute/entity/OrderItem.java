package vn.ute.entity;

import jakarta.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Khóa chính

    @ManyToOne // Mối quan hệ nhiều về một với Order
    @JoinColumn(name = "order_id") // Đặt tên cột order_id trong bảng OrderItem
    private Order order; // Liên kết với bảng orders

    @ManyToOne // Mối quan hệ nhiều về một với Product
    @JoinColumn(name = "product_id") // Đặt tên cột product_id trong bảng OrderItem
    private Product product; // Liên kết với bảng sản phẩm

    @Column
    private Integer quantity; // Số lượng sản phẩm trong đơn hàng

    @Column
    private Float price; // Giá của sản phẩm

    // Getter và Setter cho id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter và Setter cho order
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    // Getter và Setter cho product
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    // Getter và Setter cho quantity
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    // Getter và Setter cho price
    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
