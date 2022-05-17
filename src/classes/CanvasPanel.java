package classes;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class CanvasPanel extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<CanvasObject> objects;
	
	public CanvasPanel(ArrayList<CanvasObject> objects ) {
		this.objects = objects;
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
	}
	
	
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (CanvasObject o : objects) {
        	o.render(g);
        }
    }


	@Override
	public void mouseClicked(MouseEvent e) {
		
		
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		
//		Position clickPosition = new Position(e.getX(), e.getY());
//				
//		for (CanvasObject o : objects) {
//			
//			
//			if (o.isInside(clickPosition)) {
//				o.setSize(new Size(o.getSize().getWidth() + 5, o.getSize().getHeight() + 5));
//				super.repaint();
//			}
//		}
		
		// TODO Select Object
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		
		
		
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	



	@Override
	public void mouseDragged(MouseEvent e) {
		Position position = new Position(e.getX(), e.getY());
		
		
		for (CanvasObject o : objects) {
			if (o.isInside(position)) {
				o.setPosition(new Position(position.getX() - o.getSize().getWidth()/2, position.getY() - o.getSize().getHeight()/2));
				
			}
		}
		super.repaint();

	}



	@Override
	public void mouseMoved(MouseEvent e) {
		
		
		Position position = new Position(e.getX(), e.getY());
		
		Cursor c = new Cursor(Cursor.DEFAULT_CURSOR);
		
		for (CanvasObject o : objects) {
			if (o.isInside(position)) {
				c = new Cursor(Cursor.HAND_CURSOR);
			}
		}
		
		setCursor(c);
		
	}
	

	
	

}
