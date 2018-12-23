package strategy_difficulty;

public interface Strategy {
	/**
	 * 
	 * @return speed of moving objects
	 */
	public int speed();

	/**
	 * 
	 * @return change in starting time.
	 */
	public int time();

	/**
	 * 
	 * @return increasing of score at three mode.
	 */
	public int score();

	/**
	 * 
	 * @return number of shapes falling in any mode.
	 */
	public int noOfShapes();

	/**
	 * 
	 * @return return max height in any mode if exceed then game over.
	 */
	public int maxHeightOfPlate();
	/**
	 * 
	 * @return the max time started
	 */
	public int getMaxTime();

}
