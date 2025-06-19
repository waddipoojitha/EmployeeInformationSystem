package com.example.employeeInformationSystem.dto;

public class DepartmentDTO {
    private int id;
    private String name;
    private Integer hodId;
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
    public Integer getHodId() {
        return hodId;
    }
    public void setHodId(Integer hodId) {
        this.hodId = hodId;
    }
    public DepartmentDTO() {
    }
    public DepartmentDTO(int id, String name, Integer hodId) {
        this.id = id;
        this.name = name;
        this.hodId = hodId;
    }
    
}
