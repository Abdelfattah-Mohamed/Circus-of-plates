package Control.world_class;

import Control.Pool.MovingPool;
import Model.State.ControlledImageObject;
import Model.State.MovingImageObject;
import Model.State.State;
import Model.Logger.GameLogger;
import eg.edu.alexu.csd.oop.game.GameObject;
import Control.iterator.Iterator;
import Control.iterator.Iterator_concrete;
import Control.momento.Caretaker;
import Control.momento.Originator;
import Control.observer.*;
import View.Shapes.*;
import java.awt.*;
import java.util.ArrayList;

public class Facad implements IFacad {
	private Circus game;
	private static Caretaker caretaker;
	private static Originator originator;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private boolean flag = false;

	public Facad(Circus game) {
		new Time(game);
		new Sound(game);
		new Score(game);
		new plateServer(game);
		this.game = game;
		caretaker = Circus.getCaretaker();
		originator = Circus.getOriginator();
		saveStateL();
		saveStateR();
		GameLogger.getInstance().log.debug("Facade initialized");
	}

	public boolean refreshLogic() {
		GameObject lastplateL = game.getControlL().get(game.getControlL().size() - 1);
		GameObject lastplateR = game.getControlR().get(game.getControlR().size() - 1);
		Iterator_concrete xx;
		xx = new Iterator_concrete(game.getMoving()); // handle moving plates here
		long timepassed = System.currentTimeMillis() - game.getStartTime();
		GameLogger.getInstance().log.debug("Time passed: " + timepassed);

		if (timepassed >= Circus.getMaxTime() - 5) {
			View.addition_classes.Sound.getInstance().almostDone();

		}
		boolean timeout = System.currentTimeMillis() - game.getStartTime() > Circus.getMaxTime(); // time end and game
																									// over
		for (Iterator iter = xx.getIterator(0); iter.hasNext();) {
			ImageObject o = (ImageObject) iter.next();
			o.setY((o.getY() + 1));
			if (o.getY() + 100 >= game.getHeight()) {
				// reuse the plate in another position
				o.setY(-1 * (int) (Math.random() * game.getHeight()));
				o.setX((int) (Math.random() * game.getWidth()));

			}
			GameLogger.getInstance().log.debug("View.Shapes moved down");
			if (!timeout & o.isVisible() && (intersect(o, lastplateL))) {
				int midx = (o.getX() + o.getX() + o.getWidth()) / 2;
				if (midx <= game.getDummyL().getX() + game.getDummyL().getWidth() && midx >= game.getDummyL().getX()) {
					View.addition_classes.Sound.getInstance().caught();
					GameLogger.getInstance().log.info("Shape Caught");
					game.getMoving().remove(o);
					State state = new ControlledImageObject(o.getX(), o.getY() + 5);
					o.setState(state);
					game.getControl().add(o);
					game.getControlL().add(o);
					saveStateL();

					String color1 = o.getColor();
					String color2 = ((ImageObject) lastplateL).getColor();
					if (game.getControlL().size() > 3) {
						String color3 = ((ImageObject) game.getControlL().get(game.getControlL().size() - 3))
								.getColor();

						if (color1.equals(color2) && color2.equals(color3)) {
							notifyAllObserver(1);
							GameLogger.getInstance().log.info("3 Matched - Score increased");
						}
					}

				} else {
					View.addition_classes.Sound.getInstance().two();
					GameLogger.getInstance().log.info("Shape Fell");
					game.getControl().remove(lastplateL);
					game.getControlL().remove(lastplateL);
					State state = new MovingImageObject();
					((ImageObject) lastplateL).setState(state);
					game.getMoving().add(lastplateL);
					caretaker.removeL();
					game.setCurrentMementoL(game.getCurrentMementoL() - 1);
					int scr = game.getScore();
					scr--;
					game.setScore(scr);
				}
			}
			if (!timeout & o.isVisible() && (intersect(o, lastplateR))) {
				int midx = (o.getX() + o.getX() + o.getWidth()) / 2;
				if (midx <= game.getDummyR().getX() + game.getDummyR().getWidth() && midx >= game.getDummyR().getX()) {
					View.addition_classes.Sound.getInstance().caught();
					GameLogger.getInstance().log.info("Shape Caught");
					game.getMoving().remove(o);
					State state = new ControlledImageObject(o.getX(), o.getY() + 5);
					((ImageObject) o).setState(state);
					game.getControl().add(o);
					game.getControlR().add(o);
					saveStateR();
					String color1 = o.getColor();
					String color2 = ((ImageObject) lastplateR).getColor();
					if (game.getControlR().size() > 3) {
						String color3 = ((ImageObject) game.getControlR().get(game.getControlR().size() - 3))
								.getColor();
						if (color1.equals(color2) && color2.equals(color3)) {
							notifyAllObserver(2);
							GameLogger.getInstance().log.info("3 View.Shapes Matched");
						}
					}
				} else {
					View.addition_classes.Sound.getInstance().two();
					GameLogger.getInstance().log.info("Shape Fell");
					game.getControl().remove(lastplateR);
					game.getControlR().remove(lastplateR);
					State state = new MovingImageObject();
					((ImageObject) lastplateR).setState(state);
					game.getMoving().add(lastplateR);
					caretaker.removeR();
					game.setCurrentMementoR(game.getCurrentMementoR() - 1);
					int scr = game.getScore();
					scr--;
					game.setScore(scr);
				}

			}

		}

		if (lastplateR.getY() <= game.getMaxY() || lastplateL.getY() <= game.getMaxY()) {
			if (!flag) {
				flag = true;
				View.addition_classes.Sound.getInstance().gameOver();
				View.addition_classes.Sound.getInstance().stopTheme();
				GameLogger.getInstance().log.info("Game Over");
			}
			this.game.setTime(0);
		}

		if (game.getControl().size() >= game.getMaxNum() + 3) {
			if (!flag) {
				flag = true;
				View.addition_classes.Sound.getInstance().gameOver();
				View.addition_classes.Sound.getInstance().stopTheme();
				GameLogger.getInstance().log.info("Game Over");
			}
			this.game.setTime(0);
		}
		if (timeout) {
			if (!flag) {
				flag = true;
				View.addition_classes.Sound.getInstance().gameOver();
				View.addition_classes.Sound.getInstance().stopTheme();
				GameLogger.getInstance().log.info("Game Over");
			}
		}
		return !timeout;
	}

