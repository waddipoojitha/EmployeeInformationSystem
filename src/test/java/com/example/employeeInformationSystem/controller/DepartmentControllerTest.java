package com.example.employeeInformationSystem.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.employeeInformationSystem.config.SecurityConfig;
import com.example.employeeInformationSystem.dto.DepartmentDTO;
import com.example.employeeInformationSystem.entity.Department;
import com.example.employeeInformationSystem.service.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(DepartmentController.class)
@WithMockUser(username="admin",roles={"ADMIN"})
@Import(SecurityConfig.class)
public class DepartmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    @Test
    void testGetDepartments() throws Exception{
        DepartmentDTO dept1 = new DepartmentDTO(1, "HR", 100);
        DepartmentDTO dept2 = new DepartmentDTO(2, "IT", null);
        List<DepartmentDTO> departments = Arrays.asList(dept1,dept2);
        Mockito.when(departmentService.getDepartments()).thenReturn(departments);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/departments"))
        .andExpect(status().isOk());
    }

    @Test
    void testSetHod() throws Exception{
        int deptId=1;
        int empId=4;
        mockMvc.perform(MockMvcRequestBuilders.put("/api/departments/{deptId}/hod/{empId}", deptId,empId))
        .andExpect(status().isOk());
        verify(departmentService,times(1)).setHod(deptId, empId);
    }

    @Test
    void testCreateDepartment() throws Exception{
        Department department = new Department();
    department.setId(1);
    department.setName("Finance");

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(department);

    mockMvc.perform(MockMvcRequestBuilders.post("/api/departments")
            .contentType("application/json")
            .content(json))
            .andExpect(status().isOk());

    verify(departmentService, times(1)).createDepartment(Mockito.any(Department.class));
    }
}