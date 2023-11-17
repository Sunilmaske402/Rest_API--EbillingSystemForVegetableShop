package com.ebilling.shop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebilling.shop.entity.Bill_Order;
import com.ebilling.shop.entity.Customer;
import com.ebilling.shop.entity.OrderedItem;
import com.ebilling.shop.exception.ResourceNotFoundException;
import com.ebilling.shop.repository.BillRepository;
import com.ebilling.shop.repository.CustomerRepository;
import com.ebilling.shop.repository.OrderedItemReposotory;

@RestController
@RequestMapping("/bill")
public class BillController {
	
	@Autowired
	private BillRepository billRepo;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private OrderedItemReposotory orderedItemRepo;
	
	//get all bill 
	@GetMapping("/getAll")
	public List<Bill_Order> getAllBill(){
		return billRepo.findAll();
	}
	

	//get all bill for a customer
	@GetMapping("/getOfCustomer/{custoId}")
	public List<Bill_Order> getAllBillByCustomer(@PathVariable("custoId") int id){
		return billRepo.findByCustomerId(id);
	}

	

	//get a bill by id 
	@GetMapping("/get/{id}")
	public Bill_Order getBillById(@PathVariable("id") int id){
		return billRepo.findById(id).get();
	}
	
	//create bill and making an order
	@PostMapping("/save/{customerId}")
	public Bill_Order saveBill(@RequestBody Bill_Order bill, @PathVariable("customerId") int id) {
		long totalPrice = 0;
		for(OrderedItem item : bill.getOrderedItem()) {
			totalPrice+=(item.getProduct().getPrice())*(item.getQuantity());
		}
		bill.setTotalPrice(totalPrice);
		Optional<Customer> customer = customerRepo.findById(id);
		if(customer.isEmpty()) {
			throw new ResourceNotFoundException("Customer is not found with id : "+id);
		}
		bill.setCustomer(customer.get());		
		return billRepo.save(bill);
	}
	
	//make order
	@PostMapping("/order/save/{billId}")
	public List<OrderedItem> makeOrder(@RequestBody List<OrderedItem> orderedItem, @PathVariable("billId") int id) {
		Optional<Bill_Order> bill = billRepo.findById(id);

		if(bill.isEmpty()) {
				throw new ResourceNotFoundException("Bill is not found with id : "+id);
		}
		
		for(OrderedItem item:orderedItem) {
			item.setBill(bill.get());
		}				
		return orderedItemRepo.saveAll(orderedItem);
	}


}