	public void iterate() {

		Iterator_concrete itr2;
		itr2 = new Iterator_concrete(game.getControlL());
		for (Iterator iter = itr2.getIterator(0); iter.hasNext();) {
			GameObject o = (GameObject) iter.next();
			o.setX((int) Math.min(o.getX(), screenSize.getWidth() - 225));
		}
		Iterator_concrete itr3;
		itr3 = new Iterator_concrete(game.getControlR());
		for (Iterator iter = itr3.getIterator(0); iter.hasNext();) {
			GameObject o = (GameObject) iter.next();
			o.setX(Math.max(o.getX(), 157));
		}
	}

	public void pooling() {
		MovingPool mpl = MovingPool.getInstance();
		if (mpl.hasElement()) {
			State state = new MovingImageObject();
			GameObject obj = mpl.useObj();
			((ImageObject) obj).setState(state);
			obj.setX((int) (Math.random() * screenSize.width));
			obj.setY((int) (Math.random() * screenSize.height));
			game.getMoving().add(obj);
		}
	}

	private boolean intersect(GameObject o1, GameObject o2) {
		int midx = (o1.getX() + o1.getX() + o1.getWidth()) / 2;
		// System.out.println(o1.getX());
		// System.out.println(o1.getWidth());
		/* && o1.getY() + o1.getHeight() <= o2.getY()+2 */
		if (o1.getY() + o1.getHeight() == o2.getY() && (midx <= o2.getX() + o2.getWidth() && midx >= o2.getX())) {
			// System.out.println("true");
			return true;
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	private void saveStateL() {
		originator.set((ArrayList<GameObject>) game.getControlL().clone());
		caretaker.addMementoL(originator.storeInMemento());
		game.setCurrentMementoL(game.getCurrentMementoL() + 1);
	}

	@SuppressWarnings("unchecked")
	private void saveStateR() {
		originator.set((ArrayList<GameObject>) game.getControlR().clone());
		caretaker.addMementoR(originator.storeInMemento());
		game.setCurrentMementoR(game.getCurrentMementoR() + 1);
	}

	private void notifyAllObserver(int num) {
		for (Observer observer : game.getObservers()) {
			observer.update(num);
		}
	}

}
