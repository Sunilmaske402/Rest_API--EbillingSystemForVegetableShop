package com.ebilling.shop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ebilling.shop.entity.Shopowner;
import com.ebilling.shop.exception.ResourceNotFoundException;
import com.ebilling.shop.repository.ShopOwnerRepository;

@RestController
public class ShopownerController {
	
	@Autowired
	private ShopOwnerRepository shopownerRepo;
	
	//get all shopowner
	@GetMapping("/getAllShopowner")
	public List<Shopowner> getAllShopOwner(){
		return shopownerRepo.findAll();
	}
	
	//get shopowne by id
	@GetMapping("/getShopowner/{id}")
	public Shopowner getShopownerById(@PathVariable("id") int id) {
		Optional<Shopowner> shopowner = shopownerRepo.findById(id);
		if(shopowner.isPresent()) {
			
			return shopowner.get();
		}
		else {
			throw new ResourceNotFoundException("Shoponer id is not found "+id);
		}
	}
	
	//get shopowner by name
	@GetMapping("/getShopowner/byName/{name}")
	public List<Shopowner> getShopownerByName(@PathVariable("name") String name) {
		List<Shopowner> shopowner = shopownerRepo.findAllByName(name);
		if(shopowner.isEmpty()) {
			throw new ResourceNotFoundException("Shoponer name is not found "+name);
			
			
		}
		return shopowner;			
	}

	
	//register shopowner
	@PostMapping("/saveShopowner")
	public Shopowner saveShopOwner(@RequestBody Shopowner shopOwner) {
		return shopownerRepo.save(shopOwner);
	}
	
	//update
	@PutMapping("/updateShopowner")
	public Shopowner updateShopOwner(@RequestBody Shopowner shopOwner) {
		return shopownerRepo.save(shopOwner);
	}
	
	//delete
	@DeleteMapping("/deleteShopowner/{id}")
	public String deleteShopowner(@PathVariable("id") int id) {
	Optional<Shopowner> shopowner = shopownerRepo.findById(id);
		if(shopowner.isPresent()) {
			shopownerRepo.delete(shopowner.get());
			return "Shopowner deleted succesfully";
		}
		else {
			throw new ResourceNotFoundException("Shoponer id is not found "+id);
		}
	}
	

}
