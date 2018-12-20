package observer;

import strategy_difficulty.Strategy;
import world_class.Circus;

public class Sound extends Observer{
	private Circus game;

	public Sound(Circus game) {
		this.game = game;
		game.attach(this);
	}

	@Override
	public void update() {
		// TODO this must be called from strategy
		// update sound here.
	}
}
