package ponyvet.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ponyvet.gui.articulo.JDFormularioArticulo;

public class ImageUtilities {
    
    public static byte[] getBytesImage(File file) {
        byte[] fileContent = new byte[(int) file.length()];
        FileInputStream inputStream = null;
        try {
            
            inputStream = new FileInputStream(file);
            inputStream.read(fileContent);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JDFormularioArticulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JDFormularioArticulo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(JDFormularioArticulo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return fileContent;
    }
    
}
