/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SMS;

/**
 *
 * @author Lasitha
 */
import java.awt.*;
import java.io.File;
import javax.swing.*;
public class Mycomponent extends JPanel {
    private File selected;
    
    public Mycomponent(File selected){
        this.selected = selected;
    }
    
    public void paintComponent(Graphics g){
        
        Image image = new ImageIcon(selected.getAbsolutePath()).getImage();
        g.drawImage(image, 1, 1, this);
        
    }
    
    

}
