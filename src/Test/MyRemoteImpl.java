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
import java.rmi.server.*;

public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {
    
    public String sayHello(){
        return "Server says 'Hey'";
    }

    
    public MyRemoteImpl() throws RemoteException{
        
    }
    
    public static void main(String[] args){
        
       try{
           MyRemote service = new MyRemoteImpl();
           Naming.rebind("hello", service);
           
           
       }catch(Exception ex){
           ex.printStackTrace();
       }
        
    }
}
