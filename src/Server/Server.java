/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Server;



import SMS.DBInteface;
import SMS.DataBaseCreator;
import SMS.LoginDetails;
import SMS.Message;
import SMS.Student;
import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import java.awt.event.*;
import java.util.Vector;

/**
 *
 * @author Ashan
 */
public class Server {

    DataBaseCreator creator;
    public Server(){
     creator = new DataBaseCreator();
     creator.createDB();


    }


    public static void main(String[] args){
        new Server().go();
    }





    public class ClientHandler implements Runnable{
         private ObjectOutputStream outStream;
         private Socket clientSocket;
         private ObjectInputStream inStream;
         private DBInteface inteface;
         private boolean connected;
        public ClientHandler(Socket clientSocket) throws IOException{
            this.clientSocket = clientSocket;
            outStream = new ObjectOutputStream(clientSocket.getOutputStream());
            inStream = new ObjectInputStream(clientSocket.getInputStream());
            inteface = new DBInteface();
            connected = true;
        }


        public void run(){
            Message message;
            String cmd = null;
            Student student;

            while(connected){
                try{
                    do{
                        message =(Message) inStream.readObject();
                    }while(message.equals(null));
                    cmd = message.getCmd();
                    student = message.getStudent();
                     if(cmd.equalsIgnoreCase("login")){
                        LoginDetails details = inteface.login(student.getIndex(), student.getEntrdPassWord());
                         message = new Message();
                         message.setDetails(details);
                         outStream.writeObject(message);
                         outStream.flush();
                         
                      }else if(cmd.equalsIgnoreCase("saveStudent")){
                          boolean saved = inteface.addStudent(student.getIndex(), student.getNameWithIn(), student.getFName(), student.getDateOfBirth(), student.getGender(), student.getHomeAddress(), student.getTelephone());
                          message.setSaved(saved);
                          outStream.writeObject(message);
                          outStream.flush();

                      }else if(cmd.equalsIgnoreCase("checkStudent")){
                         Student checkedStudent = inteface.getStudent(student.getIndex());
                         message.setStudent(checkedStudent);
                         outStream.writeObject(message);
                         outStream.flush();


                      }else if(cmd.equalsIgnoreCase("upDate")){

                          boolean upDated = inteface.upDateInfo(student);
                          message.setUpDated(upDated);
                          outStream.writeObject(message);
                          outStream.flush();

                      }else if(cmd.equalsIgnoreCase("changePWord")){
                          boolean changed = inteface.changePassWord(student.getIndex(), student.getEntrdPassWord());
                          message.setChangedPWord(changed);
                          outStream.writeObject(message);
                          outStream.flush();

                      }else if(cmd.equalsIgnoreCase("Exit")){
                          connected = false;
                      }else if(cmd.equalsIgnoreCase("generateReports")){
                          System.out.println("Server is generating reports..... ");
                          Vector students = inteface.getStudentRec();
                          outStream.writeObject(students);
                          outStream.flush();


                      }

                }catch(Exception ex){
                   // ex.printStackTrace();
                    connected = false;
                }




            }
            







        }



    }


     public void trayIcon(){
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon =
                new TrayIcon(new ImageIcon("bulb.gif", "tray icon").getImage());
        final SystemTray tray = SystemTray.getSystemTray();
        MenuItem exitItem = new MenuItem("Exit");
        popup.add(exitItem);

        trayIcon.setPopupMenu(popup);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
            return;
        }

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon);
                System.exit(0);
            }
        });
     }


    public void go(){
        this.trayIcon();
        try {
            ServerSocket server = new ServerSocket(5000);
            while(true){
                Socket clientSocket = server.accept();
                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();

            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }




}
