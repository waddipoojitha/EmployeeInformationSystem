package com.example.employeeInformationSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeeInformationSystem.entity.Job;
import com.example.employeeInformationSystem.repository.JobRepository;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepo;

    public List<Job> getJobs()
    {
     return jobRepo.findAll();
    }
    public void createJob(Job job){
         jobRepo.save(job);
    }
}
