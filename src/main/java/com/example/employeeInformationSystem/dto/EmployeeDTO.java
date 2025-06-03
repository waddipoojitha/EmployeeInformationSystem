package com.example.employeeInformationSystem.dto;

import java.time.LocalDate;

public class EmployeeDTO {
    private int id;
    private String name;
    private double salary;
    private LocalDate joinDate;
    private String jobTitle;
    private String departmentName;
    
    public EmployeeDTO() {
    }
    
    public EmployeeDTO(int id, String name, double salary, LocalDate joinDate,String jobTitle,String departmentName) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.joinDate = joinDate;
        this.jobTitle=jobTitle;
        this.departmentName=departmentName;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public LocalDate getJoinDate() {
        return joinDate;
    }
    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    
    
}
