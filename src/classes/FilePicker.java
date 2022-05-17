package classes;

import java.awt.BorderLayout;
//import java.awt.FlowLayout;
//import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

public class FilePicker {
	private Editor editor;
//	
////   public static void main(String[] args) {
////      createWindow();
////   }
//	
	public FilePicker(Editor editor) {
		this.editor = editor;
	}

   public void createWindow() {    
      JFrame frame = new JFrame("Pick an Image");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      createUI(frame);
      frame.setSize(560, 200);      
      frame.setLocationRelativeTo(null);  
      frame.setVisible(true);
   }
   
   
   private void createUI(final JFrame frame){  
      JPanel panel = new JPanel();
//      LayoutManager layout = new FlowLayout();  
//      panel.setLayout(layout);       
      JButton button = new JButton("Click Me!");
      final JLabel label = new JLabel();
      button.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.addChoosableFileFilter(new ImageFilter());
            fileChooser.setAcceptAllFileFilterUsed(false);

            int option = fileChooser.showOpenDialog(frame);
            if(option == JFileChooser.APPROVE_OPTION){
               File file = fileChooser.getSelectedFile();
               label.setText("File Selected: " + file.getName());
               Picture p = new Picture(file.getAbsolutePath());
               
               editor.addObject(p);
//               image = file.getAbsolutePath();
//               picture.setFileName(file.getName());
//               picture.load(file.getAbsolutePath());
//               picture.explore();
//               System.out.println(picture.);
            } else {
               label.setText("Open command canceled");
            }
         }
      });

      panel.add(button);
      panel.add(label);
      frame.getContentPane().add(panel, BorderLayout.CENTER);    
   }  
} 

class ImageFilter extends FileFilter {
   public final static String JPEG = "jpeg";
   public final static String JPG = "jpg";
   public final static String GIF = "gif";
   public final static String TIFF = "tiff";
   public final static String TIF = "tif";
   public final static String PNG = "png";
   
   @Override
   public boolean accept(File f) {
      if (f.isDirectory()) {
         return true;
      }

      String extension = getExtension(f);
      if (extension != null) {
         if (extension.equals(TIFF) ||
            extension.equals(TIF) ||
            extension.equals(GIF) ||
            extension.equals(JPEG) ||
            extension.equals(JPG) ||
            extension.equals(PNG)) {
            return true;
         } else {
            return false;
         }
      }
      return false;
   }

   @Override
   public String getDescription() {
      return "Image Only";
   }

   String getExtension(File f) {
      String ext = null;
      String s = f.getName();
      int i = s.lastIndexOf('.');
   
      if (i > 0 &&  i < s.length() - 1) {
         ext = s.substring(i+1).toLowerCase();
      }
      return ext;
   } 
}
