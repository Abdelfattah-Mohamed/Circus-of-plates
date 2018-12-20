package to_come;

import to_come.Plate;
import to_come.RedPlate;
import to_come.State;

import java.util.HashMap;

public class PlateFactory {


    private static final HashMap PlateMap = new HashMap();

    public  Plate getPlate(String size, int weidth, int height, State state,String color) {

        IPlate plate = (Plate)PlateMap.get(size);
        if(plate == null) {
            plate = new RedPlate((int) (Math.random() * weidth), (int) (Math.random() * height / 2),
                    "/"+color+"plate"+size+".png", state);
            PlateMap.put(size, plate);
        }else {
        	
        }
        return (Plate) plate;
    }

}