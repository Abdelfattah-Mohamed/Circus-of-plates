package to_come;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Dice implements IShape {

	BufferedImage bi;
	public Dice(String color) {
		
			try {
				 bi =ImageIO.read(getClass().getResourceAsStream("/"+color+"dice.png"));
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}

	@Override
	public BufferedImage getImage() {
		
		return bi;
	}

    
}