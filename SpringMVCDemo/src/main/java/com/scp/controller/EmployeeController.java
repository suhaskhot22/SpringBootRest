package com.scp.controller;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.scp.dao.EmployeeDao;
import com.scp.model.Employee;
import com.scp.util.CustomErrorType;
import com.scp.exception.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeDao empdao;
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@GetMapping("/list")
	public ResponseEntity<List<Employee>> getAllEmployee() {
		List<Employee> emp = empdao.findAll();
		if (emp.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Employee>>(emp, HttpStatus.OK);
	}

	@PostMapping("/empl")
	public ResponseEntity<?> createEmployee(@RequestBody Employee emp, UriComponentsBuilder ucBuilder) {
		// logger.info("Creating User : {}", emp);

		List<Employee> employeeList = empdao.findAll();
		for (Employee employee : employeeList) {

			if (emp.getEmpName().equals(employee.getEmpName())) {
				// logger.error("Unable to create. A User with name {} already
				// exist", emp.getEmpName());
				return new ResponseEntity(
						new ApiError(HttpStatus.CONFLICT, "Already exists",
								new Throwable(
										"Unable to create. A User with name " + emp.getEmpName() + " already exist")),
						HttpStatus.CONFLICT);
			}
		}
		empdao.save(emp);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/employee/empl/{id}").buildAndExpand(emp.getEmpId()).toUri());
		return new ResponseEntity(new ApiError(HttpStatus.CREATED, "Record Created",
				new Throwable(" A User with name " + emp.getEmpName() + " Created")), HttpStatus.CREATED);
	}

	@GetMapping("/list/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable("id") long empId) {

		// logger.info("Fetching User with id {}", empId);
		List<Employee> employeeList = empdao.findAll();
		for (Employee employee : employeeList) {

			if (empId == (employee.getEmpId())) {
				// logger.error("User with id {} not found.", empId);
				return new ResponseEntity<Employee>(employee, HttpStatus.OK);
			}
		}

		return new ResponseEntity(new ApiError(HttpStatus.NOT_FOUND, "Record Not found",
				new Throwable(" A User with name " + empId + " Not Found")), HttpStatus.NOT_FOUND);

	}

	@PutMapping("/empl/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable("id") long empId, @RequestBody Employee empdetails) {
		// logger.info("Updating User with id {}", empid);

		List<Employee> employeeList = empdao.findAll();
		for (Employee employee : employeeList) {

			if (empId == (employee.getEmpId())) {

				employee.setEmpName(empdetails.getEmpName());
				employee.setEmpAddress(empdetails.getEmpAddress());

				empdao.save(employee);
				return new ResponseEntity<Employee>(employee, HttpStatus.OK);
			}
		}
		return new ResponseEntity(new ApiError(HttpStatus.NOT_FOUND, "Record Not Found",
				new Throwable(" A User with name " + empId + " Not Found")), HttpStatus.NOT_FOUND);

	}

	@DeleteMapping("empl/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") long empId) {
		

		List<Employee> employeeList = empdao.findAll();
		for (Employee employee : employeeList) {

			if (empId == (employee.getEmpId())) {
				
				empdao.delete(employee);
				return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		}
		return new ResponseEntity(
				new ApiError(HttpStatus.NOT_FOUND, "Record Not Found",
						new Throwable(" A User with name " + empId + " Not Found")),
				HttpStatus.NOT_FOUND);

	}

}
