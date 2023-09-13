package com.bway.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bway.springproject.model.Employee;
import com.bway.springproject.service.DepartmentService;
import com.bway.springproject.service.EmployeeService;
import com.bway.springproject.utils.EmployeeExcelView;
import com.bway.springproject.utils.EmployeePdfView;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private DepartmentService deptService;
	
	@GetMapping("/add")
	public String getEmployee(Model model) {
		
		model.addAttribute("deptList", deptService.getAllDepts());
		
		return "EmployeeForm";
	}
	
	@PostMapping("/add")
	public String postEmployee(@ModelAttribute Employee employee) {
		
		empService.addEmp(employee);
		
		return "redirect:/employee/add";
	}
	
	@GetMapping("/list")
	public String getAll(Model model) {
		model.addAttribute("empList", empService.getAllEmps());
		return "EmployeeListForm";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam Long id, Model model) {
		
		model.addAttribute("mEmployee", empService.getEmpById(id));
		model.addAttribute("deptList", deptService.getAllDepts());
		
		return "EmployeeEditForm";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute Employee employee) {
		
		empService.updateEmp(employee);
		return "redirect:/employee/list";
	}
	
	@GetMapping("/view")
	public String view(@RequestParam Long id, Model model) {
		
		model.addAttribute("mEmployee", empService.getEmpById(id));
		
		return "EmployeeViewForm";
		
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam Long id) {
		empService.deleteEmp(id);
		return "redirect:/employee/list";
	}
	
	@GetMapping("/excel")
	public ModelAndView excelView() {
		
		ModelAndView mv = new ModelAndView();
		mv.setView(new EmployeeExcelView());
		mv.addObject("list", empService.getAllEmps());
		
		return mv;
	}
	
	@GetMapping("/pdf")
	public ModelAndView pdfView() {
		
		ModelAndView mv = new ModelAndView();
		mv.setView(new EmployeePdfView());
		mv.addObject("list", empService.getAllEmps());
		
		return mv;
	}
	

}
