package com.example.employeeInformationSystem.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double salary;
    private LocalDate joinDate;
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getSalary(){
        return salary;
    }
    public LocalDate getJoinDate() {
        return joinDate;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSalary(double salary){
        this.salary=salary;
    }
    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }
    
    public Job getJob() {
        return job;
    }
    public void setJob(Job job) {
        this.job = job;
    }
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }

    
    public Employee() {
    }


    public Employee(String name, double salary, LocalDate joinDate, Job job, Department department) {
        this.name = name;
        this.salary = salary;
        this.joinDate = joinDate;
        this.job = job;
        this.department = department;
    }


    @ManyToOne
    @JoinColumn(name="job_id")
    private Job job;
    
    @ManyToOne
    @JoinColumn(name="department_id")
    private Department department;


    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    private List<JobHistory> job_histories;
    
    
    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", joinDate=" + joinDate + ", job="
                + job + ", department=" + department + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        long temp;
        temp = Double.doubleToLongBits(salary);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((joinDate == null) ? 0 : joinDate.hashCode());
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
        Employee other = (Employee) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(other.salary))
            return false;
        if (joinDate == null) {
            if (other.joinDate != null)
                return false;
        } else if (!joinDate.equals(other.joinDate))
            return false;
        return true;
    }
    public Employee(int id) {
        this.id = id;
    }
    

    
}
