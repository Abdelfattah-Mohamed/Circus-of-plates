package to_come;

public class BluePlate extends Plate {

    public BluePlate(int posX, int posY, String path, State state) {
        super(posX, posY, path, state,"blue");
    }

    public BluePlate(int posX, int posY, String path, int type, State state) {
        super(posX, posY, path, type, state, "blue");
    }
}
