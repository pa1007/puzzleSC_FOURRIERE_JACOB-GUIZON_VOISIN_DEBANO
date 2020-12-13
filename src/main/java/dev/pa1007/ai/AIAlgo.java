package dev.pa1007.ai;

import dev.pa1007.game.Position;
import dev.pa1007.game.Puzzle;
import dev.pa1007.game.PuzzleAITemplate;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class AIAlgo implements AI {

    /**
     * Set the cache system if this was to be reused for for not recalculating anythings
     */
    private boolean auto;


    /**
     * Caches the moves for victory
     */
    private LinkedList<Puzzle> moves;

    /**
     * This method is used for making the move
     *
     * @param pz the puzzle
     * @return 1 is on top,2 is on the right,3 is besides 4 is on the left, 0 are the same,5 anything,6 if not possible
     * @see AIUtils#calculateResult(Position, Position)
     * @see #dijkstraSolve(Puzzle)
     */
    @Override
    public int faireChoix(Puzzle pz) {
        Position currentPos = pz.getVoidBlock().getCurrentPos();
        Position nextMove;
        if (auto) {
            if (moves != null && !moves.isEmpty()) {
                Puzzle nxtMove = moves.removeFirst();
                nextMove = nxtMove.getVoidBlock().getCurrentPos();
                return AIUtils.calculateResult(currentPos, nextMove);
            }
        }
        moves = dijkstraSolve(pz);
        if (moves.isEmpty()) {
            return 6;
        }
        Puzzle nxtMove = moves.removeFirst(); // Remove actual state
        nxtMove = moves.removeFirst();
        System.out.println(nxtMove.toString());
        nextMove = nxtMove.getVoidBlock().getCurrentPos();
        System.out.println(currentPos);
        System.out.println(nextMove);
        System.out.println(AIUtils.manhattanDistance(currentPos, nextMove));
        int i = AIUtils.calculateResult(currentPos, nextMove);
        System.out.println(i);
        return i;

    }

    public void setAuto(boolean auto) {
        this.auto = auto;
    }

    /**
     * This is the dijkstra algorithms for the game,it works but take a lot of times, need rework and multithreading for working better
     *
     * @param puzzle the start puzzle
     * @return a list of moves done
     */
    public LinkedList<Puzzle> dijkstraSolve(Puzzle puzzle) {
        int        nbBlock = puzzle.getBlocks().size();
        BigInteger nbMove  = AIUtils.factorial(nbBlock + 1).divide(BigInteger.TWO);
        System.out.println(nbMove.toString());
        Queue<Puzzle>       toVisit     = new LinkedList<>();
        Map<Puzzle, Puzzle> predecessor = new HashMap<>();
        toVisit.add(puzzle);
        predecessor.put(puzzle, null);
        int cnt = 0;
        while (!toVisit.isEmpty()) {
            Puzzle candidate = toVisit.remove();
            if (nbMove.compareTo(BigInteger.valueOf(cnt)) < 0) {
                System.err.println("Not solvable");
                break;
            }
            cnt++;
            if (cnt % 10000 == 0) {
                System.out.printf("Considered %,d positions. Queue = %,d\n", cnt, toVisit.size());
            }

            if (candidate.isSolved()) {
                System.out.printf("Solution considered %d boards\n", cnt);
                LinkedList<Puzzle> solution  = new LinkedList<>();
                Puzzle             backtrace = candidate;
                while (backtrace != null) {
                    solution.addFirst(backtrace);
                    backtrace = predecessor.get(backtrace);
                }
                return solution;
            }


            List<Puzzle> puzzles = calculatePuzzlesPossible(candidate);
            for (Puzzle fp : puzzles) {
                if (!predecessor.containsKey(fp)) {
                    predecessor.put(fp, candidate);
                    toVisit.add(fp);
                }
            }
        }
        return new LinkedList<>();
    }

    /**
     * Create and return all the possible worlds with all possible position of the void blocks
     *
     * @param p the puzzle to start with
     * @return all the possible place the void block can move
     */
    private List<Puzzle> calculatePuzzlesPossible(Puzzle p) {
        Position       currentPos  = p.getVoidBlock().getCurrentPos();
        List<Position> surrounding = currentPos.getSurrounding();
        List<Position> collect = surrounding.stream().filter(position -> AIUtils.inRange(position, p)).collect(
                Collectors.toList());
        List<Puzzle> res = new ArrayList<>();
        for (Position position : collect) {
            PuzzleAITemplate pz = PuzzleAITemplate.createFromPz(p);
            AIUtils.calculateAndChangePuzzle(currentPos, position, pz);
            res.add(pz);
        }
        return res;
    }

}
