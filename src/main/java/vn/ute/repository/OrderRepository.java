package vn.ute.repository;

import org.springframework.data.repository.CrudRepository;
import vn.ute.entity.Order;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    List<Order> findByUserId(int userId);
}
