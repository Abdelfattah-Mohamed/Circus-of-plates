package View;

import View.ScreenResolution;
import eg.edu.alexu.csd.oop.game.GameEngine;

import javax.swing.*;

public abstract class Command {

    protected GameEngine.GameController gameController;
    protected ScreenResolution resolution;
    protected JMenuBar menuBar;

    public Command(GameEngine.GameController gameController, ScreenResolution resolution, JMenuBar menuBar) {
        this.gameController = gameController;
        this.resolution = resolution;
        this.menuBar = menuBar;
    }
    public abstract void generate();
}
