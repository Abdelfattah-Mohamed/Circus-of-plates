package Control;

import Model.Logger.GameLogger;
import View.Command;
import View.ScreenResolution;
import eg.edu.alexu.csd.oop.game.GameEngine;
import Model.strategy_difficulty.Strategy;
import Model.strategy_difficulty.easy;
import Model.strategy_difficulty.hard;
import Model.strategy_difficulty.normal;
import Control.world_class.Circus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class ButtonGenerator extends Command {

    private Strategy strategy;
    private Button e,m,h;
    private JFrame frame;

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public ButtonGenerator(GameEngine.GameController gameController, ScreenResolution resolution, JMenuBar menuBar) {
        super(gameController, resolution, menuBar);
    }

    public void generate(){
        //easy Button
        e = new Button();
        e.setSize(100,100);
        e.setLabel("EASY");
        e.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                setStrategy(1);
                GameLogger.getInstance().log.info("Game set to easy mode");
                frame.setVisible(false);
                gameController = GameEngine.start("HeadCup", new Circus(resolution.getWidth(),resolution.getHeight(),strategy), menuBar, Color.BLACK);
            }});
        //normal Button
        m = new Button();
        m.setSize(100,100);
        m.setLabel("MEDIUM");
        m.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                setStrategy(2);
                GameLogger.getInstance().log.info("Game set to normal mode");
                frame.setVisible(false);
                gameController = GameEngine.start("HeadCup", new Circus(resolution.getWidth(),resolution.getHeight(), strategy), menuBar, Color.BLACK);
            }});
        //hard Button
        h = new Button();
        h.setSize(100,100);
        h.setLabel("HARD");
        h.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                setStrategy(3);
                GameLogger.getInstance().log.info("Game set to hard mode");
                frame.setVisible(false);
                gameController = GameEngine.start("HeadCup"
                        , new Circus(resolution.getWidth(),resolution.getHeight(), strategy)
                        , menuBar, Color.BLACK);
            }});
//        return  strategy;
    }

    public void setStrategy(int x){
        if(x==1){
            strategy=new easy();
        }else if(x==2){
            strategy = new normal();
        }else if(x==3){
            strategy = new hard();
        }
    }

    public Strategy getStrategy() {
        return strategy;
    }


    public Button getE() {
        return e;
    }

    public Button getM() {
        return m;
    }

    public Button getH() {
        return h;
    }

}
