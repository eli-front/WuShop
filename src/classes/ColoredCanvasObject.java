package classes;

import java.awt.Color;
import java.awt.Graphics;

public class ColoredCanvasObject extends CanvasObject {
	
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

}
