package Model.State;

import Model.Logger.GameLogger;

public class ControlledImageObject  implements State{
	
	private int x;
	private int y;
	
	public ControlledImageObject(int x , int y){
		GameLogger.getInstance().log.debug("ControlledImageObject initialized successfully");
		this.x = x;
		this.y = y;
	}
	

	@Override
	public void setX(int mX) {
		GameLogger.getInstance().log.debug("X set");
		this.x = mX;
	}

	@Override
	public void setY(int mY) {
		GameLogger.getInstance().log.debug("Y is fixed");
		return;
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
