package momento;

import java.util.ArrayList;

public class Memento {

	// The states stored in memento Object

	private ArrayList state = new ArrayList();

	// Save a new article String to the memento Object

	public Memento(ArrayList articleSave) {
		state = (ArrayList) articleSave.clone();
	}

	// Return the value stored in article

	public ArrayList getSaved() {
		return (ArrayList) state.clone();
	}

}