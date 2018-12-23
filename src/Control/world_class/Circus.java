package Control.world_class;

import Control.Pool.MovingPool;
import Model.State.ConstantImageObject;
import Model.State.ControlledImageObject;
import Model.State.State;
import Control.momento.Caretaker;
import Control.momento.Originator;
import Model.Logger.GameLogger;
import View.addition_classes.Sound;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import Control.observer.Observer;
import Model.strategy_difficulty.Strategy;
import Control.dynamicLinkage.jarread;
import View.Shapes.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Circus implements World {

	private static int MAX_TIME ; 
	private int score = 0;
	private long startTime = System.currentTimeMillis();
	private final int width;
	private final int height;
	private Strategy difficulty;
	private ArrayList<GameObject> constant = new ArrayList<GameObject>();
	private ArrayList<GameObject> moving = new ArrayList<GameObject>();
	private ArrayList<GameObject> control = new ArrayList<GameObject>();
	private ArrayList<GameObject> controlL = new ArrayList<GameObject>();
	private ArrayList<GameObject> controlR = new ArrayList<GameObject>();
	private List<Observer> observers = new ArrayList<Observer>();
	private static Caretaker caretaker = new Caretaker();
	private static Originator originator = new Originator();
	private int currentMementoL = 0;
	private int currentMementoR = 0;
	private ImageObject dummyL;
	private ImageObject dummyR;
	private IFacad logic;
	private int limitY;
	private int maxNum;
	public List<Observer> getObservers() {
		return observers;
	}

	public ArrayList<GameObject> getMoving() {
		return moving;
	}

	public ArrayList<GameObject> getControl() {
		return control;
	}

	public static int getMaxTime() {
		return MAX_TIME;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setControlL(ArrayList<GameObject> controlL) {
		this.controlL = controlL;
	}

	public void setControlR(ArrayList<GameObject> controlR) {
		this.controlR = controlR;
	}

	public ArrayList<GameObject> getControlL() {
		return controlL;
	}

	public ArrayList<GameObject> getControlR() {
		return controlR;
	}

	public Strategy getDifficulty() {
		return difficulty;
	}

	public int getCurrentMementoL() {
		return currentMementoL;
	}

	public int getCurrentMementoR() {
		return currentMementoR;
	}

	public static Caretaker getCaretaker() {
		return caretaker;
	}

	public void setCurrentMementoL(int currentMementoL) {
		this.currentMementoL = currentMementoL;
	}

	public void setCurrentMementoR(int currentMementoR) {
		this.currentMementoR = currentMementoR;
	}

	public static Originator getOriginator() {
		return originator;
	}

	public Circus(int width, int height, Strategy difficulty) {

		this.width = width;
		this.height = height;
		this.difficulty = difficulty;
		this.MAX_TIME = this.difficulty.getMaxTime();
		this.limitY=this.difficulty.maxHeightOfPlate();
		this.maxNum=this.difficulty.noOfShapes();
		State state;
		jarread x = new jarread();
		List<Class<?>> listofClasses = x.getCrunchifyClassNamesFromJar("JAR_F.jar");
		IShape shape = null;
		state = new ConstantImageObject();
		GameLogger.getInstance().log.debug("Circus World initialized Successfully!\tSize: "+width +"x"+height+"\tdifficulty: "+difficulty.getClass().getName()+"\t");
		// shape = new backGround("0");

		try {
			shape = (IShape) listofClasses.get(0).getConstructor(String.class).newInstance("0");
			GameLogger.getInstance().log.debug("View.Shapes list loaded");
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			GameLogger.getInstance().log.error("FAILED TO LOAD SHAPES");
			e.printStackTrace();
		}

		ImageObject bg = new ImageObject(0, 0, shape, 0, state);
		constant.add(bg);
		try {
			shape = (IShape) listofClasses.get(4).getConstructor(String.class).newInstance("0");
			GameLogger.getInstance().log.debug("Background loaded Successfully");
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			GameLogger.getInstance().log.error("failed to load the background");
			e.printStackTrace();
		}
		state = new ControlledImageObject(width / 3, (int) (height * 0.6));
		ImageObject clown = new ImageObject(width / 3, (int) (height * 0.6), shape, 0, state);
		try {
			shape = (IShape) listofClasses.get(7).getConstructor(String.class).newInstance("0");
			GameLogger.getInstance().log.debug("Clown loaded Successfully");
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			GameLogger.getInstance().log.error("FAILED TO LOAD CLOWN");
			e.printStackTrace();
		}
		state = new ControlledImageObject(clown.getX() - 22, clown.getY() + 37);
		dummyL = new ImageObject(clown.getX() - 22, clown.getY() + 37, shape, 0, state);
		GameLogger.getInstance().log.debug("Dummy shape generated");
		// dummyL.setVisible(false);
		state = new ControlledImageObject(clown.getX() + clown.getWidth() - 55, clown.getY() + 40);
		dummyR = new ImageObject(clown.getX() + clown.getWidth() - 55, clown.getY() + 40, shape, 0, state);
		GameLogger.getInstance().log.debug("Dummy shape generated");
		// dummyR.setVisible(false);
		GameLogger.getInstance().log.debug("Clown added to the controlled list");
		control.add(clown);
		control.add(dummyL);
		control.add(dummyR);
		controlR.add(dummyR);
		controlL.add(dummyL);
		// TODO first state in memento
		// play some music
		Sound.getInstance().startTheme();
		// moving objects (plates)
		GameLogger.getInstance().log.debug("Processing moving shapes");
		GameLogger.getInstance().log.info("Game Started");
		MovingPool mpl = MovingPool.getInstance();
		mpl.setNum(maxNum);
		mpl.setPool(width, height);
		moving = mpl.usePool();
		logic = new Facad(this);
	}

	@Override
	public List<GameObject> getConstantObjects() {
		return constant;
	}

	@Override
	public List<GameObject> getMovableObjects() {
		return moving;
	}

	@Override
	public List<GameObject> getControlableObjects() {
		return control;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	public ImageObject getDummyL() {
		return dummyL;
	}

	public ImageObject getDummyR() {
		return dummyR;
	}

	@Override
	public boolean refresh() {
		GameLogger.getInstance().log.debug("Game Refreshed");
		logic.pooling();
		logic.iterate();
		return logic.refreshLogic();
	}

	@Override
	public String getStatus() {
		String s = "Score=" + score + "   |   Time="
				+ Math.max(0, (MAX_TIME - (System.currentTimeMillis() - startTime)) / 1000);
		GameLogger.getInstance().log.debug("Status: \"Score=\" + score + \"   |   Time=\"\n" +
				"\t\t\t\t+ Math.max(0, (MAX_TIME - (System.currentTimeMillis() - startTime)) / 1000)");
		return s; // update status
	}

	@Override
	public int getSpeed() {
		return difficulty.speed();
	}

	@Override
	public int getControlSpeed() {
		return 20;
	}

	public void attach(Observer observer) {
		// TODO Auto-generated method stub
		GameLogger.getInstance().log.debug("Observer attached");
		observers.add(observer);
	}

	public int getTime() {
		// TODO Auto-generated method stub
		return (int) this.MAX_TIME;
	}

	public int getScore() {
		// TODO Auto-generated method stub
		return this.score;
	}
	
	public Circus getCircus() {
		return this;
	}

	public void setTime(int sTime) {
		MAX_TIME = sTime;
	}

	public void setScore(int score) {
		score = Math.max(score, 0);
		this.score = score;
		GameLogger.getInstance().log.debug("score set: "+score);
	}

	public int getMaxY() {
		return this.limitY;
	}
	
	public int getMaxNum() {
		return this.maxNum;
	}
	
}
