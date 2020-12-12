package dev.pa1007.ai;

import dev.pa1007.game.Position;
import dev.pa1007.game.Puzzle;
import java.util.Random;

public class AIUtils {

    private AIUtils() {

    }

    public static boolean inRange(Position p, Puzzle pz) {
        int x = p.getX();
        int y = p.getY();

        return y >= 0 && y < pz.getMaxY() && x >= 0 && x < pz.getMaxY();

    }

    public static int randBetween(int low, int high) {
        Random r = new Random();
        return r.nextInt(high - low) + low;
    }

    public static int calculateResult(Position currentPos, Position move) {
        return currentPos.getWhere(move);

    }
}
