package com.SpringBootJasperReportApplication.service;

import com.SpringBootJasperReportApplication.entity.Employee;
import com.SpringBootJasperReportApplication.repository.EmployeeRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private EmployeeRepository repository;

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {

        String path="C:/Users/uruj.fatima/Documents";
        List<Employee> employee=repository.findAll(); //we get list of employee object, now map this list to employee1.jrxml
        //Load file and compile it
        File file= ResourceUtils.getFile("classpath:employeePie.jrxml");  //Resource utils to get it from resource folder
        //To compile we need to use jasper report
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
        //Now we need to get the employees and map to the report so there is a class
        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(employee);

        Map<String,Object> parameters=new HashMap<>();
        parameters.put("createdBy","Uruj Fatima");
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,parameters,dataSource);
        //datasource contains list of employee object
        //in parameter we can add some kind of comment
        //now check report format if it is html,we will generate html report,if it is pdf we will generate pdf report
        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"/employeePie.html");
        }
        if(reportFormat.equalsIgnoreCase("pdf")){

            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"/employeePie.pdf");
        }

        return "report generated in path : "+ path;

    }
}
