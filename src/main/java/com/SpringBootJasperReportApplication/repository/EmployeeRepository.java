package com.SpringBootJasperReportApplication.repository;


import com.SpringBootJasperReportApplication.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}