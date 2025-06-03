package com.example.employeeInformationSystem.dto;

import java.time.LocalDate;

public class JobHistoryDTO {
    private int jobId;
    private int employeeId;
    private LocalDate startDate;
    private LocalDate endDate;

    // Getters and setters
    public int getJobId() { return jobId; }
    public void setJobId(int jobId) { this.jobId = jobId; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public JobHistoryDTO() {
    }
    public JobHistoryDTO(int jobId, int employeeId, LocalDate startDate, LocalDate endDate) {
        this.jobId = jobId;
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    
}
