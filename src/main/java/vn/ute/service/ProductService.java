package vn.ute.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import vn.ute.entity.Product;
import vn.ute.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    public Product getProductById(int id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    public Product updateProduct(
            Integer id, String name, String type, Float price, String image, String description) {
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isEmpty()) {
            return null;
        }
        Product product = productOpt.get();
        Optional.ofNullable(name).ifPresent(product::setName);
        Optional.ofNullable(type).ifPresent(product::setType);
        Optional.ofNullable(price).ifPresent(product::setPrice);
        Optional.ofNullable(description).ifPresent(product::setDescription);
        if (image != null && !image.isBlank()) {
            product.setImage(image);
        }
        return productRepository.save(product);
    }

    public void deleteProductById(int id) {
    	productRepository.deleteById(id);
	}

    public List<Product> findByNameContaining(String name) {
    	if (name == null || name.isBlank()) {
    		var products = productRepository.findAll();
    		return StreamSupport.stream(products.spliterator(), false).toList();
    	}
        return productRepository.findByNameContaining(name);
    }

}