package com.first.springboot.project.myproject.repository;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.first.springboot.project.myproject.Employee;
@Repository
public interface EmployeRepository extends JpaRepository<Employee, Integer> {
	
	/*@Query("select u from Employee u where place = ?1")
	ArrayList<Employee> findEmpByPlace(String place);*/
	
	@Query("select u from Employee u where u.place = :place")
	ArrayList<Employee> findEmpByPlace(@Param("place") String place);
	
	@Query("select u from Employee u where u.businessUnit = :businessUnit")
	ArrayList<Employee> findSlaryByBu(@Param("businessUnit") String businessUnit);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update Employee u set u.salary =:salary where u.place =:place")
	void updateSalaryByPlace(@Param("salary") Integer salary, @Param("place") String place);
	
	@Query("select u from Employee u where u.supervisor = :supervisor")
	ArrayList<Employee> findSalaryBySupervisor(@Param("supervisor") String supervisor);
	
	@Query("select max(u.salary) from Employee u where u.title = :title")
	Integer findMaxSalaryForTitle(@Param("title") String title);
	
	@Query("select min(u.salary) from Employee u where u.title = :title")
	Integer findMinSalaryForTitle(@Param("title") String title);
}
