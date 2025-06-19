package com.example.employeeInformationSystem.service;

import com.example.employeeInformationSystem.entity.Job;
import com.example.employeeInformationSystem.repository.JobRepository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class JobServiceTest {

    @InjectMocks
    private JobService jobService;

    @Mock
    private JobRepository jobRepo;

    @Test
    void testGetJobs() {
        Job job1 = new Job();
        job1.setId(1);
        job1.setTitle("Developer");

        Job job2 = new Job();
        job2.setId(2);
        job2.setTitle("Analyst");

        Mockito.when(jobRepo.findAll()).thenReturn(Arrays.asList(job1, job2));

        List<Job> jobs = jobService.getJobs();

        Assertions.assertEquals(2, jobs.size());
        Assertions.assertEquals("Developer", jobs.get(0).getTitle());
    }

    @Test
    void testCreateJob() {
        Job job = new Job();
        job.setId(1);
        job.setTitle("Tester");

        jobService.createJob(job);

        Mockito.verify(jobRepo, Mockito.times(1)).save(job);
    }
}
