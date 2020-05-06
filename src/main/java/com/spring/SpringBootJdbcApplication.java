package com.spring;

import com.spring.filter.FilterExample;
import com.spring.model.Employee;
import com.spring.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.servlet.FilterRegistration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
//there are 2 interfaces of spring boot Application Runner and CommandLine Runner
//they both let u execute the code after the springboot application started
public class SpringBootJdbcApplication implements CommandLineRunner {
	/// org.slf4j
	//to get the logger object
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootJdbcApplication.class);
	@Bean
	public FilterRegistrationBean registrationBean() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new FilterExample());
		bean.addUrlPatterns("/employees/*");
		bean.setOrder(-1);
		return bean;
	}
	@Autowired
	//application context used to provide configuration information to the application
	//it implements BeanFactory interface..so it includes all functionalities of
	//BeanFactory and much more
	private ApplicationContext context;
	public static void main(String[] args ) {
//this is the application runner
		SpringApplication.run(SpringBootJdbcApplication.class, args);

		LOGGER.debug("This is a debug message");
		LOGGER.info("This is an info message");
		LOGGER.warn("This is a warn message");
		LOGGER.error("This is an error message");
		LOGGER.trace("this is trace message");
	}
//this is the command line runner
	@Override
	public void run(String... args) throws Exception {
	//	System.out.println(Arrays.toString(context.getBeanDefinitionNames()));

		EmployeeService employeeService = context.getBean(EmployeeService.class);


		Employee emp= new Employee();
		emp.setEmpId("emp");
		emp.setEmpName("emp");

		Employee emp1= new Employee();
		emp1.setEmpId("emp1");
		emp1.setEmpName("emp1");

		Employee emp2= new Employee();
		emp2.setEmpId("emp2");
		emp2.setEmpName("emp2");


		employeeService.insertEmployee(emp);

		List<Employee> employees = new ArrayList<>();
		employees.add(emp1);
		employees.add(emp2);
		employeeService.insertEmployees(employees);

		employeeService.getAllEmployees();

		employeeService.getEmployeeById(emp1.getEmpId());

	}
}
