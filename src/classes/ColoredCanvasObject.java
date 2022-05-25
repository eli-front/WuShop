package classes;

import java.awt.Color;
import java.awt.Graphics;

public class ColoredCanvasObject extends CanvasObject implements Colored {
	
	private Color color;

	public ColoredCanvasObject(Size size, Position position, Color color) {
		super(size, position);
		this.color = color;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(color);
		super.render(g);
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color c) {
		color = c;
	}

}
