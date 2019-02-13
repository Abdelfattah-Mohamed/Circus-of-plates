package Control.observer;

import Model.Logger.GameLogger;
import eg.edu.alexu.csd.oop.game.GameObject;
import View.Shapes.ImageObject;
import Control.Pool.MovingPool;
import Control.world_class.Circus;

import java.util.ArrayList;

public class plateServer extends Observer {

	private Circus game;
	MovingPool mpl = MovingPool.getInstance();
	ArrayList<GameObject> newControl;

	public plateServer(Circus game) {
		GameLogger.getInstance().log.debug("plateServer initialized Successfully!");
		this.game = game;
		game.attach(this);
		newControl = game.getControl();
	}

	@Override
	public void update(int num) {

		if (num == 1) {
			operationL();
		} else if (num == 2) {
			operationR();
		}
	}

	@SuppressWarnings("unchecked")
	private void operationL() {

		game.setCurrentMementoL(game.getCurrentMementoL() - 3);

		for (int k = 0; k < 3; k++) {
			ImageObject s = (ImageObject) game.getControlL().get(game.getControlL().size() - k - 1);
			mpl.releaseObj(s);
			newControl.remove(s);
			GameLogger.getInstance().log.debug("Shape removed from left Stack");
		}
		game.setControlL((ArrayList<GameObject>) Circus.getOriginator()
				.restoreFromMemento(Circus.getCaretaker().getMementoL(game.getCurrentMementoL() - 1)).clone());
		GameLogger.getInstance().log.debug("View.Shapes cloned");
		Circus.getCaretaker().removeL();
		Circus.getCaretaker().removeL();
		Circus.getCaretaker().removeL();
	}

	@SuppressWarnings("unchecked")
	private void operationR() {

		if (game.getCurrentMementoR() >= 1) {
			game.setCurrentMementoR(game.getCurrentMementoR() - 3);
			for (int k = 0; k < 3; k++) {
				ImageObject s = (ImageObject) game.getControlR().get(game.getControlR().size() - k - 1);
				mpl.releaseObj(s);
				newControl.remove(s);
				GameLogger.getInstance().log.debug("Shape removed from right Stack");
			}
			game.setControlR((ArrayList<GameObject>) Circus.getOriginator()
					.restoreFromMemento(Circus.getCaretaker().getMementoR(game.getCurrentMementoR() - 1)).clone());
			GameLogger.getInstance().log.debug("View.Shapes cloned");
			Circus.getCaretaker().removeR();
			Circus.getCaretaker().removeR();
			Circus.getCaretaker().removeR();
		}
	}

}
