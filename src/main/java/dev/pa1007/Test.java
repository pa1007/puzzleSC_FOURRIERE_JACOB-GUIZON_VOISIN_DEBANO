package dev.pa1007;

import dev.pa1007.game.Puzzle;
import dev.pa1007.game.PuzzleConsole;

public class Test {

    public static void main(String[] arguments) {
        PuzzleConsole p = new PuzzleConsole(4,4);
        p.init();
        p.startGameLine();
    }
}
