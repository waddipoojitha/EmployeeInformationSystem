package com.example.employeeInformationSystem.entity;

import java.time.LocalDate;



import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class JobHistory { 
    
    @EmbeddedId
    private JobHistoryId id;
    private LocalDate endDate;
    
    @ManyToOne
    @MapsId("jobId")
    @JoinColumn(name="job_id")
    private Job job;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name="employee_id")
    private Employee employee;

    public JobHistoryId getId() {
        return id;
    }

    public void setId(JobHistoryId id) {
        this.id = id;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate end_date) {
        this.endDate = end_date;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    
    public LocalDate getStartDate() {
    return id != null ? id.getStartDate() : null;
    }

    public void setStartDate(LocalDate startDate) {
        if (this.id == null) {
            this.id = new JobHistoryId();
        }
        this.id.setStartDate(startDate);
    }

    public JobHistory() {
    }

    public JobHistory(JobHistoryId id, LocalDate endDate, Job job, Employee employee) {
        this.id = id;
        this.endDate = endDate;
        this.job = job;
        this.employee = employee;
        // this.startDate=startDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = prime * result + ((job == null) ? 0 : job.hashCode());
        result = prime * result + ((employee == null) ? 0 : employee.hashCode());
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
        JobHistory other = (JobHistory) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (endDate == null) {
            if (other.endDate != null)
                return false;
        } else if (!endDate.equals(other.endDate))
            return false;
        if (job == null) {
            if (other.job != null)
                return false;
        } else if (!job.equals(other.job))
            return false;
        if (employee == null) {
            if (other.employee != null)
                return false;
        } else if (!employee.equals(other.employee))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "JobHistory [id=" + id + ", endDate=" + endDate + ", job=" + job + ", employee=" + employee + "]";
    }     
}