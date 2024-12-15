package vn.ute.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import jakarta.annotation.security.RolesAllowed;
import vn.ute.entity.Product;
import vn.ute.service.ProductService;
import vn.ute.service.UserWebService;

@RolesAllowed({"admin", "vendor"})
@Controller
@RequestMapping("/products")
public class ProductController {

    public static Path UPLOAD_DIR = Path.of("tmp", "product-images");
    public static String RESOURCE_PATH = "/product-images";

    static {
        try {
            Files.createDirectories(UPLOAD_DIR);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private ProductService productService;

    @Autowired
    private UserWebService userService;

    @PostMapping("/create")
    public String createProduct(
            @RequestParam String name,
            @RequestParam String type,
            @RequestParam Float price,
            @RequestParam MultipartFile image,
            @RequestParam String description) {
        var product = new Product();
        product.setName(name);
        product.setType(type);
        product.setPrice(price);
        var imgSrc = uploadImage(image);
        product.setImage(imgSrc);
        product.setDescription(description);
        productService.createProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/create")
    public String pageCreateProduct(Product product, Model model) {
        return "product";
    }

    @GetMapping
    public String getAllProducts(@RequestParam(value = "search", required = false) String search, Model m) {
        List<Product> products = productService.findByNameContaining(search);
        m.addAttribute("products", products);
        return "/products";
    }

    @RolesAllowed({"admin", "vendor", "user"})
    @GetMapping("/{id}")
    public String getProductById(@PathVariable int id, Model m) {
        var product = productService.getProductById(id);
        m.addAttribute("product", product);

        var user = userService.findCurrentUserEntity();
        var productsBought = user.getProductIdsBought();
        var canReviewProduct = false;
        if (!CollectionUtils.isEmpty(productsBought)) {
            canReviewProduct = productsBought.contains(id);
        }
        m.addAttribute("canReviewProduct", canReviewProduct);
        m.addAttribute("comment", "");
        m.addAttribute("rating", "");
        return "product";
    }

    @RolesAllowed({"admin", "vendor", "user"})
    @PostMapping("/{id}/reviews")
    public String reviewProduct(
            @PathVariable int id,
            @RequestParam(value = "comment", required = false) String comment,
            @RequestParam(value = "rating", required = false) Integer rating,
            Model m) {
        var product = productService.getProductById(id);
        m.addAttribute("product", product);

        var user = userService.findCurrentUserEntity();
        var productsBought = user.getProductIdsBought();
        var canReviewProduct = false;
        if (!CollectionUtils.isEmpty(productsBought)) {
            canReviewProduct = productsBought.contains(id);
        }
        m.addAttribute("canReviewProduct", canReviewProduct);
        return "product";
    }

    @PostMapping("/{id}")
    public String updateProduct(@PathVariable Integer id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Float price,
            @RequestParam(required = false) MultipartFile image,
            @RequestParam(required = false) String description,
            Model m) {
        var imgSrc = uploadImage(image);
        var product = productService.updateProduct(id, name, type, price, imgSrc, description);
        m.addAttribute("product", product);
        return "redirect:/products";
    }

    @PostMapping("/{id}/delete")
    public String deleteProductById(@PathVariable Integer id, Model m) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }

    private static String uploadImage(MultipartFile file) {
        if (file == null) {
            return null;
        }
        Path fileNameAndPath = UPLOAD_DIR.resolve(file.getOriginalFilename());
        try {
            Files.write(fileNameAndPath, file.getBytes());
        }
        catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return fileNameAndPath.toString().replace("" + UPLOAD_DIR, RESOURCE_PATH);
    }

}
