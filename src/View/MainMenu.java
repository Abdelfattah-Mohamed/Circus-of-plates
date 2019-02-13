package View;

import Control.ButtonGenerator;
import Model.Logger.GameLogger;
import eg.edu.alexu.csd.oop.game.GameEngine;
import Model.strategy_difficulty.Strategy;

public class MainMenu {

	private ScreenResolution resolution;

	public MainMenu(Strategy s, ScreenResolution screenResolution) {
		resolution = screenResolution;
	}

	public void start() {
		final GameEngine.GameController[] gameController = new GameEngine.GameController[1];
		ButtonGenerator buttonGenerator = new ButtonGenerator(gameController[0], resolution, null);
		buttonGenerator.startMenu();
		buttonGenerator.generate();
		GameDifficultyBox difficultyBox = new GameDifficultyBox(gameController[0], resolution, buttonGenerator.menuBar);
		difficultyBox.setFrame();
		buttonGenerator.setFrame(difficultyBox.getFrame());
		difficultyBox.setButtonGenerator(buttonGenerator);
		difficultyBox.generate();
		GameLogger.getInstance().log.info("Main Menu Started Sucessfully!");
	}
}
