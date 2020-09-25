package dev.pa1007;

import dev.pa1007.game.Puzzle;

public class Test {

    public static void main(String[] arguments) {
        Puzzle p = new Puzzle(3, 2);
        p.init();
        p.startGameLine();
    }
}
