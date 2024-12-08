package vn.ute.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import vn.ute.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    Page<Product> findAll(Pageable page);
    List<Product> findByName(String name);
    List<Product> findByType(String type);
    List<Product> findByNameContaining(String name);
    void deleteById(int id);
}