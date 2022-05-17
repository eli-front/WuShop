package classes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Picture extends CanvasObject {
	private String filePath;
	private BufferedImage image;
	
	public Picture(String filePath) {
		super(new Size(100, 100), new Position(50, 50));

		this.filePath = filePath;
	}
	
	public void setFilePath(String filePath) { this.filePath = filePath;}
	
	public String getFilePath() {
		return filePath;
	}
	
	public void render(Graphics g) {
		try {                
	          image = ImageIO.read(new File(filePath));
	          g.drawImage(image, 0, 0, null);
	       } catch (IOException ex) {
	            // handle exception...
	       }
	}
	
}
