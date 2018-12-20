
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameEngine.GameController;
import strategy_difficulty.*;
import world_class.Circus;

public class Main {

    public static void main(String[] args) {
        //System.out.println("Uncomment any of the lines in the Main to run a new game, Have Fun :)");

        /* -------------------------------------------------------------------- */
        /* using default background color */
//		GameEngine.start("Very Simple Game in 99 Line of Code", new eg.edu.alexu.csd.oop.game.sample.world.Gold(400, 400));

        /* -------------------------------------------------------------------- */
        /* using background color at the game */
//		GameEngine.start("Very Simple Game in 99 Line of Code", new eg.edu.alexu.csd.oop.game.sample.world.Ball(700, 400), Color.YELLOW);

        /* -------------------------------------------------------------------- */
        /* controlling the behavior of the close button
         * alternatively, you can use JFrame.DISPOSE_ON_CLOSE, JFrame.HIDE_ON_CLOSE */
//		GameEngine.start("Very Simple Game in 99 Line of Code", new eg.edu.alexu.csd.oop.game.sample.world.Bubles(600, 600), JFrame.EXIT_ON_CLOSE);

        /* using menus in the game */
        JMenuBar  menuBar = new JMenuBar();;
        JMenu menu = new JMenu("File");
        JMenuItem  exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(exitMenuItem);
        menuBar.add(menu);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        GameEngine.start("Very Simple Game in 99 Line of Code", new Circus((int)screenSize.getWidth(), (int)screenSize.getHeight()-75, new easy()), menuBar, Color.BLACK);

        /* -------------------------------------------------------------------- */
        /* allow pause, resume, and restart multiple worlds in the same frame */
//		JMenuBar  menuBar = new JMenuBar();;
//		JMenu menu = new JMenu("File");
//		JMenuItem newMenuItem = new JMenuItem("New");
//		JMenuItem pauseMenuItem = new JMenuItem("Pause");
//		JMenuItem resumeMenuItem = new JMenuItem("Resume");
//		menu.add(newMenuItem);
//		menu.addSeparator();
//		menu.add(pauseMenuItem);
//		menu.add(resumeMenuItem);
//		menuBar.add(menu);
//		final GameController gameController = GameEngine.start("Very Simple Game in 99 Line of Code", new eg.edu.alexu.csd.oop.game.sample.world.Space(400, 700), menuBar, Color.BLACK);
//		newMenuItem.addActionListener(new ActionListener() {
//		@Override public void actionPerformed(ActionEvent e) {
//				gameController.changeWorld(new eg.edu.alexu.csd.oop.game.sample.world.Space(400, 700));
//			}
//		});
//		pauseMenuItem.addActionListener(new ActionListener() {
//		@Override public void actionPerformed(ActionEvent e) {
//				gameController.pause();
//			}
//		});
//		resumeMenuItem.addActionListener(new ActionListener() {
//			@Override public void actionPerformed(ActionEvent e) {
//				gameController.resume();
//			}
//		});
    }

}
