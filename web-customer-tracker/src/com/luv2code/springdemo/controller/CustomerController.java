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

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	
	
	@GetMapping("/list")
	public String listCustomer(Model model){
		
		List<Customer> customers = customerService.getCustomers();//delegate calls to service
		
		model.addAttribute("customers",customers);
		return "list-customer";//jsp file
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAddCustomer(Model model){
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		
		return "customer-form";
	}
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int id){
		
		customerService.deleteCustomer(id);
		
		return "redirect:/customer/list";
		
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer ){
		
		customerService.saveCustomer(customer);
		
		return "redirect:/customer/list";
		
	}
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id,
									Model model){
		
		Customer customer = customerService.getCustomers(id);
		
		model.addAttribute("customer",customer);
		
		return "customer-form";
	}
	
}
