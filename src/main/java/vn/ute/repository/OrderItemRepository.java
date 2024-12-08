package vn.ute.repository;

import org.springframework.data.repository.CrudRepository;
import vn.ute.entity.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {
}
