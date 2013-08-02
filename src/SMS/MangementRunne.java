/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SMS;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Lasitha
 */
public class MangementRunne {
    
    public static void main(String[] args) throws IOException{
        
       
        ClientMnpltn clientMan = new ClientMnpltn(new Socket("localhost",5000));
        LogIn logIn = new LogIn(clientMan);
        logIn.setVisible(true);
        
        
        
    }
    

}
