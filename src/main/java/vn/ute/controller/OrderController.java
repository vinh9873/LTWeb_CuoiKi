package vn.ute.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.annotation.security.RolesAllowed;
import vn.ute.dto.VnPayRequest;
import vn.ute.entity.Order;
import vn.ute.entity.Product;
import vn.ute.service.OrderService;
import vn.ute.service.ProductService;
import vn.ute.util.SecCtxHolderUtils;
import vn.ute.util.VnPayRequestUtil;

@RolesAllowed({"admin", "vendor", "user"})
@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private ProductService prodService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/products/{prodId}/add-to-cart")
    public String buyProduct(@PathVariable Integer prodId, Model m) {
        prodService.addToCart(prodId);
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String viewCart(Model m) {
        var products = prodService.findAllProductInCart();
        var payAmount = products.stream()
                .map(Product::getPrice)
                .reduce(0F, (a, b) -> a + b);
        m.addAttribute("products", products);

        var payRequest = new VnPayRequest();
        payRequest.setVnp_Amount(payAmount);
        m.addAttribute("payRequest", payRequest);
        return "cart";
    }

    @PostMapping("/buy-now")
    public String buyAllItemsInCart(VnPayRequest vnpayRequest) {
        var paymentTimed = VnPayRequestUtil.getPaymentTimed();
        vnpayRequest.setVnp_CreateDate(paymentTimed[0]);
        vnpayRequest.setVnp_ExpireDate(paymentTimed[1]);
        return "redirect:" + VnPayRequest.VNP_URL + VnPayRequestUtil.buildUrlQuery(vnpayRequest);
    }

    @GetMapping("/pay-with-vnpay-return")
    public String paymentSuccess(Model m) {
        var vnpResponseCode = "00";
        if ("00".equals(vnpResponseCode)) {
            prodService.buyAllItemsInCart();
            return "/payment-success";
        }
        return "/payment-failure";
    }

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
