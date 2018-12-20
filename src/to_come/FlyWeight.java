package to_come;

import java.util.ArrayList;

import eg.edu.alexu.csd.oop.game.GameObject;

public class FlyWeight {

    //ArrayList<String> sizes=new ArrayList<String>();
    ArrayList<GameObject> shapes=new ArrayList<GameObject>();
   public FlyWeight( ){
    	
       // sizes.add("cup");
       // sizes.add("dice");
        //sizes.add("plate");
        
    }
    
    public ArrayList<GameObject> createPlates(int width, int height) {
    	
    	for(int i=0; i < 25; ++i) {
        	//hn3del hna lw hn5ly al etnin random
    		State state = new MovingImageObject();
    		shapes.add(new ImageObject((int) (Math.random() * width), (int) (Math.random() * height / 2),getRandomshape(),
					0,state));
    		
    		
        }
		return shapes;
    }

    private IShape getRandomshape() {
    	//factory
    	IShape shape;
    	String color;
    	int num1 = (1+(int)(Math.random()*3));
    	int num2 = (1+(int)(Math.random()*3));
    	if(num2==1) {
    		color="red";
        }else if(num2==2) {
        	color="blue";
        }else {
        	color="purple";
        }
    	
    	
         if(num1==1) {
        	 shape= new Plate(color);
         }else if(num1==2) {
        	 shape= new Cup(color);
         }else {
        	 shape= new Dice(color);
         }
         return shape;
    }

}