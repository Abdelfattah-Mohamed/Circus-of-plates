package to_come;

public class RedPlate extends Plate {

    public RedPlate(int posX, int posY, String path, State state) {
        super(posX, posY, path, state,"red");
    }

    public RedPlate(int posX, int posY, String path, int type, State state) {
        super(posX, posY, path, type, state, "red");
    }
}
