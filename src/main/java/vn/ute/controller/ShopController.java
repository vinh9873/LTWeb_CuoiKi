package vn.ute.controller;

import java.util.List;

import vn.ute.entity.Shop;
import vn.ute.service.ShopService;
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
@RequestMapping("/shops")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @PostMapping("/create")
    public String createShop(Shop shop, BindingResult binding) {
    	if (binding.hasErrors()) {
    		return "error";
    	}
    	shopService.createShop(shop);	
        return "redirect:/shops";
    }

    @GetMapping("/create")
    public String pageCreateShop(Shop shop, Model model) {
        return "shop"; 
    }
    
    @GetMapping
    public String getAllShop(@RequestParam(value = "search", required = false) String search, Model m) {       
        List<Shop> shops = shopService.findByNameContaining(search); 
        m.addAttribute("shops", shops);        
        return "/shops";
    }

    @GetMapping("/{id}")
    public String getShopById(@PathVariable int id, Model m) {
        var shop =  shopService.getShopById(id);
        m.addAttribute("shop", shop);
        return "shop";
    }

    @PostMapping("/{id}")
    public String updateShop(@PathVariable int id, Shop updatedShop, Model m) {
        var shop = shopService.updateShop(updatedShop);
        m.addAttribute("shop", shop);
        return "redirect:/shops";
    }
    
    @PostMapping("/{id}/delete")
    public String deleteShopById(@PathVariable Integer id, Model m) {
        shopService.deleteShopById(id);
        return "redirect:/shops"; 
    }

}