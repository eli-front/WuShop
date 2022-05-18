package classes;

public class WuShop {
	
	public static void main(String[] args) {
		
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		
		
		Editor e = new Editor();
		
		
		e.addObject(new CanvasObject(new Size(30, 20), new Position(20, 50)));
		
		e.addObject(new CanvasObject(new Size(30, 20), new Position(100, 50)));

	}

}
