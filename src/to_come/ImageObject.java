package to_come;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import eg.edu.alexu.csd.oop.game.GameObject;

public  class ImageObject implements GameObject{
	private State state ;
	private static final int MAX_MSTATE = 1;
	// an array of sprite images that are drawn sequentially
	private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
	//private int x;
	//private int y;
	private boolean visible;
	private int type;
	
	public ImageObject(int posX, int posY, String path , State state){
		this(posX, posY, path, 0,state);
//		try {
//			spriteImages[1] = ImageIO.read(getClass().getResourceAsStream("/plate.png"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public ImageObject(int posX, int posY, String path, int type , State state){
		this.state = state;
		this.state.setX(posX) ;
		this.state.setY(posY) ;
		this.type = type;
		this.visible = true;
		// create a bunch of buffered images and place into an array, to be displayed sequentially
		try {
			spriteImages[0] = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public  int getX() {
		return state.getX();
	}

	@Override
	public  void setX(int mX) {
		 
		state.setX(mX);
		return;
	}

	@Override
	public  int getY() {
		return state.getY();
	}

	@Override
	public  void setY(int mY) {
		state.setY(mY);
		return;
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		return spriteImages;
	}

	@Override
	public int getWidth(){
		return spriteImages[0].getWidth();
	}

	@Override
	public int getHeight() {
		return spriteImages[0].getHeight();
	}

	@Override
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible){
		this.visible = visible;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public void setState( State state) {
		state.setX(this.state.getX() );
		state.setY(this.state.getY() );
		this.state = state;
	}
	
}
