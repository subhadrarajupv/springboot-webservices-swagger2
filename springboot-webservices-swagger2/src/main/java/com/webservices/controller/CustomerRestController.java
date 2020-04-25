package com.webservices.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webservices.dao.CustomerRepository;
import com.webservices.entity.Customer;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {

	@Autowired
	private CustomerRepository repo;
	
	@GetMapping
	public Iterable<Customer> getAllCustomers(
			@RequestParam(name="_page", defaultValue="1") Integer pageNum, 
			@RequestParam(name="_limit", defaultValue="50") Integer pageSize) {
		Pageable p = PageRequest.of(pageNum-1, pageSize);//pageNum-1 because it starts with 0
		return repo.findAll(p);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCustomer(@PathVariable Integer id) {
		try {
			Customer c1 = repo.findById(id).get();
			return ResponseEntity.ok(c1);
		} catch (Exception e) {
			return ResponseEntity.status(404).body(null);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) {
		repo.save(customer);
		return ResponseEntity.ok(customer);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable Integer id, 
			@RequestBody Customer customer){
		customer.setId(id);
		repo.save(customer);
		return ResponseEntity.ok(customer);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable Integer id){
		try {
			Customer c1 = repo.findById(id).get();
			repo.delete(c1);
			return ResponseEntity.ok(c1);
		} catch (Exception e) {
			return ResponseEntity.status(404).body(null);
		}
	}
}