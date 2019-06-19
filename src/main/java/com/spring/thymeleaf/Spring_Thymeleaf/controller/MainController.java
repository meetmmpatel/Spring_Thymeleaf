package com.spring.thymeleaf.Spring_Thymeleaf.controller;

import com.spring.thymeleaf.Spring_Thymeleaf.model.Employee;
import com.spring.thymeleaf.Spring_Thymeleaf.model.EmployeeForm;
import com.spring.thymeleaf.Spring_Thymeleaf.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
  
  
  // Inject via application.properties
  @Value("${welcome.message}")
  private String message;
  
  @Value("${error.message}")
  private String errorMessage;
  
  @Autowired
  private EmployeeRepository repository;
  
  @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
  public String index(Model model) {
	
	model.addAttribute("message", message);
	
	return "index";
  }
  
  @RequestMapping(value = {"/employeeList"}, method = RequestMethod.GET)
  public String personList(Model model) {
	
	List<Employee> list = new ArrayList<>();
	list = repository.findAll();
	
	model.addAttribute("employees", list);
	
	return "employeeList";
  }
  
  @RequestMapping(value = {"/addEmployee"}, method = RequestMethod.GET)
  public String showAddPersonPage(Model model) {
	
	EmployeeForm employeeForm = new EmployeeForm();
	model.addAttribute("employeeForm", employeeForm);
	
	return "addEmployee";
  }
  
  @RequestMapping(value = {"/addEmployee"}, method = RequestMethod.POST)
  public String savePerson(Model model, //
						   @ModelAttribute("employeeForm") EmployeeForm employeeForm) {
	
	String firstName = employeeForm.getFirstName();
	String lastName = employeeForm.getLastName();
	
	if (firstName != null && firstName.length() > 0 //
			&& lastName != null && lastName.length() > 0) {
	  Employee newEmployee = new Employee(firstName, lastName);
	  
	  repository.save(newEmployee);
	  
	  return "redirect:/employeeList";
	}
	
	model.addAttribute("errorMessage", errorMessage);
	return "addEmployee";
  }
  
}
