package dev.pa1007.ai;

import dev.pa1007.game.Position;
import dev.pa1007.game.Puzzle;
import dev.pa1007.game.PuzzleAITemplate;
import java.math.BigInteger;
import java.util.Random;

public class AIUtils {

    /**
     * Not a usable class, just an helper class
     */
    private AIUtils() {

    }

    /**
     * To test if the position is in the puzzle
     *
     * @param p  The  position to test
     * @param pz the puzzle
     * @return true if in, false if not
     */
    public static boolean inRange(Position p, Puzzle pz) {
        int x = p.getX();
        int y = p.getY();

        return y >= 0 && y < pz.getMaxY() && x >= 0 && x < pz.getMaxY();

    }

    /**
     * Get a random between 2 numbers; recreate a random for a new seed
     *
     * @param low  low part
     * @param high high part
     * @return a random between 2 numbers
     */
    public static int randBetween(int low, int high) {
        Random r = new Random();
        return r.nextInt(high - low) + low;
    }

    /**
     * @param currentPos
     * @param move
     * @return
     */
    public static int calculateResult(Position currentPos, Position move) {
        return currentPos.getWhere(move);

    }

    /**
     * This method is used for moving the void block on a puzzle and test if it is possible to move, used to refactor code
     *
     * @param currentPos the current position of the block
     * @param position   the target position
     * @param pz         the puzzle to move on
     */
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

    /**
     * Calculate a factorial for getting the number of possible result
     *
     * @param n the number of block
     * @return a factorial
     */
    public static BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    /**
     * Calculate manhattan Distance
     *
     * @param po1 first
     * @param po2 second
     * @return a distance
     */
    public static long manhattanDistance(Position po1, Position po2) {
        return Math.abs(po1.getX() - po2.getX()) + Math.abs(po1.getY() - po2.getY());
    }
}
