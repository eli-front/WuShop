package classes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;

import classes.CanvasObject.Side;

public class CanvasPanel extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	private JButton colorButton;
	
	private DrawingTool draw;

	
	private ArrayList<CanvasObject> objects;
	
	public CanvasPanel(ArrayList<CanvasObject> objects ) {
		this.objects = objects;
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		colorButton = new JButton("Choose Color");
        colorButton.setPreferredSize(new Dimension(100, 50));
        
        colorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Color currentColor = Color.white;
				
				ColoredCanvasObject currentObject = null;
				
				for (CanvasObject o : objects) {
					if (o instanceof Colored && o.isSelected()) {
						currentColor = ((Colored) o).getColor();
						
						if (o instanceof ColoredCanvasObject) {
							currentObject = ((ColoredCanvasObject) o);
						}
					}
				}
				
				Color c = JColorChooser.showDialog(null, "Choose a Color", currentColor);
				
				currentObject.setColor(c);
				
				repaint();
				
			}
        	
        });
        
        		
	}
	
	public void saveImage(String name,String type) {
		BufferedImage image = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = image.createGraphics();
		paint(g2);
		try{
			ImageIO.write(image, type, new File(name+"."+type));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	public void toggleDraw() {
		if (draw == null) {
			this.draw = new DrawingTool(new Size(100,100), new Position(0,0), Color.black);
			objects.add(draw);
		} else {
			this.draw = null;
		}
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
		
		if (draw != null) {
			return;
		}
				
		Position clickPosition = new Position(e.getX(), e.getY());
		for (CanvasObject o : objects) {
			if (o.isInside(clickPosition)) {
				o.setSelected(true);
				
				if (o instanceof ColoredCanvasObject) {
					this.add(colorButton);
					this.revalidate();
				}
			} 
		}
		
		super.repaint();
	}
	
	private ArrayList<CanvasObject> reversed() {
		ArrayList<CanvasObject> newList = new ArrayList<>();
		
		for (int i = objects.size() - 1; i >= 0; i--) {
			newList.add(objects.get(i));
		}
		
		return newList;
	}


	@Override
	public void mousePressed(MouseEvent e) {
		
		if (draw != null) {
			return;
		}
		
		boolean isSelected = false;
		
		Position position = new Position(e.getX(), e.getY());
		for (CanvasObject o : reversed()) {
			if (o.isInside(position)) {
				o.setDragging(true);
				o.setSelected(true);
				if (o instanceof ColoredCanvasObject) {
					isSelected = true;
					this.add(colorButton);
					this.revalidate();
				}
				break;
			} else if (o.isOnRightBorder(position)) {
				o.setResizeSide(Side.RIGHT);
			}
			else if (o.isOnTopBorder(position)) {
				o.setResizeSide(Side.TOP);
			}
			else if (o.isOnBottomBorder(position)) {
				o.setResizeSide(Side.BOTTOM);
			}
			else if (o.isOnLeftBorder(position)) {
				o.setResizeSide(Side.LEFT);
			} else {
				o.setSelected(false);
			}
		}
		
		if (!isSelected) {
			this.remove(colorButton);
			this.revalidate();
		}
		
		
		super.repaint();
						
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		
		if (draw != null) {
			return;
		}
		
		for (CanvasObject o : objects) {
			o.setDragging(false);
			o.setResizeSide(null);
		}
		
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
		
		if (draw != null) {
			draw.addPoint(position);
			super.repaint();
			return;
		}
		
		
		for (CanvasObject o : objects) {
			if (o.isDragging()) {
				o.setPosition(new Position(position.getX() - o.getSize().getWidth()/2, position.getY() - o.getSize().getHeight()/2));
				
			}
			if (o.getResizeSide() != null) {
				switch (o.getResizeSide()) {
				case BOTTOM:
					int yb = o.getPosition().getY();						
					o.getSize().setHeight(position.getY() - yb);		
					break;
				case LEFT:
					o.getSize().setWidth(o.getSize().getWidth() + o.getPosition().getX() - position.getX());
					o.getPosition().setX(position.getX());
					break;
				case RIGHT:
					int xr = o.getPosition().getX();						
					o.getSize().setWidth(position.getX() - xr);		
					break;
				case TOP:
					o.getSize().setHeight(o.getSize().getHeight() + o.getPosition().getY() - position.getY());
					o.getPosition().setY(position.getY());
					break;
				default:
					break;
					
				}
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
			
			if (o.isOnRightBorder(position)) {
				c = new Cursor(Cursor.E_RESIZE_CURSOR);
			}
			else if (o.isOnTopBorder(position)) {
				c = new Cursor(Cursor.N_RESIZE_CURSOR);
			}
			else if (o.isOnBottomBorder(position)) {
				c = new Cursor(Cursor.S_RESIZE_CURSOR);
			}
			else if (o.isOnLeftBorder(position)) {
				c = new Cursor(Cursor.W_RESIZE_CURSOR);
			}
		}
		
		setCursor(c);
		
	}

	
	

}
