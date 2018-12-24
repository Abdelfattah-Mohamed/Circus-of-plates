package View;

import Control.ButtonGenerator;
import Model.Logger.GameLogger;
import eg.edu.alexu.csd.oop.game.GameEngine;

import javax.swing.*;





public class GameDifficultyBox extends Command {

    private ButtonGenerator buttonGenerator;
    private JFrame frame;

    public GameDifficultyBox(GameEngine.GameController gameController, ScreenResolution resolution, JMenuBar menuBar) {
        super(gameController, resolution, menuBar);
    }

    public void setButtonGenerator(ButtonGenerator buttonGenerator) {
        this.buttonGenerator = buttonGenerator;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void  generate(){

        JPanel panel = new JPanel();
        panel.add(buttonGenerator.getE());
        panel.add(buttonGenerator.getM());
        panel.add(buttonGenerator.getH());
        GameLogger.getInstance().log.debug("JPanal created");
        GameLogger.getInstance().log.debug("Buttons added to the JPanal");
        panel.setVisible(true);
        frame.add(panel);
        GameLogger.getInstance().log.debug("JPanal added to JFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void setFrame() {
        frame = new JFrame();
        GameLogger.getInstance().log.debug("JFrame created");
        frame.setSize(300,100);
    }
}
