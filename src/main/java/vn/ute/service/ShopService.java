package vn.ute.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import vn.ute.entity.Shop;
import vn.ute.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {
	
	@Autowired
    private ShopRepository shopRepository;

    public Shop createShop(Shop shop) {
        return shopRepository.save(shop);
    }

    public List<Shop> getAllShops() {
        return (List<Shop>) shopRepository.findAll();
    }

    public Shop getShopById(int id) {
        Optional<Shop> Shop = shopRepository.findById(id);
        return Shop.orElse(null); 
    }

	public Shop updateShop(Shop shop) {
		Optional<Shop> ShopOpt = shopRepository.findById(shop.getId());
		if (ShopOpt.isEmpty()) {
			return null;
		}
		Shop Shop = ShopOpt.get();
		Shop.setName(shop.getName());
		Shop.setType(shop.getType());
		return shopRepository.save(shop);
	}

    public void deleteShopById(int id) {
    	shopRepository.deleteById(id);
	}
    
    public List<Shop> findByNameContaining(String name) {
    	if (name == null || name.isBlank()) {
    		var Shops = shopRepository.findAll();
    		return StreamSupport.stream(Shops.spliterator(), false).toList();
    	}
        return shopRepository.findByNameContaining(name);
    }
    
}