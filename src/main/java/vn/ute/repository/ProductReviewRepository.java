package vn.ute.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import vn.ute.entity.ProductReview;

public interface ProductReviewRepository extends CrudRepository<ProductReview, Integer> {

    List<ProductReview> findAllByProductId(Integer productId);

    ProductReview findByProductIdAndUserId(Integer productId, Integer userId);
}
