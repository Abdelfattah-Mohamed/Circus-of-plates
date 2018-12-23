package world_class;

import momento.*;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import observer.Observer;
import strategy_difficulty.Strategy;
import dynamicLinkage.jarread;
import to_come.*;

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
		// shape = new backGround("0");

		try {

			shape = (IShape) listofClasses.get(4).getConstructor(String.class).newInstance("0");
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ImageObject bg = new ImageObject(0, 0, shape, 0, state);
		constant.add(bg);
		try {
			shape = (IShape) listofClasses.get(5).getConstructor(String.class).newInstance("0");
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		state = new ControlledImageObject(width / 3, (int) (height * 0.6));
		ImageObject clown = new ImageObject(width / 3, (int) (height * 0.6), shape, 0, state);
		try {
			shape = (IShape) listofClasses.get(2).getConstructor(String.class).newInstance("0");
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		state = new ControlledImageObject(clown.getX() - 22, clown.getY() + 37);
		dummyL = new ImageObject(clown.getX() - 22, clown.getY() + 37, shape, 0, state);
		// dummyL.setVisible(false);
		state = new ControlledImageObject(clown.getX() + clown.getWidth() - 55, clown.getY() + 40);
		dummyR = new ImageObject(clown.getX() + clown.getWidth() - 55, clown.getY() + 40, shape, 0, state);
		// dummyR.setVisible(false);
		control.add(clown);
		control.add(dummyL);
		control.add(dummyR);
		controlR.add(dummyR);
		controlL.add(dummyL);
		// TODO first state in memento
		// play some music
		addition_classes.Sound.getInstance().startCircusSound();
		// moving objects (plates)
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
		logic.pooling();
		logic.iterate();
		return logic.refreshLogic();
	}

	@Override
	public String getStatus() {
		return "Score=" + score + "   |   Time="
				+ Math.max(0, (MAX_TIME - (System.currentTimeMillis() - startTime)) / 1000); // update status
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
	}

	public int getMaxY() {
		return this.limitY;
	}
	
	public int getMaxNum() {
		return this.maxNum;
	}
	
}
