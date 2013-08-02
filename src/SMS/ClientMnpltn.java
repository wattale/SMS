/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SMS;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;
import javax.swing.JFileChooser;

/**
 *
 * @author Ashan
 */
public class ClientMnpltn  {
    private Socket socket;
    private ObjectOutputStream outStream;
    private ObjectInputStream inStream;

    
    public ClientMnpltn(Socket socket) throws IOException{
        this.socket = socket;
        outStream = new ObjectOutputStream(socket.getOutputStream());
        inStream = new ObjectInputStream(socket.getInputStream());        
    }
    
    public LoginDetails login(String userName, String passWord) throws IOException, ClassNotFoundException{
        LoginDetails details;
        Student student = new Student(userName, "", "","", "", "", "",null);
        student.setEntrdPassWord(passWord);
        Message message= new Message();
        message.setStudent(student);
        message.setCmd("login");
        outStream.writeObject(message);
        do{
        message =(Message) inStream.readObject();
        }while(message ==null);
        details=message.getDetails();
        return details;
        
    }

    public boolean saveStudent(Student newStudent) throws IOException, ClassNotFoundException{
        boolean saved = false;
        Message message = new Message();
        message.setCmd("saveStudent");
        message.setStudent(newStudent);
        outStream.writeObject(message);
        do{
        message = (Message) inStream.readObject();
        }while(message ==null);
        saved = message.isSaved();
        return saved;
    }


    public Student checkStudent(String index) throws IOException, ClassNotFoundException{
        Student student = new Student(index,"", "", "", "", "", "",null);
        Message message = new Message();
        message.setStudent(student);
        message.setCmd("checkStudent");
        outStream.writeObject(message);
        do{
            message =(Message) inStream.readObject();
        }while(message ==null);
        student = message.getStudent();

        return student;


    }


    public boolean upDate(Student student) throws IOException, ClassNotFoundException{
        boolean upDated = false;
        Message message = new Message();
        message.setStudent(student);
        message.setCmd("upDate");
        outStream.writeObject(message);
        do{
            message =(Message) inStream.readObject();
        }while(message ==null);
        upDated = message.isUpDated();

        return upDated;
    }

    public boolean changePWord(String index,String newPWord) throws IOException, ClassNotFoundException{
        boolean changed = false;
        Student student = new Student(index, "", "", "", "", "", "",null);
        student.setEntrdPassWord(newPWord);
        Message message = new Message();
        message.setStudent(student);
        message.setCmd("changePWord");
        outStream.writeObject(message);
        do{
            message = (Message) inStream.readObject();
        }while(message==null);
        changed = message.isChangedPWord();

        return changed;
    }

  public void disposeThread() throws IOException{
      Message message = new Message();
      message.setCmd("Exit");
      outStream.writeObject(message);


  }


public boolean generateReports(String path) throws IOException, ClassNotFoundException{
    boolean reported = false;
    Message message = new Message();
    message.setCmd("generateReports");
    outStream.writeObject(message);
    Vector students =null;
    do{
        students = (Vector) inStream.readObject();
        System.out.println("Wating till server sends the Vector... ");
    }while(students == null);
    System.out.println("Got the Student Vector...... ");
    reported = wroteOnFile(students,path);
    return reported;
}

public boolean wroteOnFile(Vector students,String filePath){
        System.out.println("Inside wrote on file method.... ");
        System.out.println("The save path: " +filePath);
        boolean wrote = false;        
        int numOfElements;     
        numOfElements = 0;
        try{
        File file = new File(filePath);
        FileWriter fstream = new FileWriter(file);
        BufferedWriter out = new BufferedWriter(fstream);
        out.write("\t\t Student Information Record");
        out.newLine();
        out.write("==============================================================");
        out.newLine();
            //System.out.println("Student Capacity.. "+Integer.toString(students.size()));
        while(numOfElements != students.size()){
            System.out.println("Inside the while looopp... ");
             //out.write("Student number: "+Integer.toString(numOfElements+1)+"\t\t");
             //out.write("*****************************************************\t\t");
             Student tmpStudent = (Student) students.get(numOfElements);
             System.out.println("the received student index: "+tmpStudent.getIndex());
             out.write(tmpStudent.getIndex() + "\t\t");
             
             out.write(tmpStudent.getNameWithIn() + "\t\t");
             out.write(tmpStudent.getFName() +"\t\t");
             out.write(tmpStudent.getDateOfBirth() +"\t\t");
             out.write(tmpStudent.getGender() +"\t\t");
             out.write(tmpStudent.getHomeAddress() +"\t\t");
             out.write(tmpStudent.getTelephone() +"\t\t");
             out.newLine();
             out.flush();
             numOfElements++;
        }

        out.close();
        wrote = true;

        }catch(Exception ex){
            System.out.println("gOT AN eXCEPTIONG WHILE WRITING TO THE FILE.. ");
            wrote = false;
        }

    return wrote;
}


    


    

}