package com.example.employeeInformationSystem.service;

import com.example.employeeInformationSystem.dto.JobHistoryDTO;
import com.example.employeeInformationSystem.entity.Employee;
import com.example.employeeInformationSystem.entity.Job;
import com.example.employeeInformationSystem.entity.JobHistory;
import com.example.employeeInformationSystem.repository.EmployeeRepository;
import com.example.employeeInformationSystem.repository.JobHistoryRepository;
import com.example.employeeInformationSystem.repository.JobRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class JobHistoryServiceTest {

    @InjectMocks
    private JobHistoryService jobHistoryService;

    @Mock
    private JobHistoryRepository jobHistoryRepo;
    @Mock
    private JobRepository jobRepository;
    @Mock
    private EmployeeRepository employeeRepository;

    @Test
void testSaveJobHistory() {
    JobHistoryDTO dto = new JobHistoryDTO(1, 1,
            LocalDate.of(2023, 1, 1),
            LocalDate.of(2024, 1, 1));

    Job job = new Job();
    job.setId(1);

    Employee employee = new Employee();
    employee.setId(1);

    JobHistory jobHistory = new JobHistory();
    jobHistory.setStartDate(dto.getStartDate());
    jobHistory.setEndDate(dto.getEndDate());
    jobHistory.setJob(job);
    jobHistory.setEmployee(employee);

    Mockito.when(jobHistoryRepo.save(Mockito.any(JobHistory.class)))
           .thenReturn(jobHistory);

    Mockito.when(jobRepository.findById(1)).thenReturn(java.util.Optional.of(job));
    Mockito.when(employeeRepository.findById(1)).thenReturn(java.util.Optional.of(employee));
    jobHistoryService.createJobHistory(dto);
    Mockito.verify(jobHistoryRepo).save(Mockito.any(JobHistory.class));
}

    @Test
    void testGetJobHistories() {
        Job job = new Job();
        job.setId(1);

        Employee employee = new Employee();
        employee.setId(1);

        JobHistory history = new JobHistory();
        history.setJob(job);
        history.setEmployee(employee);
        history.setStartDate(LocalDate.of(2022, 5, 1));
        history.setEndDate(LocalDate.of(2023, 5, 1));

        Mockito.when(jobHistoryRepo.findAll()).thenReturn(Arrays.asList(history));

        List<JobHistoryDTO> dtos = jobHistoryService.getJobHistories();

        Assertions.assertEquals(1, dtos.size());
        Assertions.assertEquals(1, dtos.get(0).getEmployeeId());
        Assertions.assertEquals(1, dtos.get(0).getJobId());
    }

    @Test
    void testFindByEmployeeId() {
        Job job = new Job();
        job.setId(2);

        Employee employee = new Employee();
        employee.setId(100);

        JobHistory history = new JobHistory();
        history.setJob(job);
        history.setEmployee(employee);
        history.setStartDate(LocalDate.of(2021, 1, 1));
        history.setEndDate(LocalDate.of(2022, 1, 1));

        Mockito.when(jobHistoryRepo.findByEmployeeId(100L)).thenReturn(Arrays.asList(history));

        List<JobHistoryDTO> dtos = jobHistoryService.findByEmployeeId(100L);

        Assertions.assertEquals(1, dtos.size());
        Assertions.assertEquals(100, dtos.get(0).getEmployeeId());
        Assertions.assertEquals(2, dtos.get(0).getJobId());
    }
}
