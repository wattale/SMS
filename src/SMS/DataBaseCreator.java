/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SMS;

/**
 *
 * @author Lasitha
 */
import java.sql.*;
public class DataBaseCreator {
   
    

    public void createDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mysql";
            Connection connection = DriverManager.getConnection(url, "root", "");
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS StudentsManagement");
            stmt.executeUpdate("use StudentsManagement");
            
             String loginTableStudents = "loginStdnts(Student_index VARCHAR(100) NULL,Password VARCHAR(100) NULL)";
             stmt.executeUpdate("CREATE TABLE IF NOT EXISTS "+loginTableStudents);
             
             String loginTableAdministrators ="loginAdmin(User_Name VARCHAR(100) NULL, Password VARCHAR(100) NULL)";
             stmt.executeUpdate("CREATE TABLE IF NOT EXISTS "+loginTableAdministrators);
             
            
             String studentInfo= "students(Student_index VARCHAR(100),FOREIGN KEY(Student_index) REFERENCES loginStdnts(Student_index),Namewith_in VARCHAR(100) NULL,FName VARCHAR(100) NULL,DateofB VARCHAR(100) NULL,Gender VARCHAR(50) NULL,HomeADD VARCHAR(100) NULL,Telephone VARCHAR(100) NULL,photo longblob,PRIMARY KEY (Student_index))";
             stmt.executeUpdate("CREATE TABLE IF NOT EXISTS "+studentInfo);
             DBInteface inteface = new DBInteface();
             boolean sinedUP =inteface.setNewSignUP("lasitha", "blue", "admin");
             
             inteface = null;
             
             connection.close();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        
    }
    
    

}
