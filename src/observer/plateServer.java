package observer;

import world_class.Circus;
import momento.*;

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
       // Originator.
    }

    private void saveR(){

    }


}
