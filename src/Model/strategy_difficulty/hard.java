package Model.strategy_difficulty;

import Model.Logger.GameLogger;

public class hard implements Strategy {

	@Override
	public int speed() {
		// TODO Auto-generated method stub
		GameLogger.getInstance().log.debug("game speed set to 10");
		return 10;
	}

	@Override
	public int time() {
		// TODO Auto-generated method stub
		GameLogger.getInstance().log.debug("time set to 1000");
		return 1000;
	}

	@Override
	public int score() {
		// TODO Auto-generated method stub
		GameLogger.getInstance().log.debug("score set to 1");
		return 1;
	}

	@Override
	public int noOfShapes() {
		// TODO Auto-generated method stub
        GameLogger.getInstance().log.debug("no. of shapes is 25");
        return 25;
	}

	@Override
	public int maxHeightOfPlate() {
		// TODO Auto-generated method stub
        GameLogger.getInstance().log.debug("max height of shapes is 150");
		return 150;
	}
	
	@Override
	public int getMaxTime() {
		// TODO Auto-generated method stub
        GameLogger.getInstance().log.debug("max time is 1 sec");
		return 1*60000;
	}

}
