package vn.ute.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import jakarta.annotation.security.RolesAllowed;
import vn.ute.dto.ProductReviewDto;
import vn.ute.entity.Product;
import vn.ute.repository.ProductReviewRepository;
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

    @Autowired
    private ProductReviewRepository reviewRepo;

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

        var canUpdateReview = productService.canUpdateReview(product.getId(), user.getId());
        m.addAttribute("canUpdateReview", canUpdateReview);

        var review = new ProductReviewDto();
        if (canUpdateReview) {
            var entity = reviewRepo.findByProductIdAndUserId(id, user.getId());
            review.setComment(entity.getComment());
            review.setRate(Optional.ofNullable(entity.getRate()).map(Double::intValue).orElse(0));
            review.setId(entity.getId());
        }
        m.addAttribute("review", review);

        var reviews = productService.findAllReviewsByProductId(id);
        m.addAttribute("reviews", reviews);

        return "product";
    }

    @RolesAllowed({"admin", "vendor", "user"})
    @PostMapping("/{id}/reviews")
    public String reviewProduct(@PathVariable int id, ProductReviewDto review, BindingResult binding, Model m) {
        if (binding.hasErrors()) {
            return "error";
        }
        productService.addReview(id, review);
        return getProductById(id, m);
    }

    @RolesAllowed({"admin", "vendor", "user"})
    @PostMapping("/{id}/reviews/{revId}")
    public String updateProductReview(
            @PathVariable int id,
            @PathVariable int revId,
            ProductReviewDto review,
            BindingResult binding,
            Model m) {
        if (binding.hasErrors()) {
            return "error";
        }
        productService.updateReview(revId, review);
        return getProductById(id, m);
    }

    @RolesAllowed({"admin", "vendor", "user"})
    @GetMapping("/{id}/reviews/{revId}/delete")
    public String deleteProductReview(@PathVariable int id, @PathVariable int revId, Model m) {
        productService.deleteReview(revId);
        return getProductById(id, m);
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
