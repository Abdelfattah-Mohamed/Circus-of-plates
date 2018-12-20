package to_come;

import eg.edu.alexu.csd.oop.game.GameObject;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Clown implements IShape {

	BufferedImage bi;
	String color;
	public Clown(String color) {
		this.color=color;
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

	@Override
	public String getColor() {
		// TODO Auto-generated method stub
		return color;
	}
}
