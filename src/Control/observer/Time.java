package Control.observer;

import Model.Logger.GameLogger;
import Control.world_class.Circus;
import Model.strategy_difficulty.Strategy;

public class Time extends Observer {
	private Circus game;
	private Strategy strategy;

	public Time(Circus game) {
		this.game = game;
		this.strategy=game.getDifficulty();
		game.attach(this);
	}

	@Override
	public void update(int num) {
		// TODO this must be called from strategy
		game.setTime(game.getTime() + strategy.time());
		GameLogger.getInstance().log.debug("time updated: "+ game.getTime() + strategy.time());
	}
}
