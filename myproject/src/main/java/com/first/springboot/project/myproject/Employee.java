package com.first.springboot.project.myproject;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.opencsv.bean.CsvBindByName;

@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Employee {
	@Id
	@GeneratedValue
	private int employeeId;
	@CsvBindByName
	private String employeeName;
	@CsvBindByName
	private String title;
	@CsvBindByName
	private String businessUnit;
	@CsvBindByName
	private String place;
	@CsvBindByName
	private String supervisor;
	@CsvBindByName
	private String competensies;
	@CsvBindByName
	private Integer salary;
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBusinessUnit() {
		return businessUnit;
	}
	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	public String getCompetensies() {
		return competensies;
	}
	public void setCompetensies(String competensies) {
		this.competensies = competensies;
	}
	
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", title=" + title
				+ ", businessUnit=" + businessUnit + ", place=" + place + ", supervisor=" + supervisor
				+ ", competensies=" + competensies + ", salary=" + salary + "]";
	}

}
