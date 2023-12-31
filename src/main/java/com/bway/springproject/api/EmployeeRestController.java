package com.bway.springproject.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bway.springproject.model.Employee;
import com.bway.springproject.model.Product;
import com.bway.springproject.repository.ProductRepository;
import com.bway.springproject.service.EmployeeService;

@RestController
@CrossOrigin
public class EmployeeRestController {

	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private ProductRepository prodRepo;
	
	@GetMapping("/api/emp/list")
	public List<Employee> getAll() {
		
		return empService.getAllEmps();
	}
	
	@GetMapping("/api/emp/{id}")
	public Employee getOneEmp(@PathVariable Long id) {
		
		return empService.getEmpById(id);
	}
	
	@PostMapping("api/emp/add")
	public String add(@RequestBody Employee emp) {
		
		empService.addEmp(emp);
		
		return "success";
	}
	
	@PutMapping("api/emp/update")
	public String update(@RequestBody Employee emp) {
		
		empService.updateEmp(emp);
		
		return "update success";
	}
	
	@DeleteMapping("/api/emp/delete/{id}")
	public String delete(@PathVariable Long id) {
		
		empService.deleteEmp(id);
		
		return "delete success";
	}
	
	@GetMapping("/api/emp/j2o")
	public String jsonToObjectMapping() {
		
		RestTemplate temp = new RestTemplate();
		Employee e = temp.getForObject("http://localhost/api/emp/1", Employee.class);
		
		return "FirstName = "+e.getFname();
	}
	
	@GetMapping("/api/emp/ja2oa")
	public String jarrayToObjArray() {
		
		RestTemplate temp = new RestTemplate();
		Employee[] elist = temp.getForObject("http://localhost/api/emp/list", Employee[].class);
		
		return "FirstName = "+elist[0].getFname();
	}
	
	@GetMapping("/api/emp/load")
	public String loadProductData() {
		
		RestTemplate temp = new RestTemplate();
		Product[] plist = temp.getForObject("https://fakestoreapi.com/products", Product[].class);
		
		prodRepo.saveAll(List.of(plist));
		
		return "success";
	}
	
}
