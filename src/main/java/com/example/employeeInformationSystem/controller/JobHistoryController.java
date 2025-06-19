package com.example.employeeInformationSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeInformationSystem.dto.JobHistoryDTO;
import com.example.employeeInformationSystem.service.JobHistoryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class JobHistoryController {
    @Autowired
    private JobHistoryService jobHistoryService;

   @PostMapping("/jobHistories")
public void createJobHistory(@RequestBody JobHistoryDTO dto) {
    jobHistoryService.createJobHistory(dto);
}

    @GetMapping("/jobHistories")
    public List<JobHistoryDTO> getJobHistories(){
        return jobHistoryService.getJobHistories();
    }
}