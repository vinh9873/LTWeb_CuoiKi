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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.annotation.security.RolesAllowed;
import vn.ute.entity.Product;
import vn.ute.service.ProductService;

@RolesAllowed({"admin", "vendor", "user"})
@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    ProductService prodService;

    @PostMapping("/products/{prodId}/add-to-cart")
    public String buyProduct(@PathVariable Integer prodId, Model m) {
        prodService.addToCart(prodId);
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String viewCart(Model m) {
        var products = prodService.findAllProductInCart();
        var totalCost = products.stream()
                .map(Product::getPrice)
                .reduce(0F, (a, b) -> a + b);
        m.addAttribute("products", products);
        m.addAttribute("totalCost", totalCost);
        return "cart";
    }

    @PostMapping("/buy-now")
    public String buyAllItemsInCart() {
        prodService.buyAllItemsInCart();
        return "payment-success";
    }
    

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

    @PostMapping("/products/{prodId}/remove")
    public String removeToCart(@PathVariable Integer prodId) {
        prodService.removeFromCart(prodId);
        return "redirect:/orders/cart";
    }
    
}
