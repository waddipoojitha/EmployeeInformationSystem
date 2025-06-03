package com.example.employeeInformationSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeInformationSystem.dto.JobHistoryDTO;
import com.example.employeeInformationSystem.entity.Employee;
import com.example.employeeInformationSystem.entity.Job;
import com.example.employeeInformationSystem.entity.JobHistory;
import com.example.employeeInformationSystem.entity.JobHistoryId;
import com.example.employeeInformationSystem.repository.EmployeeRepository;
import com.example.employeeInformationSystem.repository.JobRepository;
import com.example.employeeInformationSystem.service.JobHistoryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class JobHistoryController {
    @Autowired
    private JobHistoryService jobHistoryService;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/jobHistories")
    public void createJobHistory(@RequestBody JobHistoryDTO dto)
    {
        JobHistoryId id=new JobHistoryId();
        id.setJobId(dto.getJobId());
        id.setEmployeeId(dto.getEmployeeId());
        id.setStartDate(dto.getStartDate());


        Job job = jobRepository.findById(dto.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found"));
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        JobHistory jobHistory=new JobHistory();
        jobHistory.setId(id);
        jobHistory.setStartDate(dto.getStartDate());
        jobHistory.setEndDate(dto.getEndDate());
        jobHistory.setJob(job);
        jobHistory.setEmployee(employee);

        jobHistoryService.saveJobHistory(jobHistory);
    }
    @GetMapping("/jobHistories")
    public List<JobHistoryDTO> getJobHistories(){
        return jobHistoryService.getJobHistories();
    }

    
}