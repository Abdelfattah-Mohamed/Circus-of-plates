package Model.State;

import Model.Logger.GameLogger;

public class ConstantImageObject  implements State{
	
	private int x;
	private int y;


    public ConstantImageObject() {
        GameLogger.getInstance().log.debug("ConstantImageObject initialized successfully");
    }

    @Override
	public void setX(int mX) {
        GameLogger.getInstance().log.debug("Constant Image X is Constant");
		return;
	}

	@Override
	public void setY(int mY) {
        GameLogger.getInstance().log.debug("Constant Image Y is Constant");
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
