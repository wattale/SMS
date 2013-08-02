package SMS;

import java.io.File;
import javax.swing.filechooser.FileFilter;


public class ImageFilter extends FileFilter {

    private final String[] imgFormat =
            new String[]{"jpg", "png", "gif","jpeg","bmp"};

    public boolean accept(File f) {
        if(f.isDirectory())
           return true;
        
        for (String ext : imgFormat) {
            if (f.getName().toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }
    
    public String getDescription() {
        return ".jpg";
    }
}