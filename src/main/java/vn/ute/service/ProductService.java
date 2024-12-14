package vn.ute.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.ute.entity.Product;
import vn.ute.entity.UserCart;
import vn.ute.repository.ProductRepository;
import vn.ute.repository.UserCartRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserCartRepository cartRepo;

    @Autowired
    private UserWebService userService;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Page<Product> findAllProducts(Pageable page) {
        return productRepository.findAll(page);
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

    public void addToCart(Integer prodId) {
        var currentUser = userService.findCurrentUserEntity();
        var cartOpt = cartRepo.findByUserId(currentUser.getId());
        UserCart cart;
        if (cartOpt.isEmpty()) {
            cart = new UserCart();
            cart.setUser(currentUser);
        }
        else {
            cart = cartOpt.get();
        }
        cart.addProductToCart(prodId);
        cartRepo.save(cart);
    }

    public List<Product> findAllProductInCart() {
        var currentUser = userService.findCurrentUserEntity();
        var cart = cartRepo.findByUserId(currentUser.getId()).orElseThrow();
        var prodIds = cart.getProductIds();
        if (prodIds == null || prodIds.isEmpty()) {
            return List.of();
        }
        return prodIds.stream()
                .map(id -> productRepository.findById(id).orElse(null))
                .filter(Objects::nonNull)
                .toList();
    }

    public void buyAllItemsInCart() {
        var currentUser = userService.findCurrentUserEntity();
        var cart = cartRepo.findByUserId(currentUser.getId()).orElseThrow();
        var updatedProducts = cart.getProductIds().stream()
                .map(id -> productRepository.findById(id).orElse(null))
                .filter(Objects::nonNull)
                .map(Product::increaseSoldNumber)
                .toList();
        productRepository.saveAll(updatedProducts);
        cartRepo.delete(cart);
    }

    public void removeFromCart(Integer prodId) {
        var currentUser = userService.findCurrentUserEntity();
        var cartOpt = cartRepo.findByUserId(currentUser.getId());

        if (cartOpt.isEmpty()) {
            return;
        }

        var cart = cartOpt.get();
        cart.removeItemFromCart(prodId);
        cartRepo.save(cart);
    }
}
