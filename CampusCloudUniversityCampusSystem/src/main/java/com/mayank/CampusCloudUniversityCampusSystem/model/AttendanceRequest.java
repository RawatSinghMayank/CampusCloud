package com.mayank.CampusCloudUniversityCampusSystem.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AttendanceRequest {
    private String studentUnivId;
    private String facultyUnivId;
    private Long subjectId;
    private LocalDate date;  // Added this field
    private boolean present;
    private String remarks;
}