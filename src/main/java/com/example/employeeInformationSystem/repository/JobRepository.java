package com.example.employeeInformationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employeeInformationSystem.entity.Job;
@Repository
public interface JobRepository extends JpaRepository<Job,Integer> {
    
}
