package to_come;

import java.util.ArrayList;

import eg.edu.alexu.csd.oop.game.GameObject;

public class FlyWeight {

    ArrayList<String> sizes=new ArrayList<String>();
    ArrayList<GameObject> newMoving=new ArrayList<GameObject>();
    FlyWeight( ){
    	
        sizes.add("m");
        sizes.add("l");
        sizes.add("xl");
        
    }
    
    public ArrayList<GameObject> createPlates(int weidth, int height, State state) {
    	PlateFactory  pf = new PlateFactory();
    	for(int i=0; i < 7; ++i) {
        	//hn3del hna lw hn5ly al etnin random
    		newMoving.add(pf.getPlate(getRandomSize(),weidth,height,state,"red"));
    		newMoving.add(pf.getPlate(getRandomSize(),weidth,height,state,"blue"));
    		newMoving.add(pf.getPlate(getRandomSize(),weidth,height,state,"green"));
        }
		return newMoving;
    }

    private String getRandomSize() {
        return sizes.get((int)(Math.random()*sizes.size()));
    }

}