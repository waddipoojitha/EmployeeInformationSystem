package com.example.employeeInformationSystem.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class JobHistoryId implements Serializable{
    @Column(name="job_id")
    private int jobId;
    @Column(name="employee_id")
    private int employeeId;
    @Column(name="start_date")
    private LocalDate startDate;
    
   
    public int getJobId() {
        return jobId;
    }
    public void setJobId(int jobId) {
        this.jobId = jobId;
    }
    public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate starDate) {
        this.startDate = starDate;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + jobId;
        result = prime * result + employeeId;
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JobHistoryId other = (JobHistoryId) obj;
        if (jobId != other.jobId)
            return false;
        if (employeeId != other.employeeId)
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        return true;
    }

    
}
