package View.Shapes;

import java.awt.image.BufferedImage;

import Model.State.State;
import Model.Logger.GameLogger;
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
	private String color;
	/*public ImageObject(int posX, int posY, String path , State state){
		this(posX, posY, path, 0,state);
//		
	}*/
	
	/*public ImageObject(int posX, int posY, String path, int type , State state){
		this.state = state;
		this.state.setX(posX) ;
		this.state.setY(posY) ;
		this.type = type;
		this.visible = true;
		// create a bunch of buffered images and place into an array, to be displayed sequentially
		
		try {
	spriteImages[0] = ImageIO.read(getClass().getResourceAsStream(path));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}*/
	
	public ImageObject(int posX, int posY, IShape image, int type , State state){
		this.state = state;
		this.state.setX(posX) ;
		this.state.setY(posY) ;
		this.type = type;
		this.visible = true;
		// create a bunch of buffered images and place into an array, to be displayed sequentially
			color =  image.getColor();
			spriteImages[0] = image.getImage();
        GameLogger.getInstance().log.debug("ImageObject initialized Successfully");
	}

	@Override
	public  int getX() {
        GameLogger.getInstance().log.debug("X returned");
		return state.getX();
	}

	@Override
	public  void setX(int mX) {
        GameLogger.getInstance().log.debug("X set");
		state.setX(mX);
		return;
	}

	@Override
	public  int getY() {
        GameLogger.getInstance().log.debug("Y returned");
		return state.getY();
	}

	@Override
	public  void setY(int mY) {
        GameLogger.getInstance().log.debug("Y set");
		state.setY(mY);
		return;
	}

	@Override
	public BufferedImage[] getSpriteImages() {
        GameLogger.getInstance().log.debug("sprites returned");
		return spriteImages;
	}

	@Override
	public int getWidth(){
        GameLogger.getInstance().log.debug("image width returned");
		return spriteImages[0].getWidth();
	}

	@Override
	public int getHeight() {
        GameLogger.getInstance().log.debug("image height returned");
		return spriteImages[0].getHeight();
	}

	@Override
	public boolean isVisible() {
        GameLogger.getInstance().log.debug("ImageObject isVisible: "+ visible);
		return visible;
	}
	
	public void setState( State state) {
		state.setX(this.state.getX() );
		state.setY(this.state.getY() );
        GameLogger.getInstance().log.debug("State set");
		this.state = state;
	}
	
	public String getColor() {
        GameLogger.getInstance().log.debug("color returned");
		return color;
	}
	
}
