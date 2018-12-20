package to_come;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class backGround implements IShape{
	
	BufferedImage bi;

	public backGround(String color) {
		
		try {
			 bi =ImageIO.read(getClass().getResourceAsStream("/"+color+"background.jpg"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public BufferedImage getImage() {

		return bi;
	}

}
