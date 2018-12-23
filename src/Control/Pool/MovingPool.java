package Control.Pool;

import java.util.ArrayList;

import Model.Flyweight.FlyWeight;
import Model.Logger.GameLogger;
import eg.edu.alexu.csd.oop.game.GameObject;
import Control.iterator.Iterator;
import Control.iterator.Iterator_concrete;

public class MovingPool {
	 

	private MovingPool() {
		GameLogger.getInstance().log.debug("Moving Pool initialzied");
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
		GameLogger.getInstance().log.debug("Pool set & Flyweight Created");
	}
	
	public ArrayList<GameObject> usePool(){
		Iterator_concrete itr;
		itr = new Iterator_concrete(thePool);
		ArrayList<GameObject> thePoolused = new ArrayList<GameObject>();
		for (Iterator iter = itr.getIterator(0); iter.hasNext();) {
			GameObject o = (GameObject) iter.next();
			thePoolused.add(o);
		}
		GameLogger.getInstance().log.debug("Pool used");
		thePool.clear();
        GameLogger.getInstance().log.debug("Pool cleared");
		return thePoolused;
		
	}
	
	public void releaseObj(GameObject obj) {
		//object returned to pool
        GameLogger.getInstance().log.debug("Object returned to the pool");
		thePool.add(obj);
	}
	
	public boolean hasElement() {
        GameLogger.getInstance().log.debug("Checking if pool Empty: "+!(thePool.size()>0));
		return(thePool.size()>0);
	}
	public GameObject useObj() {
		
		if(!hasElement()) {
			return null;
		}
        GameLogger.getInstance().log.debug("Object used form the pool");
		return (thePool.remove(0));
	}
	public void setNum(int num) {
		GameLogger.getInstance().log.debug("Maximum num of Objects ");
		this.num=num;
	}
	
}
