package world_class;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import iterator.Iterator;
import iterator.Iterator_concrete;
import momento.Caretaker;
import momento.Originator;
import observer.*;
import to_come.*;
import java.awt.*;
import java.util.ArrayList;

public class Facad implements IFacad{
    private Observer t;
    private Observer s;
    private Observer sc;
    private Observer mom;
    private Circus game;
    private static Caretaker caretaker;
    private static Originator originator;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public Facad(Circus game){
        //initializing the observers
        t = new Time(game);
        s = new Sound(game);
        sc = new Score(game);
        mom = new plateServer(game);
        this.game=game;
        caretaker = game.getCaretaker();
        originator = game.getOriginator();
        saveStateL();
        saveStateR();
    }

    public boolean refreshLogic(){
        GameObject lastplateL = game.getControlL().get(game.getControlL().size() - 1);
        GameObject lastplateR = game.getControlR().get(game.getControlR().size() - 1);
        Iterator_concrete xx;
        xx = new Iterator_concrete(game.getMoving()); // handle moving plates here
        boolean timeout = System.currentTimeMillis() - game.getStartTime() > game.getMaxTime(); // time end and game over
        for (Iterator iter = xx.getIterator(0); iter.hasNext();) {
            ImageObject o = (ImageObject) iter.next();
            o.setY((o.getY() + 1));
            if (o.getY() + 100 >= game.getHeight()) {
                // reuse the plate in another position
                o.setY(-1 * (int) (Math.random() * game.getHeight()));
                o.setX((int) (Math.random() * game.getWidth()));

            }
            
            if (!timeout & o.isVisible() && (intersect(o, lastplateL))) {
                int midx = (o.getX() + o.getX() + o.getWidth()) / 2;
                if (midx <= game.getDummyL().getX() + game.getDummyL().getWidth() && midx >= game.getDummyL().getX()) {
                    game.getMoving().remove(o);
                    State state = new ControlledImageObject(o.getX(), o.getY() + 5);
                    o.setState(state);
                    game.getControl().add(o);
                    game.getControlL().add(o);
                    saveStateL();
                    
                    String color1 = o.getColor();
                    String color2 = ((ImageObject) lastplateL).getColor();
                    if (game.getControlL().size() > 3) {
                        String color3 = ((ImageObject) game.getControlL().get(game.getControlL().size() - 3)).getColor();

                        if (color1.equals(color2) && color2.equals(color3)) {
                            notifyAllObserver(1);
                        }
                    }

                    

                } else {
                    game.getControl().remove(lastplateL);
                    game.getControlL().remove(lastplateL);
                    State state = new MovingImageObject();
                    ((ImageObject) lastplateL).setState(state);
                    game.getMoving().add(lastplateL);
                    caretaker.removeL();
                    game.setCurrentMementoL(game.getCurrentMementoL() - 1);
                }
            }
            if (!timeout & o.isVisible() && (intersect(o, lastplateR))) {
                // clown caught a plate here on the right side
                int midx = (o.getX() + o.getX() + o.getWidth()) / 2;
                if (midx <= game.getDummyR().getX() + game.getDummyR().getWidth() && midx >= game.getDummyR().getX()) {
                    game.getMoving().remove(o);
                    State state = new ControlledImageObject(o.getX(), o.getY() + 5);
                    ((ImageObject) o).setState(state);
                    game.getControl().add(o);
                    game.getControlR().add(o);
                    saveStateR();
                    String color1 = o.getColor();
                    String color2 = ((ImageObject) lastplateR).getColor();
                    if (game.getControlR().size() > 3) {
                        String color3 = ((ImageObject) game.getControlR().get(game.getControlR().size() - 3)).getColor();
                        if (color1.equals(color2) && color2.equals(color3)) {
                            notifyAllObserver(2);
                        }
                    }
                } else {
                    game.getControl().remove(lastplateR);
                    game.getControlR().remove(lastplateR);
                    State state = new MovingImageObject();
                    ((ImageObject) lastplateR).setState(state);
                    game.getMoving().add(lastplateR);
                    caretaker.removeR();
                    game.setCurrentMementoR(game.getCurrentMementoR() - 1);
                }
                
            }

        }
        
        if(lastplateR.getY()<=game.getMaxY() || lastplateL.getY()<=game.getMaxY()) {
        	this.game.setTime(0);
        }
       
        if(game.getControl().size()>= game.getMaxNum()+3) {
        	this.game.setTime(0);
        }
        return !timeout;
    }

    public void iterate(){

        Iterator_concrete itr2;
        itr2 = new Iterator_concrete(game.getControlL());
        for (Iterator iter = itr2.getIterator(0); iter.hasNext();) {
            GameObject o = (GameObject) iter.next();
            o.setX((int) Math.min(o.getX(), screenSize.getWidth()-225));
        }
        Iterator_concrete itr3;
        itr3 = new Iterator_concrete(game.getControlR());
        for (Iterator iter = itr3.getIterator(0); iter.hasNext();) {
            GameObject o = (GameObject) iter.next();
            o.setX(Math.max(o.getX(), 157));
        }
    }
    public void pooling(){
        MovingPool mpl = MovingPool.getInstance();
        if(mpl.hasElement()) {
            State state = new MovingImageObject();
            GameObject obj = mpl.useObj();
            ((ImageObject)obj).setState(state);
            obj.setX((int) (Math.random() * screenSize.width));
            obj.setY((int) (Math.random() * screenSize.height));
            game.getMoving().add(obj);
        }
    }

    private boolean intersect(GameObject o1, GameObject o2) {
        int midx = (o1.getX() + o1.getX() + o1.getWidth()) / 2;
        // System.out.println(o1.getX());
        // System.out.println(o1.getWidth());
        /* && o1.getY() + o1.getHeight() <= o2.getY()+2 */
        if (o1.getY() + o1.getHeight() == o2.getY() && (midx <= o2.getX() + o2.getWidth() && midx >= o2.getX())) {
            // System.out.println("true");
            return true;
        }

        return false;
    }

    private void saveStateL() {
        originator.set((ArrayList<GameObject>) game.getControlL().clone());
        caretaker.addMementoL(originator.storeInMemento());
        game.setCurrentMementoL(game.getCurrentMementoL()+1);
    }

    private void saveStateR() {
        originator.set((ArrayList<GameObject>) game.getControlR().clone());
        caretaker.addMementoR(originator.storeInMemento());
        game.setCurrentMementoR(game.getCurrentMementoR()+1);
    }

    private void notifyAllObserver(int num) {
        for (Observer observer : game.getObservers()) {
            observer.update(num);
        }
    }


}
