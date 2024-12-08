package vn.ute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vn.ute.entity.Order;
import vn.ute.service.OrderService;
import vn.ute.util.SecCtxHolderUtils;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Lịch sử đơn hàng của người dùng
    @GetMapping("/orders")
    public String getOrderHistory(Model m) {
        int userId = SecCtxHolderUtils.getUserId();
        List<Order> orders = orderService.getOrdersByUserId(userId);
        m.addAttribute("orders", orders);
        return "order-history";
    }

    // Xem chi tiết đơn hàng
    @GetMapping("/orders/{id}")
    public String getOrderDetails(@PathVariable int id, Model m) {
        Order order = orderService.getOrderById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        m.addAttribute("order", order);
        return "order-details";
    }
}
