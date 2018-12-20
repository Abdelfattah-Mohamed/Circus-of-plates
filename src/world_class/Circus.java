package world_class;

import momento.*;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import observer.*;
import observer.Observer;
import strategy_difficulty.Strategy;
import iterator.Iterator;
import iterator.Iterator_concrete;
import to_come.ConstantImageObject;
import to_come.ControlledImageObject;
import to_come.ImageObject;
import to_come.MovingImageObject;
import to_come.State;

import java.util.*;

public class Circus implements World {

	private static int MAX_TIME = 1 * 60 * 1000; // 1 minute
	private int score = 0;
	private long startTime = System.currentTimeMillis();
	private final int width;
	private final int height;
	private Strategy difficulty;
	private Observer t;
	private Observer s;
	private Observer sc;
	private boolean flag = false;
	private ArrayList<GameObject> constant = new ArrayList<GameObject>();
	private ArrayList<GameObject> moving = new ArrayList<GameObject>();
	private ArrayList<GameObject> control = new ArrayList<GameObject>();
	private ArrayList<GameObject> controlL = new ArrayList<GameObject>();
	private ArrayList<GameObject> controlR = new ArrayList<GameObject>();
	private List<Observer> observers = new ArrayList<Observer>();
	private static Caretaker caretaker = new Caretaker();
	private static Originator originator = new Originator();
	private Iterator_concrete xx;
	private int currentMementoL = 0;
	private int currentMementoR = 0;
	int countL = 0;
	int countR = 0;

	public Circus(int width, int height, Strategy difficulty) {
		this.width = width;
		this.height = height;
		this.difficulty = difficulty;
		State state;
		state = new ConstantImageObject();
		ImageObject bg = new ImageObject(0, 0, "/aaa.jpg", state);
		constant.add(bg);
		state = new ControlledImageObject(width / 3, (int) (height * 0.6));
		ImageObject clown = new ImageObject(width / 3, (int) (height * 0.6), "/clown.png", state);
		state = new ControlledImageObject(clown.getX() - 17, clown.getY() + 37);
		ImageObject dummyL = new ImageObject(clown.getX() - 17, clown.getY() + 37, "/plate.png", state);
		dummyL.setVisible(false);
		state = new ControlledImageObject(clown.getX() + clown.getWidth() - 50, clown.getY() + 40);
		ImageObject dummyR = new ImageObject(clown.getX() + clown.getWidth() - 50, clown.getY() + 40, "/plate.png",
				state);
		dummyR.setVisible(false);
		control.add(clown);
		control.add(dummyL);
		control.add(dummyR);

		controlR.add(dummyR);
		controlL.add(dummyL);
		// TODO first state in memento
		saveStateL();
		saveStateR();
		// play some music
		addition_classes.Sound.getInstance().startCircusSound();
		// moving objects (plates)
		for (int i = 0; i < 25; i++) {
			state = new MovingImageObject();
			moving.add(new ImageObject((int) (Math.random() * width), (int) (Math.random() * height / 2), "/plate.png",
					state));
		}
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

	@Override

	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public boolean refresh() {
		if (!flag) {
			t = new Time(this);
			s = new Sound(this);
			sc = new Score(this);
			flag = true;
		}
		boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME; // time end and game over

		System.out.print(System.currentTimeMillis() +" " );
		System.out.print(startTime+" ");
		System.out.println(MAX_TIME);

		// last plate in both stacks0
		GameObject lastplateL = controlL.get(controlL.size() - 1);
		GameObject lastplateR = controlR.get(controlR.size() - 1);
		xx = new Iterator_concrete(moving); // handle moving plates here

		for (Iterator iter = xx.getIterator(0); iter.hasNext();) {
			ImageObject o = (ImageObject) iter.next();
			o.setY((o.getY() + 1));
			if (o.getY() + 100 >= getHeight()) {
				// reuse the plate in another position
				o.setY(-1 * (int) (Math.random() * getHeight()));
				o.setX((int) (Math.random() * getWidth()));
			}
			// o.setX(o.getX() + (Math.random() > 0.5 ? 2 : -2));
			// al taba2 b3dha l taba2 ele fo2
			if (!timeout & o.isVisible() && (intersect(o, lastplateL))) {
				moving.remove(o);
				State state = new ControlledImageObject(o.getX(), o.getY());
				o.setState(state);
				control.add(o);
				controlL.add(o);
				saveStateL();
				countL++;
				if (countL % 3 == 0) {

					this.notifyAllObserver();
				}


			}
			if (!timeout & o.isVisible() && (intersect(o, lastplateR))) {
				// clown caught a plate here on the right side
				moving.remove(o);
				State state = new ControlledImageObject(o.getX(), o.getY());
				((ImageObject) o).setState(state);
				control.add(o);
				controlR.add(o);
				saveStateR();
				countR++;
				if (countR % 3 == 0) {
					if (currentMementoR >= 1) {
						currentMementoR -= 3;
						for (int k = 0; k < 3; k++) {
							ImageObject s = (ImageObject) controlR.get(controlR.size() - k - 1);
							s.setVisible(false);
						}
						controlR = (ArrayList<GameObject>) originator
								.restoreFromMemento(caretaker.getMementoR(currentMementoR - 1)).clone();
					}
					this.notifyAllObserver();
				}

				// this.notifyAllObserver();
			}
		}

		return !timeout;

	}

	// al taba2 b3dha l taba2 ele fo2
	// m7taga design pattern ?
	private boolean intersect(GameObject o1, GameObject o2) {
		int midx = (o1.getX() + o1.getX() + o1.getWidth()) / 2;
		// System.out.println(o1.getX());
		// System.out.println(o1.getWidth());
		/* && o1.getY() + o1.getHeight() <= o2.getY()+2 */
		if (o1.getY() + o1.getHeight() == o2.getY() && (midx <= o2.getX() + o2.getWidth() && midx >= o2.getX())) {
			System.out.println("true");
			return true;
		}

		return false;
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

	public void notifyAllObserver() {
		for (Observer observer : observers) {
			observer.update();
		}
	}

	public void setTime(int sTime) {
		MAX_TIME = sTime;
	}

	public void setScore(int score) {
		this.score = score;
	}

	private void saveStateL() {
		// TODO Auto-generated method stub
		originator.set((ArrayList<GameObject>) controlL.clone());
		caretaker.addMementoL(originator.storeInMemento());
		currentMementoL++;
	}

	private void saveStateR() {
		// TODO Auto-generated method stub
		originator.set((ArrayList<GameObject>) controlR.clone());
		caretaker.addMementoR(originator.storeInMemento());
		currentMementoR++;
	}
}
