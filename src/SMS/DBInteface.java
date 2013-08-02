/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SMS;

/**
 *
 * @author Lasitha
 */
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.*;
import java.util.Vector;
import javax.imageio.ImageIO;

public class DBInteface {

     public Vector getStudentRec(){
         System.out.println("Database Interface is trying to get student Records... ");
         Vector studentRec = null;
         ResultSet resultSet;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mysql";
            Connection connection = DriverManager.getConnection(url, "root", "");
            System.out.println("Got the connection.... to the data base.. ");
            Statement statement = connection.createStatement();
            statement.executeUpdate("use StudentsManagement");
            resultSet = statement.executeQuery("select * from students order by Student_index ASC");
            System.out.println("Got the result set.... ");
            studentRec = new Vector();
            while (resultSet.next()) {
                System.out.println("putting... Students in to the vector... ");
                Student tmpStudent = new Student(resultSet.getString("Student_index"), resultSet.getString("Namewith_in"), resultSet.getString("FName"), resultSet.getString("DateofB"), resultSet.getString("Gender"), resultSet.getString("HomeADD"), resultSet.getString("Telephone"), null);
                studentRec.addElement(tmpStudent);
                System.out.println("put a student.. ");
            }
            return studentRec;
        } catch (Exception ex) {
            System.out.println("Got an Exceptiong while entering  to the file.. .");
            return studentRec;
        }


    }

    
    

   public boolean upDateInfo(Student student){
       boolean upDated = false;
       
       try{
            String url = "jdbc:mysql://localhost:3306/mysql";
            Connection connection = DriverManager.getConnection(url, "root", "");
            //Statement stmt = connection.createStatement();
             Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate("use StudentsManagement");
            PreparedStatement PStmt = connection.prepareStatement("update students set  Namewith_in = '"+student.getNameWithIn()+"', FName = '"+student.getFName()+"', DateofB = '"+student.getDateOfBirth()+"', Gender = '"+student.getGender()+"', HomeADD = '"+student.getHomeAddress()+"', Telephone = '"+student.getTelephone()+"' where Student_index ='"+student.getIndex()+"'");
            PStmt.executeUpdate();

            ResultSet tmp=stmt.executeQuery("select * from students where Student_index='"+student.getIndex()+"' for update");
            if(tmp.first()){
            tmp.updateBytes("photo", student.getImage());
            tmp.updateRow();
                }else{
                System.out.println("not validddddddd");
                }
                tmp.close();
            upDated = true;

            }catch(Exception e){
           System.out.println("UPDATE ERROR");}

            upDated = true;
           
      
       
       
       
       return upDated;
       
       
       
   }
  
   public boolean changePassWord(String index,String newPW){
       boolean pWchanged = false;
       try{
             Class.forName("com.mysql.jdbc.Driver");
             String url = "jdbc:mysql://localhost:3306/mysql";
             Connection connection = DriverManager.getConnection(url, "root", "");
             Statement statement = connection.createStatement();
             statement.executeUpdate("use StudentsManagement");
             PreparedStatement PStmt = connection.prepareStatement("update loginStdnts set  Password = '"+newPW+"' where Student_index ='"+index+"'");
             PStmt.executeUpdate();
             pWchanged = true;
           
       }catch(Exception ex){
           ex.printStackTrace();
       }
       
       
       return pWchanged;
   }
    
    public LoginDetails login(String userName, String passWord){
      LoginDetails details = new LoginDetails(false, "");
      try{
             Class.forName("com.mysql.jdbc.Driver");
             String url = "jdbc:mysql://localhost:3306/mysql";
             Connection connection = DriverManager.getConnection(url, "root", "");
             Statement statement = connection.createStatement();
             statement.executeUpdate("use StudentsManagement");
            
      
     
           ResultSet rs = statement.executeQuery("SELECT * from loginStdnts WHERE Student_index ='"+ userName+"'" + "AND Password ='" +passWord+"'");
           if(rs.next()){
              details= new LoginDetails(true,"student"); 
               
               
           }else{
                rs = statement.executeQuery("SELECT * from loginAdmin WHERE User_Name ='"+ userName+"'" + "AND Password ='" +passWord+"'");
                if(rs.next()){
                    details = new LoginDetails(true,"admin");
                }
           }
           
      }catch(Exception ex){
         // ex.printStackTrace();
          
          return details;
      }
     return details;
        
    }
    
    public boolean isStudent(String index){
        boolean isIn = false;
        try{
             Class.forName("com.mysql.jdbc.Driver");
             String url = "jdbc:mysql://localhost:3306/mysql";
             Connection connection = DriverManager.getConnection(url, "root", "");
             Statement statement = connection.createStatement();
             statement.executeUpdate("use StudentsManagement");
      
        
        
       
             ResultSet rs = statement.executeQuery("SELECT * from students WHERE Student_index ='"+index+"'");
             
             if(rs.next()){
                 isIn = true;
             }else return false;
                 
            
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return isIn;
        
    }
    
    public boolean addStudent(String index, String nameWithIn, String fName, String dateOfBirth, String gender, String homeAdd, String telephone){
        boolean isAdd = false;
        try{
             Class.forName("com.mysql.jdbc.Driver");
             String url = "jdbc:mysql://localhost:3306/mysql";
             Connection connection = DriverManager.getConnection(url, "root", "");
             Statement statement = connection.createStatement();
             statement.executeUpdate("use StudentsManagement");
                System.out.println("Inside Add logic....");
                  if(!isStudent(index)){
                      System.out.println("Got student is not in the login table...");
           
                PreparedStatement PStmt = connection.prepareStatement("insert into students values (?,?,?,?,?,?,?,?)");
                      System.out.println("setting a new sign up");
                setNewSignUP(index, fName+"@sms", "student");
                PStmt.setString(1, index);
                PStmt.setString(2, nameWithIn);
                PStmt.setString(3, fName);
                PStmt.setString(4, dateOfBirth);
                PStmt.setString(5, gender);
                PStmt.setString(6, homeAdd);
                PStmt.setString(7, telephone);
                PStmt.setBytes(8, selectPicture());              
                PStmt.executeUpdate();
                isAdd = true;
                
                
           
        }
        } catch(Exception ex){
                ex.printStackTrace();
        }
        
       return isAdd; 
        
    }
    
    public byte[] selectPicture(){
        byte[] pic = null;
        try{
        BufferedImage image = ImageIO.read(new File("smile.JPG"));
        pic = imageToBytes(image);
        
        }catch(Exception ex){
            System.out.println("Select pic Exception... ");
            ex.printStackTrace();
        }
        return pic;
    }


    public Student getStudent(String index){
        Student student = new Student("","","","","","","");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mysql";
            Connection connection = DriverManager.getConnection(url, "root", "");
            Statement statement = connection.createStatement();
            statement.executeUpdate("use StudentsManagement");
            ResultSet rs = null;
            rs = statement.executeQuery("SELECT * from students WHERE Student_index ='"+index+"'" );
            
            if(rs.next()){
                student = new Student((String) rs.getString("Student_index"),(String) rs.getString("Namewith_in"),(String) rs.getString("FName"),(String) rs.getString("DateofB"),(String) rs.getString("Gender"),(String) rs.getString("HomeADD"),(String) rs.getString("Telephone"),rs.getBytes("photo"));
                
                
            }else{
                student.setIsValid(false);
                
                
            }
                
           
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        
        return student;        
    }
    
    
    
    
    public boolean setNewSignUP(String userName, String passWord, String type){
        boolean isSignUP = false;
       
            
           
        if(type.equalsIgnoreCase("student")){
            try{
             Class.forName("com.mysql.jdbc.Driver");
             String url = "jdbc:mysql://localhost:3306/mysql";
             Connection connection = DriverManager.getConnection(url, "root", "");
             Statement statement = connection.createStatement();
             statement.executeUpdate("use StudentsManagement");
             ResultSet rs = null;
            
           
                rs = statement.executeQuery("SELECT * from loginStdnts WHERE Student_index ='"+ userName+"'" );
                if(!rs.next()){
                   // System.out.println("Student is now being added...");
                     PreparedStatement PStmt = connection.prepareStatement("insert into loginStdnts values (?,?)");
                     PStmt.setString(1,userName);
                     PStmt.setString(2,passWord);
                     PStmt.executeUpdate();
                     isSignUP = true;
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
               
         
            
        }else{
             try{
             Class.forName("com.mysql.jdbc.Driver");
             String url = "jdbc:mysql://localhost:3306/mysql";
             Connection connection = DriverManager.getConnection(url, "root", "");
             Statement statement = connection.createStatement();
             statement.executeUpdate("use StudentsManagement");
                //System.out.println("Just about to add an admin ....");
                ResultSet rs = null;
                rs = statement.executeQuery("SELECT * from loginAdmin WHERE User_Name ='"+ userName+"'" );
                if(!rs.next()){
                    PreparedStatement PStmt = connection.prepareStatement("insert into loginAdmin values (?,?)");
                     PStmt.setString(1,userName);
                     PStmt.setString(2,passWord);
                     PStmt.executeUpdate();
                     //System.out.println("Successfully added admin...");
                     isSignUP = true;
                }
                 }catch(Exception ex){
                     ex.printStackTrace();
                 }
               
          
            
            
        }
       
        
        
        
        return isSignUP;
        
        
    }
    
   public byte[] imageToBytes(BufferedImage img){
        byte[] imageInByte = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
	ImageIO.write( img, "jpg", baos );
	baos.flush();
	imageInByte = baos.toByteArray();
	baos.close();}
        catch(Exception e){

        }
        return imageInByte;
    }
    public BufferedImage bytesToImage(byte[] byteImage){
        BufferedImage bImageFromConvert = null;
        InputStream in = new ByteArrayInputStream(byteImage);
        try{
	bImageFromConvert = ImageIO.read(in);
        }catch(Exception e){
            System.out.println("byte array error");
        }
        return bImageFromConvert;
    }
    
    

}
