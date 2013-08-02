/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SMS;

import java.io.Serializable;

/**
 *
 * @author Lasitha
 */
public class LoginDetails implements Serializable{

    private boolean ifExcist;
    private String type;
    
    public LoginDetails(boolean ifExcist, String type){
        this.ifExcist = ifExcist;
        this.type = type;
    }
    
    public boolean getIfExcist(){
        return ifExcist;
    }
    
    public String getType(){
        return type;
    }

}
