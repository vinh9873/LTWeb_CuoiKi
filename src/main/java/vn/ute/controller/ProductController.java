package vn.ute.controller;

import java.util.List;

import vn.ute.entity.Product;
import vn.ute.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public String createProduct(Product product, BindingResult binding) {
    	if (binding.hasErrors()) {
    		return "error";
    	}
    	productService.createProduct(product);	
        return "redirect:/products";
    }

    @GetMapping("/create")
    public String pageCreateProduct(Product product, Model model) {
        return "product"; 
    }
    
    @GetMapping
    public String getAllProducts(@RequestParam(value = "search", required = false) String search, Model m) {       
        List<Product> products = productService.findByNameContaining(search); 
        m.addAttribute("products", products);        
        return "/products";
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable int id, Model m) {
        var product =  productService.getProductById(id);
        m.addAttribute("product", product);
        return "product";
    }

    @PostMapping("/{id}")
    public String updateProduct(@PathVariable int id, Product updatedProduct, Model m) {
        var product = productService.updateProduct(updatedProduct);
        m.addAttribute("product", product);
        return "redirect:/products";
    }
    
    @PostMapping("/{id}/delete")
    public String deleteProductById(@PathVariable Integer id, Model m) {
        productService.deleteProductById(id);
        return "redirect:/products"; 
    }
   
}