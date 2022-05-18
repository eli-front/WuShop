package classes;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class Editor {
	private ArrayList<CanvasObject> objects = new ArrayList<CanvasObject>();
	
	private CanvasPanel panel = new CanvasPanel(objects);
	
	private JFrame frame;
	
	private FilePicker picker = new FilePicker(this);
	
	public Editor() {
		createWindow();
	}
	
	private void createWindow() {
		frame = new JFrame("Editor");
		frame.add(panel);
		
	    
	    JMenuBar mb = new JMenuBar();
	    
        // create a menu
        JMenu x = new JMenu("File");
  
        // create menuitems
        JMenuItem m1 = new JMenuItem("Import Image");
        
        m1.setAccelerator(KeyStroke.getKeyStroke('I', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
//        JMenuItem m2 = new JMenuItem("MenuItem2");
//        JMenuItem m3 = new JMenuItem("MenuItem3");
  
        // add menu items to menu
        x.add(m1);
//        x.add(m2);
//        x.add(m3);
        
        m1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				picker.createWindow();
				
			}
        	
        });
  
        // add menu to menu bar
        mb.add(x);
        
        frame.setJMenuBar(mb);
        
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	    frame.setSize(750, 750);      
	    frame.setLocationRelativeTo(null);  
	    frame.setVisible(true);	
	}
	
	public void addObject(CanvasObject object) {
		objects.add(object);
		frame.getContentPane().repaint();
	}
	
	
	public JFrame getFrame() {
		return frame;
	}

	
}
