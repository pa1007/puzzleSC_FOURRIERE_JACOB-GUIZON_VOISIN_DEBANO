package dev.pa1007.ai;

import dev.pa1007.game.Position;
import dev.pa1007.game.Puzzle;
import dev.pa1007.game.PuzzleAITemplate;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class AIAlgo implements AI {

    private boolean            auto;
    private LinkedList<Puzzle> moves;

    @Override
    public int faireChoix(Puzzle pz) {
        Position currentPos = pz.getVoidBlock().getCurrentPos();
        Position nextMove;
        if (auto) {
            if (!moves.isEmpty()) {
                Puzzle nxtMove = moves.removeFirst();
                nextMove = nxtMove.getVoidBlock().getCurrentPos();
                return AIUtils.calculateResult(currentPos, nextMove);
            }
        }
        moves = dijkstraSolve(pz);
        //Not working;

        Puzzle nxtMove = moves.removeFirst();
        nextMove = nxtMove.getVoidBlock().getCurrentPos();
        int i = AIUtils.calculateResult(currentPos, nextMove);
        return i;

    }

    public void setAuto(boolean auto) {
        this.auto = auto;
    }

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
