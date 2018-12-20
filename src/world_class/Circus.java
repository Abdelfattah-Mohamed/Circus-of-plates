package world_class;

import momento.*;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import observer.*;
import observer.Observer;
import strategy_difficulty.Strategy;
import iterator.Iterator;
import iterator.Iterator_concrete;
import to_come.Clown;
import to_come.ConstantImageObject;
import to_come.ControlledImageObject;
import to_come.FlyWeight;
import to_come.IShape;
import to_come.ImageObject;
import to_come.MovingImageObject;
import to_come.Plate;
import to_come.State;
import to_come.backGround;

import java.awt.image.BufferedImage;
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
	private Observer mom;
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
		IShape shape;
		state = new ConstantImageObject();
		shape = new backGround("0");
		ImageObject bg = new ImageObject(0, 0, shape,0, state);
		constant.add(bg);
		shape=new Clown("0");
		state = new ControlledImageObject(width / 3, (int) (height * 0.6));
		ImageObject clown = new ImageObject(width / 3, (int) (height * 0.6),shape,0 , state);
		shape = new Plate("0");
		state = new ControlledImageObject(clown.getX() - 17, clown.getY() + 37);
		ImageObject dummyL = new ImageObject(clown.getX() - 17, clown.getY() + 37, shape,0, state);
		dummyL.setVisible(false);
		state = new ControlledImageObject(clown.getX() + clown.getWidth() - 50, clown.getY() + 40);
		ImageObject dummyR = new ImageObject(clown.getX() + clown.getWidth() - 50, clown.getY() + 40, shape,0,
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
		FlyWeight fw = new FlyWeight();
		moving = fw.createPlates(width, height);
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

	public static void setCaretaker(Caretaker caretaker) {
		Circus.caretaker = caretaker;
	}

	public int getCurrentMementoL() {
		return currentMementoL;
	}

	public int getCurrentMementoR() {
		return currentMementoR;
	}

	public static void setOriginator(Originator originator) {
		Circus.originator = originator;

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

	@Override
	public boolean refresh() {
		if (!flag) {
			t = new Time(this);
			s = new Sound(this);
			sc = new Score(this);
			mom = new plateServer(this);
			flag = true;
		}
		boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME; // time end and game over

		//System.out.print(System.currentTimeMillis() +" " );
		//System.out.print(startTime+" ");
		//System.out.println(MAX_TIME);

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
				//State state = new MovingImageObject();
				//moving.add(new ImageObject((int) (Math.random() * width), (int) (Math.random() * height / 2), "/plate.png",
					//	state));
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
				//ImageObject obj = (ImageObject) lastplateL;
				//obj = (ImageObject) controlL.get(controlL.size()-2);
				String color1 = o.getColor();
				String color2 = ((ImageObject)lastplateL).getColor();
				if(controlL.size()>3) {
				String color3 = ((ImageObject)controlL.get(controlL.size()-3)).getColor();
				
				if(color1.equals(color2) && color2.equals(color3)) {
					this.notifyAllObserver(1);
				}
				}
						
				/*if(color1.equals(color2)) {
					System.out.println(true);
					countL++;
				}else {
					if(color3.equals(color2)) {
						countL=1;
					}else {
					countL=0;
					}
				}

				if (countL == 2 ) {
					this.notifyAllObserver();
				}*/


			}
			if (!timeout & o.isVisible() && (intersect(o, lastplateR))) {
				// clown caught a plate here on the right side
				moving.remove(o);
				State state = new ControlledImageObject(o.getX(), o.getY());
				((ImageObject) o).setState(state);
				control.add(o);
				controlR.add(o);
				saveStateR();
				String color1 = o.getColor();
				String color2 = ((ImageObject)lastplateR).getColor();
				if(controlR.size()>3) {
				String color3 = ((ImageObject)controlR.get(controlR.size()-3)).getColor();
				if(color1.equals(color2) && color2.equals(color3)) {
					this.notifyAllObserver(2);
				}
				}
				/*if(color1.equals(color2)) {
					countR++;
				}else {
					if(color3.equals(color2)) {
						countR=1;
					}else {
					countR=0;
					}
				}
				if (countR ==2) {
					this.notifyAllObserver();
				}*/

				// this.notifyAllObserver();
			}
		}
		//itrator
				for(int counter = 0;counter<controlL.size();counter++) {
					GameObject o = controlL.get(counter);
					o.setX(Math.min(o.getX(), 1135));
				}
				for(int counter = 0;counter<controlR.size();counter++) {
					GameObject o = controlR.get(counter);
					o.setX(Math.max(o.getX(), 157));
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
			//System.out.println("true");
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

	public void notifyAllObserver(int num) {
		for (Observer observer : observers) {
			observer.update(num);
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
