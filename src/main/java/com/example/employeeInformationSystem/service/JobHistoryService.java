package com.example.employeeInformationSystem.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeeInformationSystem.dto.JobHistoryDTO;
import com.example.employeeInformationSystem.entity.Employee;
import com.example.employeeInformationSystem.entity.Job;
import com.example.employeeInformationSystem.entity.JobHistory;
import com.example.employeeInformationSystem.entity.JobHistoryId;
import com.example.employeeInformationSystem.exception.ResourceNotFoundException;
import com.example.employeeInformationSystem.repository.EmployeeRepository;
import com.example.employeeInformationSystem.repository.JobHistoryRepository;
import com.example.employeeInformationSystem.repository.JobRepository;

@Service
public class JobHistoryService {
    @Autowired
    private JobHistoryRepository jobHistoryRepo;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    
    public void createJobHistory(JobHistoryDTO dto) {
    JobHistoryId id = new JobHistoryId();
    id.setEmployeeId(dto.getEmployeeId());
    id.setJobId(dto.getJobId());
    id.setStartDate(dto.getStartDate());

    Job job = jobRepository.findById(dto.getJobId())
            .orElseThrow(() -> new ResourceNotFoundException("Job not found"));
    Employee employee = employeeRepository.findById(dto.getEmployeeId())
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

    JobHistory jobHistory = new JobHistory();
    jobHistory.setId(id);
    jobHistory.setStartDate(dto.getStartDate());
    jobHistory.setEndDate(dto.getEndDate());
    jobHistory.setJob(job);
    jobHistory.setEmployee(employee);

    jobHistoryRepo.save(jobHistory);
}

    public List<JobHistoryDTO> getJobHistories(){
        List<JobHistory> jobHistories=jobHistoryRepo.findAll();
        return jobHistories.stream().map(histories->new JobHistoryDTO(
            histories.getJob()!=null?histories.getJob().getId():null,
            histories.getEmployee()!=null?histories.getEmployee().getId():null,
            histories.getStartDate(),
            histories.getEndDate()
        )).collect(Collectors.toList());
    }

    public List<JobHistoryDTO> findByEmployeeId(Long empId) {
        List<JobHistory> jobHistories=jobHistoryRepo.findByEmployeeId(empId);
        return jobHistories.stream().map(histories->new JobHistoryDTO(
            histories.getJob()!=null?histories.getJob().getId():null,
            histories.getEmployee()!=null?histories.getEmployee().getId():null,
            histories.getStartDate(),
            histories.getEndDate()
        )).collect(Collectors.toList());
    }

    
}
