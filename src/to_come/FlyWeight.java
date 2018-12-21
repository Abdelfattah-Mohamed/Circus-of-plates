package to_come;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import dynamicLinkage.jarread;

public class FlyWeight {

	// ArrayList<String> sizes=new ArrayList<String>();
	ArrayList<GameObject> shapes = new ArrayList<GameObject>();
	List<Class<?>> listofClasses;

	public FlyWeight() {
		jarread x = new jarread();
		this.listofClasses = x.getCrunchifyClassNamesFromJar("JAR_F.jar");
		// sizes.add("cup");
		// sizes.add("dice");
		// sizes.add("plate");

	}

	public ArrayList<GameObject> createPlates(int width, int height) {

		for (int i = 0; i < 25; ++i) {
			// hn3del hna lw hn5ly al etnin random
			State state = new MovingImageObject();
			shapes.add(new ImageObject((int) (Math.random() * width), (int) (Math.random() * height), getRandomshape(),
					0, state));

		}
		return shapes;
	}

	private IShape getRandomshape() {
		// factory
		IShape shape = null;
		String color;
		int num1 = (1 + (int) (Math.random() * 3));
		int num2 = (1 + (int) (Math.random() * 3));
		if (num2 == 1) {
			color = "red";
		} else if (num2 == 2) {
			color = "blue";
		} else {
			color = "purple";
		}

		if (num1 == 1) {
			try {
				shape = (IShape) listofClasses.get(1).getConstructor(String.class).newInstance(color);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (num1 == 2) {
			try {
				shape = (IShape) listofClasses.get(0).getConstructor(String.class).newInstance(color);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				shape = (IShape) listofClasses.get(5).getConstructor(String.class).newInstance(color);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return shape;
	}

}