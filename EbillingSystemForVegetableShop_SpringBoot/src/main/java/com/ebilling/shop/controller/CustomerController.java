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

import com.ebilling.shop.entity.Customer;
import com.ebilling.shop.entity.Shopowner;
import com.ebilling.shop.exception.ResourceNotFoundException;
import com.ebilling.shop.repository.CustomerRepository;
import com.ebilling.shop.repository.ShopOwnerRepository;

@RestController
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private ShopOwnerRepository shopownerRepo;

	//get all customer
	@GetMapping("/getAllCustomer")
	public List<Customer> getAllCustomer(){
		return customerRepo.findAll();
	}
	
	//get all customer of a shop
	@GetMapping("/getAll/{shopId}")
	public List<Customer> getAllCustomerOfShop(@PathVariable("shopId") int id){
		return customerRepo.findAllByShopownerId(id);
	}
	
	//Get One Customer By Id
	@GetMapping("/getCustomer/{id}")
	public Customer getCustomerById(@PathVariable("id") int id) {
		Optional<Customer> customer  =  customerRepo.findById(id);
		if(customer.isPresent()) {
			return customer.get();
		}
		else {
			throw new ResourceNotFoundException("Customer is not found with id : "+id);
		}
	}
	
	//get shopowner by name
	@GetMapping("/getCustomer/byName/{name}")
	public List<Customer> getCustomerByName(@PathVariable("name") String name) {
		List<Customer> customer = customerRepo.findAllByName(name);
		if(customer.isEmpty()) {
			throw new ResourceNotFoundException("customer name is not found "+name);
		}
		return customer;			
	}
	
	//create customer
	@PostMapping("saveCustomer/{id}")
	public Customer saveCustomer(@RequestBody Customer customer, @PathVariable("id") int id) {
		Optional<Shopowner> shopowner = shopownerRepo.findById(id);
		if(shopowner.isEmpty()) {
			throw new ResourceNotFoundException("Shopowner is not found with id : "+id);
		}
		customer.setShopowner(shopowner.get());			
		return customerRepo.save(customer);
		
	}
	
	//update
	@PutMapping("/updateCustomer")
	public Customer updateCustomer(@RequestBody Customer customer) {
		return customerRepo.save(customer);
	}
	
	//delete
	@DeleteMapping("/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable("id") int id) {
	Optional<Customer> customer = customerRepo.findById(id);
		if(customer.isPresent()) {
			customerRepo.delete(customer.get());
			return "customer deleted succesfully";
		}
		else {
			throw new ResourceNotFoundException("Customer is not found with id : "+id);
		}
	}
	
}
