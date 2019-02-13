package Control.momento;

import Model.Logger.GameLogger;

import java.util.ArrayList;

public class Memento {

	// The states stored in memento Object

	private ArrayList<?> state = new ArrayList<Object>();

	// Save a new article String to the memento Object

	public Memento(ArrayList<?> articleSave) {
		GameLogger.getInstance().log.debug("Array turned into Memento");
		state = (ArrayList<?>) articleSave.clone();
	}

	// Return the value stored in article

	public ArrayList<?> getSaved() {
		GameLogger.getInstance().log.debug("returned state as Array");
		return (ArrayList<?>) state.clone();
	}

}