package vn.ute.repository;

import java.util.List;
import vn.ute.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
  
    List<Product> findByName(String name);
    List<Product> findByType(String type);
    List<Product> findByNameContaining(String name);
    void deleteById(int id); 
}