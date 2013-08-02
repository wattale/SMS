/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Test;

/**
 *
 * @author Lasitha
 */
import java.rmi.*;
public class MyRemoteClient {
    
    public static void main(String[] args){
        new MyRemoteClient().go();
    }
    
    public void go(){
        try{
            
            MyRemote service =(MyRemote) Naming.lookup("rmi://127.0.0.1/hello");
            String s = service.sayHello();
            System.out.println("The server respond: "+s);
            
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        
    }

}
