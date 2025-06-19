package com.example.employeeInformationSystem.service;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeeInformationSystem.dto.EmployeeDTO;
import com.example.employeeInformationSystem.entity.Employee;
import com.example.employeeInformationSystem.exception.ResourceNotFoundException;
import com.example.employeeInformationSystem.repository.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepo;

    public Employee createEmployee(Employee emp){
        return employeeRepo.save(emp);
    }

    public List<EmployeeDTO> getEmployees(){
        List<Employee> employees=employeeRepo.findAll();
        return employees.stream().map(emp->new EmployeeDTO(
            emp.getId(),
            emp.getName(),
            emp.getSalary(),
            emp.getJoinDate(),
            emp.getJob()!=null?emp.getJob().getTitle():null,
            emp.getDepartment()!=null? emp.getDepartment().getName():null
        )).collect(Collectors.toList());
    }

    public List<EmployeeDTO> getEmployeesByJob(int jobId) {
        //return employeeRepo.findByJobId(jobId);
        List<Employee> employees=employeeRepo.findByJobId(jobId);
        return employees.stream().map(emp->new EmployeeDTO(
            emp.getId(),
            emp.getName(),
            emp.getSalary(),
            emp.getJoinDate(),
            emp.getJob()!=null?emp.getJob().getTitle():null,
            emp.getDepartment()!=null? emp.getDepartment().getName():null
        )).collect(Collectors.toList());
        
    }

    public List<EmployeeDTO> getEmployeesByDepartment(int deptId){
        List<Employee> employees=employeeRepo.findByDepartmentId(deptId);
        return employees.stream().map(emp->new EmployeeDTO(
            emp.getId(),
            emp.getName(),
            emp.getSalary(),
            emp.getJoinDate(),
            emp.getJob()!=null?emp.getJob().getTitle():null,
            emp.getDepartment()!=null?emp.getDepartment().getName():null
        )).collect(Collectors.toList());
    }

    public List<EmployeeDTO> getEmployeesByName(String name){
        List<Employee> employees=employeeRepo.findByNameContainingIgnoreCase(name);
        return employees.stream().map(emp->new EmployeeDTO(
            emp.getId(),
            emp.getName(),
            emp.getSalary(),
            emp.getJoinDate(),
            emp.getJob()!=null?emp.getJob().getTitle():null,
            emp.getDepartment()!=null?emp.getDepartment().getName():null
        )).collect(Collectors.toList());
    }

    public List<EmployeeDTO> getEmployeesBySalaryRange(double min,double max){
        List<Employee> employees=employeeRepo.findBySalaryBetween(min, max);
        return employees.stream().map(emp->new EmployeeDTO(
             emp.getId(),
            emp.getName(),
            emp.getSalary(),
            emp.getJoinDate(),
            emp.getJob()!=null?emp.getJob().getTitle():null,
            emp.getDepartment()!=null?emp.getDepartment().getName():null
        )).collect(Collectors.toList());
    }

    public List<EmployeeDTO> getEmployeesByExperience(int years){
        LocalDate beforeDate=LocalDate.now().minusYears(years);
        List<Employee> employees=employeeRepo.findByJoinDateBefore(beforeDate);
        return employees.stream().map(emp->new EmployeeDTO(
            emp.getId(),
            emp.getName(),
            emp.getSalary(),
            emp.getJoinDate(),
            emp.getJob()!=null?emp.getJob().getTitle():null,
            emp.getDepartment()!=null?emp.getDepartment().getName():null
        )).collect(Collectors.toList());

    }

    public Employee findById(int empId) {
    return employeeRepo.findById(empId)
           .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + empId));
    }

    public void save(Employee employee) {
        employeeRepo.save(employee);
    }
}