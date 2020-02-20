package com.first.springboot.project.myproject.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.first.springboot.project.myproject.repository.EmployeeDAO;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.first.springboot.project.myproject.Employee;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;


@Service
public class EmployeeService {
	@Autowired
	private EmployeeDAO employeeDAO;

	public List<Employee> uploadFile(MultipartFile multipartFile) throws IOException {
		File file = convertMultiPartToFile(multipartFile);
		List<Employee> mandatoryMissedList = new ArrayList<Employee>();
		try (Reader reader = new FileReader(file);) {
			@SuppressWarnings("unchecked")
			CsvToBean<Employee> csvToBean = new CsvToBeanBuilder<Employee>(reader).withType(Employee.class)
					.withIgnoreLeadingWhiteSpace(true).build();
			List<Employee> employeeList = csvToBean.parse();
			Iterator<Employee> employeeListClone = employeeList.iterator();

			while (employeeListClone.hasNext()) {
				Employee student = employeeListClone.next();
				if (student.getEmployeeName() == null || student.getEmployeeName().isEmpty()
						|| student.getTitle() == null || student.getTitle().isEmpty()) {
					mandatoryMissedList.add(student);
					employeeListClone.remove();
				}
			}
			employeeDAO.batchStore(employeeList);
		}
		return mandatoryMissedList;
	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}
	
}
