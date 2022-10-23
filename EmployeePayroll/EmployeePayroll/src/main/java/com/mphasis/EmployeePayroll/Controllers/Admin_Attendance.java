package com.mphasis.EmployeePayroll.Controllers;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.mphasis.EmployeePayroll.Repositories.AdminRepository;
import com.mphasis.EmployeePayroll.Repositories.AttendanceRepository;
import com.mphasis.EmployeePayroll.Services.AdminService;
import com.mphasis.EmployeePayroll.models.Employee;
import com.mphasis.EmployeePayroll.models.EmployeeAttendance;
import com.mphasis.EmployeePayroll.models.EmployeeSchedule;

@RestController
@RequestMapping("/Admin_attendance")
public class Admin_Attendance {
	
	@Autowired
	AdminRepository adminrepo;
	
	@Autowired
	AttendanceRepository attendrepo;
	
	 AdminService adminserv;
		
	public Admin_Attendance(AdminService service) {
		super();
		this.adminserv = service;
	}
	
	//Get attendance of employee by ID
	@GetMapping("/{id}")
	public ResponseEntity<Set<EmployeeAttendance>> EmployeeById(@PathVariable("id") Long id){
				
				Optional<Employee> tempemployees= adminrepo.findById(id);
				Employee employee=tempemployees.get();
				
				 return new ResponseEntity<>(employee.getAttendances(), HttpStatus.OK);
	}
			
	//create attendance for employee
	@PostMapping("/create/{empid}")
	public ResponseEntity<EmployeeAttendance> addAttendance(@RequestBody EmployeeAttendance attendance,
			@PathVariable("empid") Long id){
		Optional<Employee> tempemployees= adminrepo.findById(id);
		Employee employee=tempemployees.get();
		attendrepo.save(attendance);
		employee.addAttendance(attendance);
		adminrepo.save(employee);
		
		return new ResponseEntity<>(attendance, HttpStatus.OK);
		
	}

	//update attendance schedule
	@PutMapping("/update/{empid}")
	public ResponseEntity<EmployeeAttendance> editAttendance(@RequestBody EmployeeAttendance attendance,
			@PathVariable("empid") Long id){
		Optional<Employee> tempemployees= adminrepo.findById(id);
		Employee employee=tempemployees.get();
		attendrepo.save(attendance);
		//employee.addSchedule(schedule);
		adminrepo.save(employee);
		
		return new ResponseEntity<>(attendance, HttpStatus.OK);
		
	}
	//delete employee schedule
	@DeleteMapping("/delete/{empid}/{aid}")
	public ResponseEntity<?> deleteAttendance(//@RequestBody EmployeeSalary salary,
			@PathVariable("aid") Long aid,
			@PathVariable("empid") Long eid){
		Optional<Employee> tempemployees= adminrepo.findById(eid);
		Employee employee=tempemployees.get();
		Optional<EmployeeAttendance> tempattend= attendrepo.findById(aid);
		EmployeeAttendance attend=tempattend.get();
		employee.removeAttendance(attend);
		adminrepo.save(employee);
		attendrepo.deleteById(aid);//
		//salaryrepo.delete(salary);
		adminrepo.save(employee);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

}
