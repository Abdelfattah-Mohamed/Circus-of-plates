package to_come;

import eg.edu.alexu.csd.oop.game.GameObject;

public class ControlledImageObject  implements State{
	
	private int x;
	private int y;
	
	public ControlledImageObject(int x , int y){
		this.x = x;
		this.y = y;
	}
	

	@Override
	public void setX(int mX) {
		this.x = mX;
	}

	@Override
	public void setY(int mY) {
		return;
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
