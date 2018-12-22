import Logger.GameLogger;

import java.awt.*;

public class ScreenResolution {

    private int height;
    private int width;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void getScreenResolution(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       // GameLogger.getInstance().log.debug("Screen Resolution is: " + width + "x" + height);
        height = screenSize.height - 100;
        width = screenSize.width - 100;
    }

    public ScreenResolution() {
        getScreenResolution();
    }
}
