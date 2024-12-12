package vn.ute.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.ute.entity.Order;
import vn.ute.entity.OrderItem;
import vn.ute.repository.OrderRepository;
import vn.ute.repository.OrderItemRepository;

@Service
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    // Tạo đơn hàng mới
    public Order createOrder(Order order, List<OrderItem> items) {
        Order newOrder = orderRepository.save(order);
        for (OrderItem item : items) {
            item.setOrder(newOrder);
            orderItemRepository.save(item);
        }
        return newOrder;
    }

    // Lấy danh sách đơn hàng của người dùng
    public List<Order> getOrdersByUserId(int userId) {
        return orderRepository.findByUserId(userId);
    }

    // Lấy chi tiết đơn hàng
    public Optional<Order> getOrderById(int orderId) {
        return orderRepository.findById(orderId);
    }
}
