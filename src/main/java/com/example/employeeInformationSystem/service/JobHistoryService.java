package com.example.employeeInformationSystem.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeeInformationSystem.dto.JobHistoryDTO;
import com.example.employeeInformationSystem.entity.JobHistory;
import com.example.employeeInformationSystem.repository.JobHistoryRepository;

@Service
public class JobHistoryService {
    @Autowired
    private JobHistoryRepository jobHistoryRepo;

    public JobHistory saveJobHistory(JobHistory jobHistory){
        return jobHistoryRepo.save(jobHistory);
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
