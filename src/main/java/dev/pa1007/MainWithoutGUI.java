package dev.pa1007;

import dev.pa1007.game.PuzzleConsole;

public class MainWithoutGUI {

    public static void main(String[] arguments) {
        if (arguments.length > 0 && arguments[0].equals("-console")) {
            PuzzleConsole p = new PuzzleConsole(4, 4);
            p.init();
            p.startGameLine();
        }
        else {
            MainApp.main(arguments);
        }
    }
}
