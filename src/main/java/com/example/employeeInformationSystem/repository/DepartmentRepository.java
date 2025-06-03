package com.example.employeeInformationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employeeInformationSystem.entity.Department;
@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer>{
    
}
