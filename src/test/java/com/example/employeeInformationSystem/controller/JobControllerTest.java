package com.example.employeeInformationSystem.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

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
import com.example.employeeInformationSystem.entity.Job;
import com.example.employeeInformationSystem.service.JobService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Import(SecurityConfig.class)  
@WebMvcTest(JobController.class)
@WithMockUser(username = "admin", roles = {"ADMIN"})
public class JobControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean 
    private JobService jobService;

    @Test
    void testGetJobs() throws Exception{
        Job job1=new Job();
        job1.setId(1);
        job1.setTitle("HR");

        Job job2=new Job();
        job2.setId(2);
        job2.setTitle("Software Engineer");

        List<Job> jobs=Arrays.asList(job1,job2);
        Mockito.when(jobService.getJobs()).thenReturn(jobs);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/jobs"))
        .andExpect(status().isOk()); 
    }

    @Test
    void testCreateJobs() throws Exception{
        Job job=new Job();
        job.setId(1);
        job.setTitle("HR");

        ObjectMapper objectMapper=new ObjectMapper();
        String json=objectMapper.writeValueAsString(job);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/jobs")
        .contentType("application/json")
        .content(json))
        .andExpect(status().isOk());

        verify(jobService, times(1)).createJob(Mockito.any(Job.class));

    }
}
