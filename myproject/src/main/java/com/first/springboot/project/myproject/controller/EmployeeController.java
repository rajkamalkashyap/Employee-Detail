package com.first.springboot.project.myproject.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.first.springboot.project.myproject.MessageBean;
import com.first.springboot.project.myproject.repository.EmployeRepository;
import com.first.springboot.project.myproject.service.EmployeeService;
import com.first.springboot.project.myproject.Employee;


@RestController
public class EmployeeController {

	@Autowired
	private EmployeRepository employeRepository;
	
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping(path="/hello/{msg}")
	public MessageBean helloWorld(@PathVariable String msg) {
		return new MessageBean(String.format("Hell3o World, %s", msg));
	}
	
	@GetMapping(path="/emp/all")
	public ArrayList<Employee> getAllEmployee() {
		return (ArrayList<Employee>) employeRepository.findAll();
	}
	
	@GetMapping(path="/emp/{id}")
	public Optional<Employee> getEmpDetail(@PathVariable int id) {
		return employeRepository.findById(id);
	}
	
	@GetMapping(path="/emp/place/{place}")
	public ArrayList<Employee> getEmpByPlaceDetail(@PathVariable String place) {
		return employeRepository.findEmpByPlace(place);
	}
	
	@GetMapping(path="/emp/salary/{requestType}/{value}")
	public Integer getSalaryByBu(@PathVariable String requestType,@PathVariable String value) throws Exception {
		ArrayList<Employee> emp=new ArrayList<Employee>();
		if(requestType.equalsIgnoreCase("BU")) {
			emp=employeRepository.findSlaryByBu(value);
		}else if(requestType.equalsIgnoreCase("SUP")) {
			emp=employeRepository.findSalaryBySupervisor(value);
		}else if(requestType.equalsIgnoreCase("PLACE")) {
			emp=employeRepository.findEmpByPlace(value);
		}else {
			throw new Exception("Wrong request Type");
		}
		Integer total = emp.stream().collect(
                Collectors.summingInt(Employee::getSalary));
		return total;
	}
	
	@PutMapping(path="/emp/place/{place}/salary/{percentage}")
	public void updateSalary(@PathVariable String place,@PathVariable Integer percentage) {
		ArrayList<Employee> emp=employeRepository.findEmpByPlace(place);
		emp.forEach( employee ->{
			Integer prevSalary=employee.getSalary();
			Integer  newSalary;
			int percValue = (int)(prevSalary*(percentage/100.0f));
			newSalary=prevSalary+percValue;
			employee.setSalary(newSalary);
			employeRepository.save(employee);
		 });
	}
	
	@GetMapping(path="/emp/salary/range/{title}")
	public String getSalaryRangeByTitle(@PathVariable String title) throws Exception {
		Integer rangeStart= employeRepository.findMinSalaryForTitle(title.trim());
		Integer rangEnd= employeRepository.findMaxSalaryForTitle(title.trim());
		return Integer.toString(rangeStart)+"-"+Integer.toString(rangEnd);
	}
	
	
	@PostMapping(path="emp/file/upload")
	public List<Employee> uploadFile(@RequestPart(value = "file") MultipartFile multiPartFile) throws IOException {
		return employeeService.uploadFile(multiPartFile);
	}
	
}
