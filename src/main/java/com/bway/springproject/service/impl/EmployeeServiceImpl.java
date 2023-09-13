package com.bway.springproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bway.springproject.model.Employee;
import com.bway.springproject.repository.EmployeeRepository;
import com.bway.springproject.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository empRepo;
	
	@Override
	public void addEmp(Employee employee) {
		empRepo.save(employee);
	}

	@Override
	public void deleteEmp(Long id) {
		empRepo.deleteById(id);
	}

	@Override
	public void updateEmp(Employee employee) {
		empRepo.save(employee);
		
	}

	@Override
	public Employee getEmpById(Long id) {
		
		return empRepo.findById(id).get();
	}

	@Override
	public List<Employee> getAllEmps() {
		
		return empRepo.findAll();
	}

}
