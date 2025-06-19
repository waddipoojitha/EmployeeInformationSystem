package com.example.employeeInformationSystem.service;


import com.example.employeeInformationSystem.dto.DepartmentDTO;
import com.example.employeeInformationSystem.entity.Department;
import com.example.employeeInformationSystem.entity.Employee;
import com.example.employeeInformationSystem.exception.ResourceNotFoundException;
import com.example.employeeInformationSystem.repository.DepartmentRepository;
import com.example.employeeInformationSystem.repository.EmployeeRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @InjectMocks
    private DepartmentService departmentService;

    @Mock
    private DepartmentRepository departmentRepo;

    @Mock
    private EmployeeRepository employeeRepo;

    @Test
    void testGetDepartments() {
    
    Department dept1 = new Department();
    dept1.setId(1);
    dept1.setName("HR");

    Employee hod = new Employee();
    Integer hodId = 1; 
    hod.setId(hodId);  

    dept1.setHod(hod);

    Department dept2 = new Department();
    dept2.setId(2);
    dept2.setName("Finance");
    dept2.setHod(null);

    Mockito.when(departmentRepo.findAll()).thenReturn(Arrays.asList(dept1, dept2));
    List<DepartmentDTO> result = departmentService.getDepartments();

    Assertions.assertEquals(2, result.size());
    Assertions.assertEquals("HR", result.get(0).getName());
    Assertions.assertEquals(1, result.get(0).getHodId());  
    Assertions.assertNull(result.get(1).getHodId());       
}


    @Test
    void testCreateDepartment() {
        Department dept = new Department();
        dept.setId(1);
        dept.setName("Engineering");

        Mockito.when(departmentRepo.save(dept)).thenReturn(dept);

        Department saved = departmentService.createDepartment(dept);

        Assertions.assertEquals("Engineering", saved.getName());
    }

    @Test
    void testSetHod_success() {
        int deptId = 1;
        int empId = 100;

        Department dept = new Department();
        dept.setId(deptId);

        Employee emp = new Employee();
        emp.setId((int) empId);

        Mockito.when(departmentRepo.findById(deptId)).thenReturn(Optional.of(dept));
        Mockito.when(employeeRepo.findById(empId)).thenReturn(Optional.of(emp));
        Mockito.when(departmentRepo.save(dept)).thenReturn(dept);

        departmentService.setHod(deptId, empId);

        Assertions.assertEquals(emp, dept.getHod());
    }

    @Test
    void testSetHod_departmentNotFound() {
        Mockito.when(departmentRepo.findById(999)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            departmentService.setHod(999, 100);
        });
    }

    @Test
    void testSetHod_employeeNotFound() {
        Department dept = new Department();
        dept.setId(1);

        Mockito.when(departmentRepo.findById(1)).thenReturn(Optional.of(dept));
        Mockito.when(employeeRepo.findById(999)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            departmentService.setHod(1, 999);
        });
    }
}