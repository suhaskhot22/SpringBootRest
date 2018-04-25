package com.scp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scp.exception.ApiError;
import com.scp.model.Employee;
import com.scp.repository.EmployeeRepository;

@Service
public class EmployeeDao {

	@Autowired
	EmployeeRepository emprespository;

	List<Employee> users = null;

	public ResponseEntity<ApiError> save(Employee emp) {

		if (isUserExist(emp)) {
			return new ResponseEntity<ApiError>(
					new ApiError(HttpStatus.CONFLICT, "User Already Exists", new Throwable("user already Exists")),
					HttpStatus.CONFLICT);
		}

		emprespository.save(emp);

		return new ResponseEntity<ApiError>(new ApiError(HttpStatus.CREATED, "Record Created",
				new Throwable(" A User with name " + emp.getEmpName() + " Created")), HttpStatus.CREATED);

	}

	public ResponseEntity<List<Employee>> findAll() {
		List<Employee> empList = emprespository.findAll();

		if (empList.isEmpty()) {

			return new ResponseEntity(
					new ApiError(HttpStatus.NO_CONTENT, "List is empty", 
							new Throwable("list is empty")), HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}

		return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);
	}

	public ResponseEntity<?> update(long empId, Employee empdetails) {

		Employee employee = emprespository.findOne(empId);

		if (emprespository.exists(empId)) {

			employee.setEmpName(empdetails.getEmpName());
			employee.setEmpAddress(empdetails.getEmpAddress());

			emprespository.save(employee);
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		}

		return new ResponseEntity(new ApiError(HttpStatus.NOT_FOUND, "Record Not Found",
				new Throwable(" A User with name " + empId + " Not Found")), HttpStatus.NOT_FOUND);

	}

	public ResponseEntity<?> delete(long empId) {

		Employee employee = emprespository.findOne(empId);

		if (emprespository.exists(empId)) {

			emprespository.delete(employee);
			return new ResponseEntity(HttpStatus.OK);
		}

		return new ResponseEntity(new ApiError(HttpStatus.NOT_FOUND, "Record Not Found",
				new Throwable(" A User with name " + empId + " Not Found")), HttpStatus.NOT_FOUND);
	}

	public Employee findOne(long empid) {
		return emprespository.findOne(empid);
	}

	public boolean isUserExist(Employee emp) {
		return findByName(emp.getEmpName()) != null;
	}

	public Employee findByName(String name) {
		users = emprespository.findAll();

		for (Employee emp : users) {
			if (emp.getEmpName().equalsIgnoreCase(name)) {
				return emp;
			}
		}
		return null;
	}

	/*
	 * @Autowired EmployeeRepository emprespository;
	 * 
	 * 
	 * List<Employee> users=new ArrayList<>();
	 * 
	 * 
	 * 
	 * public Employee save(Employee emp){ return emprespository.save(emp);
	 * 
	 * }
	 * 
	 * public List<Employee> findAll() { return emprespository.findAll(); }
	 * 
	 * public Employee findOne(long empid){ return
	 * emprespository.findOne(empid); }
	 * 
	 * public void delete(Employee employee){ emprespository.delete(employee); }
	 * public boolean isUserExist(Employee emp) { return
	 * findByName(emp.getEmpName())!=null; }
	 * 
	 * 
	 * public Employee findByName(String name) { for(Employee emp : users){
	 * if(emp.getEmpName().equalsIgnoreCase(name)){ return emp; } } return null;
	 * }
	 */

}