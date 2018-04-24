package com.scp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scp.model.Employee;
import com.scp.repository.EmployeeRepository;

@Service
public class EmployeeDao {
	 
	
	@Autowired
	EmployeeRepository emprespository;
	
	
	 List<Employee> users=new ArrayList<>();
	     
	
	
	public Employee save(Employee emp){
		return emprespository.save(emp);
		
	}
	
	public List<Employee> findAll()
	{
		return emprespository.findAll();
	}
	
	public Employee findOne(long empid){
		return emprespository.findOne(empid);	
	}

	public void delete(Employee employee){
		emprespository.delete(employee);
	}
	public boolean isUserExist(Employee emp) {
	        return findByName(emp.getEmpName())!=null;
	    }


	 public Employee findByName(String name) {
	        for(Employee emp : users){
	            if(emp.getEmpName().equalsIgnoreCase(name)){
	                return emp;
	            }
	        }
	        return null;
	    }
	    

}