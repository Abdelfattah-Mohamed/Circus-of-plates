package observer;

import world_class.Circus;

public abstract class Observer {
    protected Circus world;
    public abstract void update(int num);

}
