/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.studentresultmanagement.dto;

/**
 *
 * @author offaj
 */
public class StudentResult {
    private String courseName;
    private String branchName;
    private int rollNumber;
    private String studentName;
    private String gender;
    private String fatherName;
    private double physics;
    private double maths;
    private double em;
    private double dbms;
    private double os;

    public StudentResult(String courseName, String branchName, int rollNumber, String studentName, String gender, String fatherName, double physics, double maths, double em, double dbms, double os) {
        this.courseName = courseName;
        this.branchName = branchName;
        this.rollNumber = rollNumber;
        this.studentName = studentName;
        this.gender = gender;
        this.fatherName = fatherName;
        this.physics = physics;
        this.maths = maths;
        this.em = em;
        this.dbms = dbms;
        this.os = os;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getBranchName() {
        return branchName;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getGender() {
        return gender;
    }

    public String getFatherName() {
        return fatherName;
    }

    public double getPhysics() {
        return physics;
    }

    public double getMaths() {
        return maths;
    }

    public double getEm() {
        return em;
    }

    public double getDbms() {
        return dbms;
    }

    public double getOs() {
        return os;
    }
   
    
    
    
}
