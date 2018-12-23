package Control.observer;

import Model.Logger.GameLogger;
import Model.strategy_difficulty.Strategy;
import Control.world_class.Circus;

public class Score extends Observer {
	Circus game;
	private Strategy strategy;

	public Score(Circus game) {
		this.game = game;
		this.strategy=game.getDifficulty();
		game.attach(this);
	}

	@Override
	public void update(int num) {
		// TODO this must be called from strategy
		GameLogger.getInstance().log.debug("Score increased by: "+num);
		game.setScore(game.getScore() + strategy.score());
		GameLogger.getInstance().log.info("Score: "+ game.getScore() + strategy.score());
	}
}
