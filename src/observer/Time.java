package observer;

import world_class.Circus;
import strategy_difficulty.Strategy;

public class Time extends Observer {
	private Circus game;
	private Strategy strategy;

	public Time(Circus game) {
		this.game = game;
		this.strategy=game.getDifficulty();
		game.attach(this);
	}

	@Override
	public void update() {
		// TODO this must be called from strategy
		game.setTime(game.getTime() + strategy.time());
	}
}
