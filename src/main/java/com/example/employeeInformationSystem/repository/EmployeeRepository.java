package com.example.employeeInformationSystem.repository;


import java.time.LocalDate;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employeeInformationSystem.entity.Employee;
@Repository
public  interface EmployeeRepository extends JpaRepository<Employee,Integer>{

    List<Employee> findByJobId(int jobId);

    List<Employee> findByDepartmentId(int deptId);
    List<Employee> findByNameContainingIgnoreCase(String name);
    List<Employee> findBySalaryBetween(double min,double max);
    List<Employee> findByJoinDateBefore(LocalDate date);

    Employee findAllById(int empId);
    

} 
