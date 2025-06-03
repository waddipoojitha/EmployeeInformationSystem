package com.example.employeeInformationSystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeeInformationSystem.dto.DepartmentDTO;

import com.example.employeeInformationSystem.entity.Department;
import com.example.employeeInformationSystem.entity.Employee;

import com.example.employeeInformationSystem.repository.DepartmentRepository;
import com.example.employeeInformationSystem.repository.EmployeeRepository;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    public List<DepartmentDTO> getDepartments(){
        List<Department> departments=departmentRepo.findAll();
        return departments.stream().map(dept->new DepartmentDTO(
            dept.getId(),
            dept.getName(),
            dept.getHod()!=null?dept.getHod().getId():null
        )).collect(Collectors.toList());
    }
    

    public Department createDepartment(Department department){
        return departmentRepo.save(department);
    }

    public void setHod(int deptId,int empId){
        Department department=departmentRepo.findById(deptId).orElse(null);
        Employee employee=employeeRepo.findById(empId).orElse(null);

        if(department !=null && employee!=null){
            department.setHod(employee);
            departmentRepo.save(department);
        }
    }
}

