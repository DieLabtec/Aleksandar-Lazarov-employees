import javax.swing.*;
import java.io.File;

public class ButtonFunctionality {
    public static File takePath(JFileChooser fileChoser){
        File file = new File(fileChoser.getSelectedFile().getAbsolutePath());
        System.out.println(file);
        return file;
    }
}
