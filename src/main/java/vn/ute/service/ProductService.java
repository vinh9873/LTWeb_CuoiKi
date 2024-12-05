package vn.ute.service;

import vn.ute.entity.Product;
import vn.ute.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Lấy 10 sản phẩm bán chạy nhất
    public List<Product> getTopSellingProducts() {
        return productRepository.findTop10ByOrderBySalesDesc();
    }

    // Lấy sản phẩm theo danh mục
    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    // Lấy sản phẩm theo ID
    public Product getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    // Tìm sản phẩm theo tên
    public List<Product> searchProducts(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
}
