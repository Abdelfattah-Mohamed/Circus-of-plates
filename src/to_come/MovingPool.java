package to_come;

import java.util.ArrayList;

import eg.edu.alexu.csd.oop.game.GameObject;
import iterator.Iterator;
import iterator.Iterator_concrete;

public class MovingPool {
	 

	private MovingPool() {
		
	}
	private int num =0;
	private static MovingPool objectPool = null;
	private ArrayList<GameObject> thePool = new ArrayList<GameObject>();
	public static MovingPool getInstance() {
		if (objectPool == null) {
			objectPool = new MovingPool();
		}
		return objectPool;

	}
	
	public void setPool(int width,int height) {
		FlyWeight fw = new FlyWeight(num);
		thePool=fw.createPlates(width, height);
	}
	
	public ArrayList<GameObject> usePool(){
		Iterator_concrete itr;
		itr = new Iterator_concrete(thePool);
		ArrayList<GameObject> thePoolused = new ArrayList<GameObject>();
		for (Iterator iter = itr.getIterator(0); iter.hasNext();) {
			GameObject o = (GameObject) iter.next();
			thePoolused.add(o) ;
		}
		thePool.clear();
		
		return thePoolused;
		
	}
	
	public void releaseObj(GameObject obj) {
		thePool.add(obj);
	}
	
	public boolean hasElement() {
		return(thePool.size()>0);
	}
	public GameObject useObj() {
		
		if(!hasElement()) {
			return null;
		}
		return (thePool.remove(0));
	}
	public void setNum(int num) {
		this.num=num;
	}
	
}
