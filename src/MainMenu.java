import eg.edu.alexu.csd.oop.game.GameEngine;
import strategy_difficulty.Strategy;
import strategy_difficulty.easy;
import strategy_difficulty.hard;
import strategy_difficulty.normal;
import world_class.Circus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {

    private Strategy strategy;
    private ScreenResolution resolution;
    
    public MainMenu(Strategy s, ScreenResolution screenResolution) {
        strategy = s;
        resolution = screenResolution;
    }

    public void start() {
        JMenuBar menuBar = new JMenuBar();;
        final GameEngine.GameController[] gameController = new GameEngine.GameController[1];
        JPanel panel = new JPanel();
        Button e = new Button();
        e.setSize(100,100);
        e.setLabel("EASY");
        
        e.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                strategy = new easy();
                gameController[0] = GameEngine.start("Very Simple Game in 99 Line of Code", new Circus(resolution.getWidth(),resolution.getHeight(),strategy), menuBar, Color.BLACK);
            }});
        Button m = new Button();
        m.setSize(100,100);
        m.setLabel("MEDIUM");
        m.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                strategy = new normal();
                gameController[0] = GameEngine.start("Very Simple Game in 99 Line of Code", new Circus(resolution.getWidth(),resolution.getHeight(), strategy), menuBar, Color.BLACK);
            }});
        Button h = new Button();
        h.setSize(100,100);
        h.setLabel("HARD");
        h.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                strategy = new hard();
                gameController[0] = GameEngine.start("Very Simple Game in 99 Line of Code", new Circus(resolution.getWidth(),resolution.getHeight(), strategy), menuBar, Color.BLACK);
            }});

        JMenu menu = new JMenu("File");
        JMenuItem newgame = new JMenuItem("New");
        newgame.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                gameController[0].changeWorld( new Circus(resolution.getWidth(),resolution.getHeight(), strategy));
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
