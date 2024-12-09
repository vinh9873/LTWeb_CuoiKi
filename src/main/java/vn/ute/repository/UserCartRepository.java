package vn.ute.repository;

import java.util.Optional;
import vn.ute.entity.UserCart;
import org.springframework.data.repository.CrudRepository;

public interface UserCartRepository extends CrudRepository<UserCart, Integer> {

    Optional<UserCart> findByUserId(Integer userId);

}
