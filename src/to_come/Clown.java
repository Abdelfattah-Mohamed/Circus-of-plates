package to_come;

import eg.edu.alexu.csd.oop.game.GameObject;

import java.awt.image.BufferedImage;

public class Clown extends ImageObject {


    public Clown(int posX, int posY, String path, State state) {
        super(posX, posY, path, state);
    }

    public Clown(int posX, int posY, String path, int type, State state) {
        super(posX, posY, path, type, state);
    }
}
