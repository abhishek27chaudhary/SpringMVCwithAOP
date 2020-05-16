package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.dao.CustomerDao;
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	//Inject the customer dao
//	@Autowired
//	private CustomerDao customerDAO;
	
	//Service layer - Removing the above commented code 
	
	@Autowired
	private CustomerService customerService;
	
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		//get the Customer from service
		List<Customer> theCustomers = customerService.getCustomers();
		
		//add the customer to the model
		theModel.addAttribute("customers",theCustomers);
		
		
		return "list-customers";
	}

	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Customer theCustomer = new Customer();
		theModel.addAttribute(theCustomer);
		
		return "customer-form";
	}
	
	//Adding new Customer
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer")Customer theCustomer) {
		
		customerService.saveCustomer(theCustomer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId,
									Model theModel) {
		
		Customer theCustomer = customerService.getCustomers(theId);
		
		//System.out.println(theCustomer.getId()+" "+theCustomer.getFirstName());
		
		theModel.addAttribute(theCustomer);
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		customerService.deleteCustomer(theId);
		return "redirect:/customer/list";
	}
}


