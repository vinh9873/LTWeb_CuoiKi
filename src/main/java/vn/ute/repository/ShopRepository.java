package vn.ute.repository;

import java.util.List;
import vn.ute.entity.Shop;
import org.springframework.data.repository.CrudRepository;

public interface ShopRepository extends CrudRepository<Shop, Integer> {
  
    List<Shop> findByName(String name);
    List<Shop> findByType(String type);
    List<Shop> findByNameContaining(String name);
    void deleteById(int id); 
}