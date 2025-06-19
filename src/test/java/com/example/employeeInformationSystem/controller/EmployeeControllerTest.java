package com.example.employeeInformationSystem.controller;

import com.example.employeeInformationSystem.dto.EmployeeDTO;
import com.example.employeeInformationSystem.dto.JobHistoryDTO;
import com.example.employeeInformationSystem.entity.Employee;
import com.example.employeeInformationSystem.service.EmployeeService;
import com.example.employeeInformationSystem.service.JobHistoryService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@WithMockUser(username = "admin", roles = {"ADMIN"})
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService empService;

    @MockBean
    private JobHistoryService jobHistoryService;


    @Test
    void testGetEmployees() throws Exception {
        EmployeeDTO emp1 = new EmployeeDTO();
        emp1.setId(1);
        emp1.setName("John");

        List<EmployeeDTO> employees = Arrays.asList(emp1);
        Mockito.when(empService.getEmployees()).thenReturn(employees);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John"));
    }

    @Test
    void testGetEmployeesByJob() throws Exception {
        Mockito.when(empService.getEmployeesByJob(1)).thenReturn(Arrays.asList(new EmployeeDTO()));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/byJobId/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetEmployeesByDepartment() throws Exception {
        Mockito.when(empService.getEmployeesByDepartment(1)).thenReturn(Arrays.asList(new EmployeeDTO()));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/byDeptId/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testSearchEmployeesByName() throws Exception {
        Mockito.when(empService.getEmployeesByName("John")).thenReturn(Arrays.asList(new EmployeeDTO()));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/search/John"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetEmployeesBySalaryRange() throws Exception {
        Mockito.when(empService.getEmployeesBySalaryRange(30000, 60000)).thenReturn(Arrays.asList(new EmployeeDTO()));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/salaryRange/30000/60000"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetEmployeesByExperience() throws Exception {
        Mockito.when(empService.getEmployeesByExperience(5)).thenReturn(Arrays.asList(new EmployeeDTO()));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/experience/5"))
                .andExpect(status().isOk());
    }
    
    @Test
    void testGetJobHistory() throws Exception {
        Mockito.when(jobHistoryService.findByEmployeeId(1L)).thenReturn(Arrays.asList(new JobHistoryDTO()));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/1/job-history"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateSalary_Success() throws Exception {
        Employee emp = new Employee();
        emp.setId(1);
        emp.setSalary(30000.0);

        Mockito.when(empService.findById(1)).thenReturn(emp);
        Mockito.doNothing().when(empService).save(any(Employee.class));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/employees/1/salary")
                        .param("newSalary", "50000"))
                .andExpect(status().isOk())
                .andExpect(content().string("Salary updated."));
    }

    @Test
    void testUpdateSalary_NotFound() throws Exception {
        Mockito.when(empService.findById(1)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/employees/1/salary")
                        .param("newSalary", "50000"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Employee not found."));
    }
}
