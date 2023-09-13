package com.bway.springproject.service;

import java.util.List;

import com.bway.springproject.model.Department;


public interface DepartmentService {
	
	void addDept(Department dept);
	void deleteDept(int id);
	void updateDept(Department dept);
	Department getDeptById(int id);
	List<Department> getAllDepts();

}
