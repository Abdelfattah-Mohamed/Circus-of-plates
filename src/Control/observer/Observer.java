package Control.observer;

import Control.world_class.Circus;

public abstract class Observer {
    protected Circus world;
    public abstract void update(int num);

}
