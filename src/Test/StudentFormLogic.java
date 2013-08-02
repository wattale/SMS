/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SMS;

/**
 *
 * @author Lasitha
 */
public class StudentFormLogic {
    
    private DBInteface inteface;
    public StudentFormLogic(){
        inteface = new DBInteface();
    }
    
    public boolean saveStudent(Student newStudent){
        boolean saved = false;
        saved = inteface.addStudent(newStudent.getIndex(), newStudent.getNameWithIn(), newStudent.getFName(), newStudent.getDateOfBirth(), newStudent.getGender(), newStudent.getHomeAddress(), newStudent.getTelephone());
        
        
        return saved;
    }
    
    public Student checkStudent(String index){
        Student student = inteface.getStudent(index);        
        return student;      
    }
    
    public boolean upDate(Student student){
        
        return  inteface.upDateInfo(student) ;
        
    }
    
   public boolean changePWord(String index,String newPWord){
       return inteface.changePassWord(index, newPWord);
   } 
    

}
