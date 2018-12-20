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
    public void update(int num) {
    	
        if(num==1){
            operationL();
        }else if(num==2) {
            operationR();
        }
    }

    private void operationL(){
        if (game.getCurrentMementoL() >= 1) {
            game.setCurrentMementoL(game.getCurrentMementoL() - 3);
            for (int k = 0; k < 3; k++) {
                ImageObject s = (ImageObject) game.getControlL().get(game.getControlL().size() - k - 1);
                s.setVisible(false);
            }
            game.setControlL((ArrayList<GameObject>) game.getOriginator()
                    .restoreFromMemento(game.getCaretaker().getMementoL(game.getCurrentMementoL() - 1)).clone());
        }
    }

    private void operationR(){
        if (game.getCurrentMementoR() >= 1) {
            game.setCurrentMementoR(game.getCurrentMementoR() - 3);
            for (int k = 0; k < 3; k++) {
                ImageObject s = (ImageObject) game.getControlR().get(game.getControlR().size() - k - 1);
                s.setVisible(false);
            }
            game.setControlR((ArrayList<GameObject>) game.getOriginator()
                    .restoreFromMemento(game.getCaretaker().getMementoR(game.getCurrentMementoR() - 1)).clone());
        }
    }


}
