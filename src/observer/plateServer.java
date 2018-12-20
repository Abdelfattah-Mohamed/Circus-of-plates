package observer;

import eg.edu.alexu.csd.oop.game.GameObject;
import to_come.ImageObject;
import world_class.Circus;

import java.util.ArrayList;

public class plateServer extends Observer{

    private Circus game;



    public plateServer(Circus game){
        this.game = game;
        game.attach(this);
    }
    @Override
    public void update() {
        //game.setControlL();
        //game.setControlR();
    }

    private void saveL(){
        if (currentMementoL >= 1) {
            currentMementoL -= 3;
            for (int k = 0; k < 3; k++) {
                ImageObject s = (ImageObject) game.getControlL().get(game.getControlL().size() - k - 1);
                s.setVisible(false);
            }
            game.setControlL((ArrayList<GameObject>) originator
                    .restoreFromMemento(caretaker.getMementoL(currentMementoL - 1)).clone());
        }
    }

    private void saveR(){

    }


}
