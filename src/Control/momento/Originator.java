package Control.momento;

import java.util.ArrayList;

// Memento Design Pattern

public class Originator {

	private ArrayList state = new ArrayList();

	// Sets the value for the article

	public void set(ArrayList newControl) {
		this.state = (ArrayList) newControl.clone();
	}

	// Creates a new Memento with a new article

	public Memento storeInMemento() {
		return new Memento((ArrayList) state.clone());
	}

	// Gets the article currently stored in memento

	public ArrayList restoreFromMemento(Memento memento) {

		state = (ArrayList) memento.getSaved().clone();
		return (ArrayList) state.clone();

	}

}