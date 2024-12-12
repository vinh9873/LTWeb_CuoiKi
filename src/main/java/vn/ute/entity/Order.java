package vn.ute.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
