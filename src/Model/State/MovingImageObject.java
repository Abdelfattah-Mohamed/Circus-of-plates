package Model.State;

import Model.Logger.GameLogger;

public class MovingImageObject  implements State{
	
	private int x;
	private int y;
	
	
	

	@Override
	public void setX(int mX) {
		this.x = mX;
		GameLogger.getInstance().log.debug("X set");
	}

	@Override
	public void setY(int mY) {
		this.y = mY;
		GameLogger.getInstance().log.debug("Y set");
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		GameLogger.getInstance().log.debug("X returned");
		return this.x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		GameLogger.getInstance().log.debug("Y returned");
		return this.y;
	}

}
