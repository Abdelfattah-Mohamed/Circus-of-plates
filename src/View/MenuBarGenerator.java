package View;

import Model.Logger.GameLogger;
import eg.edu.alexu.csd.oop.game.GameEngine;
import Model.strategy_difficulty.Strategy;
import Control.world_class.Circus;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBarGenerator extends Command {

	private Strategy strategy;

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	public MenuBarGenerator(GameEngine.GameController gameController, ScreenResolution resolution, JMenuBar menuBar) {
		super(gameController, resolution, menuBar);
	}

	public void generate() {
		menuBar = new JMenuBar();
		JMenuItem newgame = new JMenuItem("New");
		newgame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameController.changeWorld(new Circus(resolution.getWidth(), resolution.getHeight(), strategy));
			}
		});
		JMenu menu = new JMenu("File");
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameLogger.getInstance().log.info("Game Terminated");
				System.exit(0);
			}
		});
		JMenuItem pauseMenuItem = new JMenuItem("Pause");
		JMenuItem resumeMenuItem = new JMenuItem("Resume");
		menuBar.add(menu);
		pauseMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameController.pause();
			}
		});
		resumeMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameController.resume();
			}
		});
		menu.add(newgame);
		menu.addSeparator();
		menu.add(pauseMenuItem);
		menu.add(resumeMenuItem);
		menu.addSeparator();
		menu.add(exitMenuItem);
		menuBar.add(menu);
		GameLogger.getInstance().log.debug("MenuBar created");
	}

	public JMenuBar getMenuBar() {
		return this.menuBar;
	}

	public void setControl(GameEngine.GameController control) {
		this.gameController = control;
	}
}
