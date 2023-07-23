/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.studentresultmanagement.Repository;

import com.studentresultmanagement.dto.StudentResult;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 *
 * @author offaj
 */
public class Repository {
    
    private static Repository instance;
     private static String url = "jdbc:mysql://localhost:3306/student_result_management_portal";
	private static String pass = "Ajai@9578";
	private static String uName = "root";
	private static Connection con;
    
    private Repository(){
        
    }
    
    public static Repository getInstance(){
        if(instance == null){
            try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,uName,pass);
            } catch (Exception e) {
                
            }
            
            return new Repository();
        }
        return instance;
    }

    public boolean checkStudentLogin(String userName, String password) {
        return true;
    }

    public boolean checkAdminLogin(String userName, String password) {
           if(userName.equals("Admin") && password.equals("Admin"))return true;
           return false;
    }

    public boolean addNewStudent(String courseName, String branchName, String rollNumber, String studentName, String gender, String fatherName) {
        String addNewStudentQuery = "insert into student_details (courseName,branchName,rollNumber,studentName,gender,fatherName) values(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(addNewStudentQuery);
            preparedStatement.setString(1, courseName);
            preparedStatement.setString(2, branchName);
            preparedStatement.setInt(3, Integer.parseInt(rollNumber));
            preparedStatement.setString(4, studentName);
            preparedStatement.setString(5, gender);
            preparedStatement.setString(6, fatherName);
            
              if(preparedStatement.executeUpdate()==1){
                  return true;
              }
            
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
       return false;
    }

    public boolean addResult(String rollNumber, String physics, String math, String em, String dbms, String os) {
        String addResult = "insert into student_marks (rollNumber,physics,Maths,EM,DBMS,OS) values(?,?,?,?,?,?)";
        String check = "select * from student_details";
     
       
        
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(check);
            
            if(rs.next()){
            PreparedStatement preparedStatement = con.prepareStatement(addResult);
            preparedStatement.setInt(1, Integer.parseInt(rollNumber));
            preparedStatement.setDouble(2, Double.parseDouble(physics));
            preparedStatement.setDouble(3, Double.parseDouble(math));
            preparedStatement.setDouble(4, Double.parseDouble(em));
            preparedStatement.setDouble(5, Double.parseDouble(dbms));
            preparedStatement.setDouble(6,Double.parseDouble(os)); 
            
              if(preparedStatement.executeUpdate()==1){
                  return true;
              }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    public ResultSet getStudentData() {
        ResultSet rs = null;
        try {
            String query = "select * from student_details";
            Statement st = con.createStatement();
            rs =  st.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getAllStudentResult() {
         ResultSet rs = null;
        try {
            String query = "select * from student_marks";
            Statement st = con.createStatement();
            rs =  st.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public StudentResult getResult() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public StudentResult getStudentResult(int rollNo) {
        ResultSet rs = null;
          StudentResult sr = null;
        try {
            String checkQuery = "select * from student_details where rollNumber = "+rollNo;
            String query = "select courseName,branchName,student_details.rollNumber,studentName,gender,fatherName,physics,Maths,EM,DBMS,OS from student_details inner join student_marks on  student_details.rollNumber = student_marks.rollNumber where student_details.rollNumber = "+rollNo;
            Statement st = con.createStatement();
            rs =  st.executeQuery(checkQuery);
            
            
            
            if(rs.next()){
               ResultSet data = st.executeQuery(query);
                 if(data ==null){
                    return null;
                }
                 data.next();
              sr = new StudentResult(data.getString("courseName"), data.getString("branchName"), data.getInt("rollNumber"), data.getString("studentName"),data.getString("gender"), data.getString("fatherName"), data.getDouble("physics"),data.getDouble("Maths"), data.getDouble("EM"), data.getDouble("DBMS"), data.getDouble("OS"));
            }
                 
         }
         catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return sr;
    }
}

