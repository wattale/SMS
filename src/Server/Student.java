/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Server;

import SMS.*;
import java.io.Serializable;

/**
 *
 * @author Lasitha
 */
public class Student implements Serializable {
    private String index;
    private String entrdPassWord;


    public String getEntrdPassWord() {
        return entrdPassWord;
    }

    public void setEntrdPassWord(String entrdPassWord) {
        this.entrdPassWord = entrdPassWord;
    }
    private boolean isValid;
    private String nameWithIn;
    private String fName;
    private String dateOfBirth;
    private String gender;
    private String homeAddress;
    private String telephone;
    private byte[] image;
    
    public Student(String index, String nameWithIn, String fName, String dateOfBirth, String gender, String homeAddress, String telephone,byte[] image){
        this.index = index;
        this.nameWithIn = nameWithIn;
        this.fName = fName; 
        this.dateOfBirth = dateOfBirth; 
        this.gender = gender;
        this.homeAddress = homeAddress;
        this.telephone = telephone;
        this.isValid = true;
        this.image = image;

    }

     public Student(String index, String nameWithIn, String fName, String dateOfBirth, String gender, String homeAddress, String telephone){
        this.index = index;
        this.nameWithIn = nameWithIn;
        this.fName = fName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.homeAddress = homeAddress;
        this.telephone = telephone;
        this.isValid = true;
        this.image = null;

    }
    
    
      public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getFName() {
        return fName;
    }

    public String getGender() {
        return gender;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public String getIndex() {
        return index;
    }

    public String getNameWithIn() {
        return nameWithIn;
    }

    public String getTelephone() {
        return telephone;
    }
    
    public void setIsValid(boolean valid){
        this.isValid = valid;
    }
    
    public boolean getIsValid(){
        return isValid;
    }

}
