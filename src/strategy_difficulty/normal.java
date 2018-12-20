package strategy_difficulty;

public class normal implements Strategy {

	@Override
	public int speed() {
		// TODO Auto-generated method stub
		return 15;
	}

	@Override
	public int time() {
		// TODO Auto-generated method stub
		return 2000;
	}

	@Override
	public int score() {
		// TODO Auto-generated method stub
		return 2;
	}

}
