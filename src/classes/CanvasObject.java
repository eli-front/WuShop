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
	
	

}
