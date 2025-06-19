package com.example.employeeInformationSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeInformationSystem.dto.DepartmentDTO;
import com.example.employeeInformationSystem.entity.Department;

import com.example.employeeInformationSystem.service.DepartmentService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;


    @GetMapping("/departments")
    public List<DepartmentDTO> getDepartments(){
        return departmentService.getDepartments();
    }

    @PostMapping("/departments")
    public void createDepartment(@RequestBody Department department){
        departmentService.createDepartment(department);
    }

    @PutMapping("/departments/{deptId}/hod/{empId}")
    public void setHod(@PathVariable int deptId,@PathVariable int empId){
        departmentService.setHod(deptId,empId);
    }
}