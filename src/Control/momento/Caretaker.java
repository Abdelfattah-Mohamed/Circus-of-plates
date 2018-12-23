package Control.momento;

// Memento Design Pattern 

import Model.Logger.GameLogger;

import java.util.ArrayList;

public class Caretaker {

	// Where all mementoes are saved

	private ArrayList<Memento> savedControlerL = new ArrayList<Memento>();
	private ArrayList<Memento> savedControlerR = new ArrayList<Memento>();

	// Adds memento to the ArrayList

	public void addMementoL(Memento m) {
		GameLogger.getInstance().log.debug("State added to Left States Array");
		savedControlerL.add(m);
	}

	// Gets the memento requested from the ArrayList

	public Memento getMementoL(int index) {
		GameLogger.getInstance().log.debug("returned State at left at: "+index);
		return savedControlerL.get(index);
	}

	public Memento removeL() {
		GameLogger.getInstance().log.debug("last State at left removed");
		return savedControlerL.remove(savedControlerL.size() - 1);
	}

	// Adds memento to the ArrayList

	public void addMementoR(Memento m) {
		GameLogger.getInstance().log.debug("State added to Right States Array");
		savedControlerR.add(m);
	}

	// Gets the memento requested from the ArrayList

	public Memento getMementoR(int index) {
		GameLogger.getInstance().log.debug("returned State at Right at: "+index);
		return savedControlerR.get(index);
	}

	public Memento removeR() {
		GameLogger.getInstance().log.debug("last State at Right removed");
		return savedControlerR.remove(savedControlerR.size() - 1);
	}
}