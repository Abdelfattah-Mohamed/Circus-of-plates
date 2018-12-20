
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameEngine.GameController;
import strategy_difficulty.*;
import world_class.Circus;

public class Main {

    public static void main(String[] args) {
        /* using menus in the game */
        final Strategy[] strategy = {null};
        ScreenResolution resolution = new ScreenResolution();
        MainMenu mainMenu = new MainMenu(strategy[0],resolution);
        mainMenu.start();

    }

}
