package com.example.employeeInformationSystem.service;

import com.example.employeeInformationSystem.dto.EmployeeDTO;
import com.example.employeeInformationSystem.entity.Department;
import com.example.employeeInformationSystem.entity.Employee;
import com.example.employeeInformationSystem.entity.Job;
import com.example.employeeInformationSystem.exception.ResourceNotFoundException;
import com.example.employeeInformationSystem.repository.EmployeeRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepo;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setName("Alice");
        employee.setSalary(5000.0);

        Mockito.when(employeeRepo.save(employee)).thenReturn(employee);

        Employee saved = employeeService.createEmployee(employee);
        Assertions.assertNotNull(saved);
        Assertions.assertEquals("Alice", saved.getName());
    }

    @Test
    void testGetEmployees() {
        Employee employee = createTestEmployee();
        Mockito.when(employeeRepo.findAll()).thenReturn(List.of(employee));

        List<EmployeeDTO> result = employeeService.getEmployees();
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("John Doe", result.get(0).getName());
    }

    @Test
    void testGetEmployeesByJob() {
        Employee employee = createTestEmployee();
        Mockito.when(employeeRepo.findByJobId(1)).thenReturn(List.of(employee));

        List<EmployeeDTO> result = employeeService.getEmployeesByJob(1);
        Assertions.assertEquals("Developer", result.get(0).getJobTitle());
    }

    @Test
    void testGetEmployeesByDepartment() {
        Employee employee = createTestEmployee();
        Mockito.when(employeeRepo.findByDepartmentId(1)).thenReturn(List.of(employee));

        List<EmployeeDTO> result = employeeService.getEmployeesByDepartment(1);
        Assertions.assertEquals("IT", result.get(0).getDepartmentName());
    }

    @Test
    void testGetEmployeesByName() {
        Employee employee = createTestEmployee();
        Mockito.when(employeeRepo.findByNameContainingIgnoreCase("john")).thenReturn(List.of(employee));

        List<EmployeeDTO> result = employeeService.getEmployeesByName("john");
        Assertions.assertEquals("John Doe", result.get(0).getName());
    }

    @Test
    void testGetEmployeesBySalaryRange() {
        Employee employee = createTestEmployee();
        Mockito.when(employeeRepo.findBySalaryBetween(3000.0, 6000.0)).thenReturn(List.of(employee));

        List<EmployeeDTO> result = employeeService.getEmployeesBySalaryRange(3000.0, 6000.0);
        Assertions.assertTrue(result.size() > 0);
    }

    @Test
    void testGetEmployeesByExperience() {
        Employee employee = createTestEmployee();
        employee.setJoinDate(LocalDate.now().minusYears(5));
        Mockito.when(employeeRepo.findByJoinDateBefore(Mockito.any())).thenReturn(List.of(employee));

        List<EmployeeDTO> result = employeeService.getEmployeesByExperience(3);
        Assertions.assertEquals("John Doe", result.get(0).getName());
    }

    @Test
    void testFindById_found() {
        Employee employee = createTestEmployee();
        Mockito.when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));

        Employee result = employeeService.findById(1);
        Assertions.assertEquals("John Doe", result.getName());
    }

    @Test
    void testFindById_notFound() {
        Mockito.when(employeeRepo.findById(99)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.findById(99);
        });
    }

    @Test
    void testSave() {
        Employee employee = createTestEmployee();
        employeeService.save(employee);
        Mockito.verify(employeeRepo, Mockito.times(1)).save(employee);
    }

    private Employee createTestEmployee() {
        Job job = new Job();
        job.setId(1);
        job.setTitle("Developer");

        Department dept = new Department();
        dept.setId(1);
        dept.setName("IT");

        Employee emp = new Employee();
        emp.setId(1);
        emp.setName("John Doe");
        emp.setSalary(5000.0);
        emp.setJoinDate(LocalDate.of(2020, 1, 1));
        emp.setJob(job);
        emp.setDepartment(dept);
        return emp;
    }
}
