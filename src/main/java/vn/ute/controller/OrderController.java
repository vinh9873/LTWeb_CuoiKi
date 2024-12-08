package vn.ute.controller;

import vn.ute.entity.Product;
import vn.ute.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.annotation.security.RolesAllowed;

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

}
