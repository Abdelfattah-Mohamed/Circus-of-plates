package momento;

// Memento Design Pattern 

import java.util.ArrayList;

public class Caretaker {

	// Where all mementoes are saved

	public ArrayList<Memento> savedControlerL = new ArrayList<Memento>();
	public ArrayList<Memento> savedControlerR = new ArrayList<Memento>();

	// Adds memento to the ArrayList

	public void addMementoL(Memento m) {
		savedControlerL.add(m);
	}

	// Gets the memento requested from the ArrayList

	public Memento getMementoL(int index) {
		return savedControlerL.get(index);
	}

	public Memento removeL() {
		return savedControlerL.remove(savedControlerL.size() - 1);
	}

	// Adds memento to the ArrayList

	public void addMementoR(Memento m) {
		savedControlerR.add(m);
	}

	// Gets the memento requested from the ArrayList

	public Memento getMementoR(int index) {
		return savedControlerR.get(index);
	}

	public Memento removeR() {
		return savedControlerR.remove(savedControlerR.size() - 1);
	}
}