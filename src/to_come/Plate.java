package to_come;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Plate  implements IShape{
	
	private BufferedImage bi;
	private String color;
	public Plate(String color) {
		this.color=color;
		try {
			bi =ImageIO.read(getClass().getResourceAsStream("/"+color+"plate.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public BufferedImage getImage() {
		
		return bi;
	}

	@Override
	public String getColor() {
		return color;
	}


    

	

}
