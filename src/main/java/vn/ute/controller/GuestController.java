package vn.ute.controller;

import vn.ute.entity.Product;
import vn.ute.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GuestController {

    @Autowired
    private ProductService productService;

    // Hiển thị trang chủ với các sản phẩm bán chạy hoặc sản phẩm mới nhất
    @RequestMapping("/")
    public String homePage(Model model) {
        // Lấy 10 sản phẩm bán chạy nhất
        var products = productService.getTopSellingProducts();
        model.addAttribute("products", products);
        return "index"; // Trả về giao diện trang chủ
    }

    // Hiển thị các sản phẩm theo danh mục
    @RequestMapping("/category/{categoryId}")
    public String categoryPage(@PathVariable("categoryId") Long categoryId, Model model) {
        // Lấy các sản phẩm theo danh mục
        var productsByCategory = productService.getProductsByCategory(categoryId);
        model.addAttribute("products", productsByCategory);
        return "category"; // Trả về giao diện danh mục sản phẩm
    }

    // Hiển thị chi tiết sản phẩm
    @RequestMapping("/product/{productId}")
    public String productDetail(@PathVariable("productId") Long productId, Model model) {
        // Lấy chi tiết sản phẩm
        var product = productService.getProductById(productId);
        model.addAttribute("product", product);
        return "product-detail"; // Trả về giao diện chi tiết sản phẩm
    }

    // Giỏ hàng của Guest
    @RequestMapping("/cart")
    public String viewCart(Model model) {
        // Lấy các sản phẩm trong giỏ hàng của Guest (từ session hoặc cơ sở dữ liệu)
        var cartItems = productService.getCartItemsForGuest(); // Đây là giả lập, bạn cần thêm logic giỏ hàng thực tế
        model.addAttribute("cartItems", cartItems);
        return "cart"; // Trả về giao diện giỏ hàng
    }
}
