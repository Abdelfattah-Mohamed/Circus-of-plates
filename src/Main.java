
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
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        JMenuBar  menuBar = new JMenuBar();;
        final Strategy[] strategy = {null};
        final GameController[] gameController = new GameController[1];
        JPanel panel = new JPanel();
        Button e = new Button();
        e.setSize(100,100);
        e.setLabel("EASY");
        e.addActionListener(new ActionListener() {
        @Override public void actionPerformed(ActionEvent e) {
            strategy[0] = new easy();
            gameController[0] = GameEngine.start("Very Simple Game in 99 Line of Code", new Circus(width,height,strategy[0]), menuBar, Color.BLACK);
        }});
        Button m = new Button();
        m.setSize(100,100);
        m.setLabel("MEDIUM");
        m.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                strategy[0] = new normal();
                gameController[0] = GameEngine.start("Very Simple Game in 99 Line of Code", new Circus(width,height, strategy[0]), menuBar, Color.BLACK);
            }});
        Button h = new Button();
        h.setSize(100,100);
        h.setLabel("HARD");
        h.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                strategy[0] = new hard();
                gameController[0] = GameEngine.start("Very Simple Game in 99 Line of Code", new Circus(width,height, strategy[0]), menuBar, Color.BLACK);
            }});

        JMenu menu = new JMenu("File");
        JMenuItem newgame = new JMenuItem("New");
        newgame.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                gameController[0].changeWorld( new Circus(width,height, strategy[0]));
            }});
        panel.add(e);
        panel.add(m);
        panel.add(h);
        panel.setVisible(true);
        JFrame frame = new JFrame();
        frame.setSize(300,100);
        frame.add(panel);
        frame.setVisible(true);
        JMenuItem  exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(exitMenuItem);
        menu.add(newgame);
        menuBar.add(menu);

    }

}
