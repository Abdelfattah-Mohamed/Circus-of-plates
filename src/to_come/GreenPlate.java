package to_come;

public class GreenPlate extends Plate {

    public GreenPlate(int posX, int posY, String path, State state) {
        super(posX, posY, path, state,"green");
    }

    public GreenPlate(int posX, int posY, String path, int type, State state) {
        super(posX, posY, path, type, state, "green");
    }
}
