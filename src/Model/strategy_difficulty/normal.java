package Model.strategy_difficulty;

import Model.Logger.GameLogger;

public class normal implements Strategy {

	@Override
	public int speed() {
		// TODO Auto-generated method stub
		GameLogger.getInstance().log.debug("game speed set to 15");
		return 15;
	}

	@Override
	public int time() {
		// TODO Auto-generated method stub
		GameLogger.getInstance().log.debug("time set to 2000");
		return 2000;
	}

	@Override
	public int score() {
		// TODO Auto-generated method stub
		GameLogger.getInstance().log.debug("score set to 2");
		return 2;
	}

	@Override
	public int noOfShapes() {
		// TODO Auto-generated method stub
        GameLogger.getInstance().log.debug("no. of shapes is " + 30);
		return 30;
	}

	@Override
	public int maxHeightOfPlate() {
		// TODO Auto-generated method stub
        GameLogger.getInstance().log.debug("no. of shapes is " + 30);
		return 100;
	}

	@Override
	public int getMaxTime() {
		// TODO Auto-generated method stub
        GameLogger.getInstance().log.debug("maximum time is 2 seconds");
        return 2*60000;
	}

}
