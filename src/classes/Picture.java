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
		try {
			this.image = ImageIO.read(new File(this.filePath));
		} catch (IOException ex) {
            System.out.println(ex);
		}
        super.setSize(new Size(image.getWidth(), image.getHeight()));
	}
	
	
	public void render(Graphics g) {
		g.drawImage(image, super.getPosition().getX(), super.getPosition().getY(), super.getSize().getWidth(), super.getSize().getHeight(), null);
		renderSelection(g);
	}
	
}
