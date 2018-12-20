package to_come;

public class Plate extends ImageObject {
    private String color;


    public Plate(int posX, int posY, String path, State state,String color) {
        super(posX, posY, path, state);
        this.color=color;
    }

    public Plate(int posX, int posY, String path, int type, State state,String color) {
        super(posX, posY, path, type, state);
        this.color=color;
    }

}
