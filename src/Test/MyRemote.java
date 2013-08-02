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
public  interface MyRemote extends Remote {
    
    public String sayHello() throws RemoteException;
    

}
