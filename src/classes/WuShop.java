package classes;

import java.awt.Color;

public class WuShop {
	
	public static void main(String[] args) {
		
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		
		
		Editor e = new Editor();
		
		
		e.addObject(new ColoredCanvasObject(new Size(30, 20), new Position(20, 50), Color.red));
		
		e.addObject(new ColoredCanvasObject(new Size(30, 20), new Position(100, 50), Color.green));

	}

}
