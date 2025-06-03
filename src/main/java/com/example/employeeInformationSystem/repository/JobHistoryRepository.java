package com.example.employeeInformationSystem.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.employeeInformationSystem.entity.JobHistory;
import com.example.employeeInformationSystem.entity.JobHistoryId;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistory,JobHistoryId>{

    List<JobHistory> findByEmployeeId(Long empId);


    
}
