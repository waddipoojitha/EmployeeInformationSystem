package com.example.employeeInformationSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeInformationSystem.entity.Job;
import com.example.employeeInformationSystem.service.JobService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api")
public class JobController {
    @Autowired
    private JobService jobService;


    @GetMapping("/jobs")
    public List<Job> getJobs()
    {
    return jobService.getJobs();
    }
   

    @PostMapping("/jobs")
    public void createJob(@RequestBody Job job){
        jobService.createJob(job);
    }
}
