/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SMS;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author mb
 */
public class TextFilter extends FileFilter{

    private final String imgFormat ="txt";

    public boolean accept(File f) {
        if(f.isDirectory()&& f.getName().toLowerCase().endsWith(imgFormat)){
           return true;
        }

        return false;
    }

    public String getDescription() {
        return null;
    }



}
