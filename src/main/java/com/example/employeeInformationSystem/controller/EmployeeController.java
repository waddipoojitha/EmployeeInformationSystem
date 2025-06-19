package com.example.employeeInformationSystem.controller;


import java.util.List;

import com.example.employeeInformationSystem.service.JobHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeInformationSystem.dto.EmployeeDTO;
import com.example.employeeInformationSystem.dto.JobHistoryDTO;

import com.example.employeeInformationSystem.entity.Employee;
import com.example.employeeInformationSystem.service.EmployeeService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final JobHistoryService jobHistoryService;

    @Autowired 
    private EmployeeService empService;

    EmployeeController( JobHistoryService jobHistoryService) {
        this.jobHistoryService = jobHistoryService;
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        return empService.createEmployee(employee);
    }

    @GetMapping("/employees")
    public List<EmployeeDTO> getEmployees(){

        return empService.getEmployees();
    }

    @GetMapping("/employees/byJobId/{jobId}")
    public List<EmployeeDTO> getEmployeesByJob(@PathVariable int jobId)
    {
        return empService.getEmployeesByJob(jobId);
    }

    @GetMapping("/employees/byDeptId/{deptId}")
    public List<EmployeeDTO> getEmployeeByDept(@PathVariable int deptId){
        return empService.getEmployeesByDepartment(deptId);
    }

    @GetMapping("/employees/search/{name}")
    public List<EmployeeDTO> searchEmployeesByName(@PathVariable String name){
        return empService.getEmployeesByName(name);
    }

    @GetMapping("/employees/salaryRange/{min}/{max}")
    public List<EmployeeDTO> getEmplyoeesBySalaryRange(@PathVariable double min,@PathVariable double max){
        return empService.getEmployeesBySalaryRange(min, max);
    }

    @GetMapping("/employees/experience/{years}")
    public List<EmployeeDTO> getEmployeesByExperience(@PathVariable int years){
        
        return empService.getEmployeesByExperience(years);

    }

    @GetMapping("/employees/{empId}/job-history")
    public List<JobHistoryDTO> getJobHistory(@PathVariable Long empId) {
        return jobHistoryService.findByEmployeeId(empId);
    }

    @PutMapping("/employees/{empId}/salary")
    public ResponseEntity<String> updateSalary(@PathVariable int empId, @RequestParam Double newSalary) {
        Employee employee = empService.findById(empId); 

        if (employee != null) {
            employee.setSalary(newSalary);
            empService.save(employee);
            return ResponseEntity.ok("Salary updated.");
        } else {
            return ResponseEntity.status(404).body("Employee not found.");
        }
    }
}