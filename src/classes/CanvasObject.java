package classes;

import java.awt.Color;
import java.awt.Graphics;

public class CanvasObject {
	private Size size;
	private Position position;
	private boolean selected;
	private boolean dragging;
	private Side resizeSide;
	
	
	public CanvasObject(Size size, Position position) {
		this.size = size;
		this.position = position;
	}
	
	public enum Side {
		TOP,
		RIGHT,
		BOTTOM,
		LEFT
	}
	
	public Position getPosition() {
		return position;
	}
	
	public Size getSize() {
		return size;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public void setSize(Size size) {
		this.size = size;
	}
	
	
	public void setResizeSide(Side side) {
		this.resizeSide = side;
	}
	
	public Side getResizeSide() {
		return resizeSide;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(position.getX() + Math.min(0, size.getWidth()), position.getY() + Math.min(0, size.getHeight()), Math.abs(size.getWidth()), Math.abs(size.getHeight()));
		renderSelection(g);
	}
	
	protected void renderSelection(Graphics g) {
		if (selected) {
			g.setColor(Color.blue);
			g.drawRect(position.getX()-5, position.getY()-5, size.getWidth()+10, size.getHeight()+10);
		}
	}
	
	public boolean isInside(Position position) {
		
		int w = Math.abs(this.size.getWidth());
		int x = this.position.getX() + Math.min(0, size.getWidth());
		int h =  Math.abs(this.size.getHeight());
		int y = this.position.getY() + Math.min(0, size.getHeight());
		
		return position.getX() >= x && 
				position.getX() <= x + w &&
				position.getY() >= y &&
				position.getY() <= y + h;
	}
	
	public boolean isOnLeftBorder(Position position) {
		return position.getX() + 5 >= this.position.getX() && 
				position.getX() <= this.position.getX() + this.size.getWidth() &&
				position.getY() >= this.position.getY() &&
				position.getY() <= this.position.getY() + this.size.getHeight() && !isInside(position);
	}
	public boolean isOnRightBorder(Position position) {
		return position.getX() >= this.position.getX() && 
				position.getX() - 5 <= this.position.getX() + this.size.getWidth() &&
				position.getY() >= this.position.getY() &&
				position.getY() <= this.position.getY() + this.size.getHeight() && !isInside(position);
	}
	public boolean isOnTopBorder(Position position) {
		return position.getX() >= this.position.getX() && 
				position.getX() <= this.position.getX() + this.size.getWidth() &&
				position.getY() + 5 >= this.position.getY() &&
				position.getY() <= this.position.getY() + this.size.getHeight() && !isInside(position);
	}
	public boolean isOnBottomBorder(Position position) {
		return position.getX() >= this.position.getX() && 
				position.getX() <= this.position.getX() + this.size.getWidth() &&
				position.getY() >= this.position.getY() &&
				position.getY() - 5 <= this.position.getY() + this.size.getHeight() && !isInside(position);
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setDragging(boolean dragging) {
		this.dragging = dragging;
	}
	
	public boolean isDragging() {
		return dragging;
	}
	
	
	

}
