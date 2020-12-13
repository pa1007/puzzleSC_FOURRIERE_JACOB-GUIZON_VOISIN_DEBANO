package dev.pa1007.ai;

import dev.pa1007.game.Position;
import dev.pa1007.game.Puzzle;
import dev.pa1007.game.PuzzleAITemplate;
import java.math.BigInteger;
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

    public static void calculateAndChangePuzzle(
            Position currentPos,
            Position position,
            PuzzleAITemplate pz
    ) {
        int i = AIUtils.calculateResult(currentPos, position);
        switch (i) {
            case 1: // Up
                pz.move(-1, 0);
                break;
            case 2: //right
                pz.move(0, 1);
                break;
            case 3: //down
                pz.move(1, 0);
                break;
            case 4: //left
                pz.move(0, -1);
                break;
            case 5:
            case 0:
            default:
                break;
        }
    }

    public static BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    public static long manhattanDistance(Position po1, Position po2) {
        return Math.abs(po1.getX() - po2.getX()) + Math.abs(po1.getY() - po2.getY());
    }
}
