package classes;

import java.awt.Graphics;

public class CanvasObject {
	Size size;
	Position position;
	
	public CanvasObject(Size size, Position position) {
		this.size = size;
		this.position = position;
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
	
	public void render(Graphics g) {
		g.fillRect(position.getX(), position.getY(), size.getWidth(), size.getHeight());
	}
	
	public boolean isInside(Position position) {
		return position.getX() >= this.position.getX() && 
				position.getX() <= this.position.getX() + this.size.getWidth() &&
				position.getY() >= this.position.getY() &&
				position.getY() <= this.position.getY() + this.size.getHeight();
	}
	
	

}
