package strategy_difficulty;

import Logger.GameLogger;

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

}
