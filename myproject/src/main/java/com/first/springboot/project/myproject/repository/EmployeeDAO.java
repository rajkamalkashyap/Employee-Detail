package com.first.springboot.project.myproject.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.first.springboot.project.myproject.Employee;

@Repository
public class EmployeeDAO {
	@Autowired
	private EmployeRepository employeeRepository;

	public void batchStore(List<Employee> employeeList) {
		employeeRepository.saveAll(employeeList);
	}

}
