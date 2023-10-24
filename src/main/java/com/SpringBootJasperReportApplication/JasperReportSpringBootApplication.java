package com.SpringBootJasperReportApplication;

import com.SpringBootJasperReportApplication.entity.Employee;
import com.SpringBootJasperReportApplication.repository.EmployeeRepository;
import com.SpringBootJasperReportApplication.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@SpringBootApplication
@RestController
public class JasperReportSpringBootApplication {

	@Autowired
	private ReportService service;

	@Autowired
	private EmployeeRepository repository;

	@GetMapping("/getEmployee")
	public List<Employee> getEmployee(){
		return repository.findAll();
	}

	@GetMapping("/report/{format}")
	public String generateReport(@PathVariable String format) throws JRException, FileNotFoundException {
		return service.exportReport(format);
	}

	public static void main(String[] args) {
		SpringApplication.run(JasperReportSpringBootApplication.class, args);
	}

}
