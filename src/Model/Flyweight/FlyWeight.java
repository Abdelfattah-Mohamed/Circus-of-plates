package Model.Flyweight;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Model.State.MovingImageObject;
import Model.State.State;
import Model.Logger.GameLogger;
import View.Shapes.IShape;
import View.Shapes.ImageObject;
import eg.edu.alexu.csd.oop.game.GameObject;
import Control.dynamicLinkage.jarread;

public class FlyWeight {

    // ArrayList<String> sizes=new ArrayList<String>();
    ArrayList<GameObject> shapes = new ArrayList<GameObject>();
    List<Class<?>> listofClasses;
    private static final HashMap classMap = new HashMap();
    private int max;

    public FlyWeight(int num) {
        GameLogger.getInstance().log.debug("FlyWeight initialized successfully");
        jarread x = new jarread();
        this.listofClasses = x.getCrunchifyClassNamesFromJar("JAR_F.jar");
        GameLogger.getInstance().log.debug("Classes List retrieved Successfully");
        max = num;
    }

    public ArrayList<GameObject> createPlates(int width, int height) {

        for (int i = 0; i < max; ++i) {
            // hn3del hna lw hn5ly al etnin random
            State state = new MovingImageObject();
            shapes.add(new ImageObject((int) (Math.random() * width), (int) (Math.random() * -1 * height), getRandomshape(),
                    0, state));

        }
        GameLogger.getInstance().log.debug("View.Shapes created Successfully");
        return shapes;
    }

    private IShape getRandomshape() {
        // factory
        IShape shape = null;
        String color;
        int num1 = (1 + (int) (Math.random() * 3));
        int num2 = (1 + (int) (Math.random() * 3));
        if (num2 == 1) {
            GameLogger.getInstance().log.debug("Random shape color: red");
            color = "red";
        } else if (num2 == 2) {
            color = "blue";
            GameLogger.getInstance().log.debug("random shape color: blue");
        } else {
            color = "purple";
            GameLogger.getInstance().log.debug("random shape color: purple");
        }

        if (num1 == 1) {
            if (classMap.get(color + "Plate") == null) {
                try {
                    classMap.put(color + "Plate", listofClasses.get(2).getConstructor(String.class).newInstance(color));
                    shape = (IShape) listofClasses.get(2).getConstructor(String.class).newInstance(color);
                    GameLogger.getInstance().log.debug("Plate Shape loaded successfully");
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                    // TODO Auto-generated catch block
                    GameLogger.getInstance().log.error("ERROR LOADING PLATE SHAPE");
                    e.printStackTrace();
                }
            } else {
                shape = (IShape) classMap.get(color + "Plate");
                GameLogger.getInstance().log.debug("Plate Shape created");
            }
        } else if (num1 == 2) {
            if (classMap.get(color + "Cup") == null) {
                try {
                    classMap.put(color + "Cup", listofClasses.get(0).getConstructor(String.class).newInstance(color));
                    shape = (IShape) listofClasses.get(0).getConstructor(String.class).newInstance(color);
                    GameLogger.getInstance().log.debug("Cup Shape loaded successfully");
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                    // TODO Auto-generated catch block
                    GameLogger.getInstance().log.error("ERROR LOADING CUP SHAPE");
                    e.printStackTrace();
                }
            } else {
                shape = (IShape) classMap.get(color + "Cup");
                GameLogger.getInstance().log.debug("Cup Shape created");
            }
        } else {
            if (classMap.get(color + "Dice") == null) {
                try {
                    classMap.put(color + "Dice", listofClasses.get(6).getConstructor(String.class).newInstance(color));
                    shape = (IShape) listofClasses.get(6).getConstructor(String.class).newInstance(color);
                    GameLogger.getInstance().log.debug("Die Shape loaded successfully");
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                    // TODO Auto-generated catch block
                    GameLogger.getInstance().log.error("ERROR LOADING DIE SHAPE");
                    e.printStackTrace();
                }
            } else {
                shape = (IShape) classMap.get(color + "Dice");
                GameLogger.getInstance().log.debug("Die Shape created");
            }
        }
        return shape;
    }

}