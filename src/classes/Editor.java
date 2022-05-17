package classes;

import java.util.ArrayList;

import javax.swing.JFrame;

public class Editor {
	private ArrayList<CanvasObject> objects = new ArrayList<CanvasObject>();
	
	private CanvasPanel panel = new CanvasPanel(objects);
	
	private JFrame frame;
	
	public Editor() {
		createWindow();
	}
	
	private void createWindow() {
		frame = new JFrame("Editor");
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(560, 200);      
	    frame.setLocationRelativeTo(null);  
	    frame.setVisible(true);	
	}
	
	public void addObject(CanvasObject object) {
		objects.add(object);
		frame.getContentPane().repaint();
	}
	

	
}
