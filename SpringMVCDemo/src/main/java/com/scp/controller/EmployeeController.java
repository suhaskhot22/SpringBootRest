package com.scp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scp.dao.EmployeeDao;
import com.scp.model.Employee;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeDao empdao;

	@GetMapping("/list")
	public ResponseEntity<List<Employee>> getAllEmployee() {
		return empdao.findAll();

	}

	@PostMapping("/empl")
	public ResponseEntity<?> createEmployee(@RequestBody Employee emp) {

		return empdao.save(emp);

	}

	@PutMapping("/empl/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable("id") long empId, @RequestBody Employee empdetails)

	{
		return empdao.update(empId, empdetails);

	}

	@DeleteMapping("empl/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") long empId) {
		return empdao.delete(empId);

	}

}
