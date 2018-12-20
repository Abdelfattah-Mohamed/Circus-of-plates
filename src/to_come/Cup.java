package to_come;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cup implements IShape {

    

	private BufferedImage bi;
	private String color;
	public Cup(String color) {
		this.color=color;
		try {
			 bi =ImageIO.read(getClass().getResourceAsStream("/"+color+"cup.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public BufferedImage getImage() {
		
		return bi;
	}

	@Override
	public String getColor() {
		// TODO Auto-generated method stub
		return color;
	}
}
