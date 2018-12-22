package to_come;

import eg.edu.alexu.csd.oop.game.GameObject;

public class MovingImageObject implements State {

	private int x;
	private int y;

	public MovingImageObject() {

	}

	

	@Override
	public void setX(int mX) {
		this.x = mX;

	}

	@Override
	public void setY(int mY) {
		this.y = mY;

	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return this.x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return this.y;
	}

}
