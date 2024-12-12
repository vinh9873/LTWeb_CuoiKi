package vn.ute.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
