import Model.strategy_difficulty.*;
import View.MainMenu;
import View.ScreenResolution;

public class Main {

    public static void main(String[] args) {

        /* using menus in the game */
        final Strategy[] strategy = {null};
        ScreenResolution resolution = new ScreenResolution();
        MainMenu mainMenu = new MainMenu(strategy[0],resolution);
        mainMenu.start();

    }

}
