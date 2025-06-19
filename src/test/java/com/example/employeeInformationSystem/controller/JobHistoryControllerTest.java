package com.example.employeeInformationSystem.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.employeeInformationSystem.config.SecurityConfig;
import com.example.employeeInformationSystem.dto.JobHistoryDTO;
import com.example.employeeInformationSystem.entity.Employee;
import com.example.employeeInformationSystem.entity.Job;
import com.example.employeeInformationSystem.repository.EmployeeRepository;
import com.example.employeeInformationSystem.repository.JobRepository;
import com.example.employeeInformationSystem.service.JobHistoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@WebMvcTest(JobHistoryController.class)
@WithMockUser(username="admin",roles={"ADMIN"})
@Import(SecurityConfig.class)
public class JobHistoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobHistoryService jobHistoryService;

    @MockBean
    private JobRepository jobRepository;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    void testGetJobHistories() throws Exception{
        JobHistoryDTO jobHistoryDTO = new JobHistoryDTO();
        jobHistoryDTO.setEmployeeId(1);
        jobHistoryDTO.setJobId(1);
        jobHistoryDTO.setStartDate(LocalDate.parse("2024-11-11"));

        List<JobHistoryDTO> jobHistories=Arrays.asList(jobHistoryDTO);

        Mockito.when(jobHistoryService.getJobHistories()).thenReturn(jobHistories);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/jobHistories"))
        .andExpect(status().isOk());
    }

      @Test
    void testCreateJobHistory_success() throws Exception {
        
        JobHistoryDTO dto = new JobHistoryDTO(2, 1,
                LocalDate.of(2024, 11, 11),
                LocalDate.of(2025, 1, 1));

        
        Job job = new Job();
        job.setId(2);

        Employee employee = new Employee();
        employee.setId(1);

        Mockito.when(jobRepository.findById(2)).thenReturn(Optional.of(job));
        Mockito.when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

        
        Mockito.doNothing().when(jobHistoryService).createJobHistory(any(JobHistoryDTO.class));

         ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        
        mockMvc.perform(MockMvcRequestBuilders.post("/api/jobHistories")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk()); 
    }

}