package Model.Logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class GameLogger {
	private static GameLogger instance;

	public static GameLogger getInstance() {

		if (GameLogger.instance == null) {

			GameLogger.instance = new GameLogger();
		}

		return GameLogger.instance;
	}

	public org.apache.log4j.Logger log;

	private GameLogger() {

		log = Logger.getLogger("MainLog");
		try {
			PropertyConfigurator.configure("config.properties");
		} catch (Exception e) {
			this.log.error("Can't load Configuration file");
		}
	}

}
