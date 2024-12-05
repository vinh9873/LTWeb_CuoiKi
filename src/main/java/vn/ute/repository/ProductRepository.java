package vn.ute.repository;

import vn.ute.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Lấy 10 sản phẩm bán chạy nhất
    List<Product> findTop10ByOrderBySalesDesc();

    // Lấy các sản phẩm theo danh mục (categoryId)
    List<Product> findByCategoryId(Long categoryId);

    // Lấy sản phẩm theo ID
    Product findById(long id);

    // Tìm sản phẩm theo tên (nếu cần)
    List<Product> findByNameContainingIgnoreCase(String name);

	List<Product> findByCategoryId(Long categoryId);

	Product findById(Long productId);
}

