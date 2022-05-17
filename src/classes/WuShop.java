package classes;

public class WuShop {
	
	public static void main(String[] args) {
		
		
		Editor e = new Editor();
		
		Size s = new Size(30, 20);
		
		
		e.addObject(new CanvasObject(s, new Position(20, 50)));
		
		e.addObject(new CanvasObject(s, new Position(100, 50)));
		
		FilePicker picker = new FilePicker(e);
		
		picker.createWindow();
	}

}
