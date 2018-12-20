package to_come;

import eg.edu.alexu.csd.oop.game.GameObject;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Clown implements IShape {

	BufferedImage bi;

	public Clown(String color) {
		
		try {
			 bi =ImageIO.read(getClass().getResourceAsStream("/"+color+"clown.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public BufferedImage getImage() {

		return bi;
	}
}
