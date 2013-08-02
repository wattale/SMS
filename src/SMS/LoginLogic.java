/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SMS;

/**
 *
 * @author Lasitha
 */
public class LoginLogic {
    DBInteface inteface;
    
    public LoginLogic(){
        inteface = new DBInteface();
}
   
public LoginDetails login(String userName, String passWord){

    LoginDetails details= inteface.login(userName, passWord);

    return details;

}


   



}