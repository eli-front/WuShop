package classes;

import java.awt.Color;
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
	
	
	private FilePicker filePicker = new FilePicker(this);
	
	public Editor() {
		createWindow();
	}
	
	private void createWindow() {
		frame = new JFrame("Editor");
		panel.setBounds(0, 0, 100, 100);
		frame.add(panel);
		
	    
	    JMenuBar mb = new JMenuBar();
	    
        // create a menu
        JMenu x = new JMenu("File");
  
        // create menuitems
        JMenuItem m1 = new JMenuItem("Import Image");
        JMenuItem m2 = new JMenuItem("New Object");
        JMenuItem m3 = new JMenuItem("Toggle Draw");
        JMenuItem m4 = new JMenuItem("Export");

        
        m1.setAccelerator(KeyStroke.getKeyStroke('I', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        m2.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        m3.setAccelerator(KeyStroke.getKeyStroke('D', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        m4.setAccelerator(KeyStroke.getKeyStroke('E', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));

  
        // add menu items to menu
        x.add(m1);
        x.add(m2);
        x.add(m3);
        x.add(m4);

        
        m1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				filePicker.createWindow();
			}
        });
        
        m2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addObject(new ColoredCanvasObject(new Size(100, 100), new Position(50, 50), Color.black));
			}
        	
        });
        
        m3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.toggleDraw();
			}
        	
        });
        
        m4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.saveImage("WuImage", "png");
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
