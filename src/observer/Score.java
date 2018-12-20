package observer;

import strategy_difficulty.Strategy;
import world_class.Circus;

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
		game.setScore(game.getScore() + strategy.score());
	}
}
