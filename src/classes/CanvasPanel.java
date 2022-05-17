package classes;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class CanvasPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<CanvasObject> objects;
	
	public CanvasPanel(ArrayList<CanvasObject> objects ) {
		this.objects = objects;
		
	}
	
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (CanvasObject o : objects) {
        	o.render(g);
        }
    }
	
	

}
