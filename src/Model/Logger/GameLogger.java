package Model.Logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.logging.FileHandler;

public class GameLogger {
    private static GameLogger instance;

    public static GameLogger getInstance(){

        if(GameLogger.instance==null){

            GameLogger.instance = new GameLogger();
        }


        return GameLogger.instance;
    }

    public org.apache.log4j.Logger log;

    private FileHandler fh;

    private GameLogger() {

        //final File f = new File("log.txt");
//        if (!f.exists()) {
//            try {
//                f.createNewFile();
//            } catch (final IOException e) {
//                System.out.println("Failed to create log file.");
//                e.printStackTrace();
//            }
//        }
//        try {
//            fh = new FileHandler("log.txt", true);
//        } catch (SecurityException | IOException e) {
//            System.out.println("Failed to handle log file.");
//            e.printStackTrace();
//        } // Appends to log.txt file.
        log = Logger.getLogger("MainLog");
        try {
            PropertyConfigurator.configure("config.properties");
        } catch (Exception e){
            this.log.error("Can't load Configuration file");
        }

        //fh.setFormatter(new SimpleFormatter());
        //log.setLevel(Level.INFO);
//        GameLogger.getInstance().log.debug("Logger initialized successfully!");
    }

}
