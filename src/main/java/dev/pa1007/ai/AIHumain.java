package dev.pa1007.ai;

import dev.pa1007.game.Block;
import dev.pa1007.game.Position;
import dev.pa1007.game.Puzzle;
import dev.pa1007.game.PuzzleAITemplate;
import dev.pa1007.game.draw.BlockVoid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AIHumain implements AI {

    @Override
    public int faireChoix(Puzzle pz) {
        return search(pz);
    }

    @Override
    public void setAuto(boolean auto) {
        //not used
    }


    public int search(Puzzle pz) {
        List<Block> blocks  = pz.getBlocks();
        BlockVoid   voi     = pz.getVoidBlock();
        Position    voidPos = voi.getCurrentPos();

        int      nbToMove    = 0;
        int      nbCurr      = 0;
        Block    blockToMove = null;
        Position toHave      = null;
        for (int i = 0; i < pz.getMaxY() * pz.getMaxX(); i++) {
            Block iBlock = blocks.get(i);
            if (iBlock.getNumber() == i) {
                continue;
            }
            else {
                for (Block block : blocks) {
                    if (block.getNumber() == i) {
                        blockToMove = block;
                        nbToMove = i;
                        nbCurr = blocks.indexOf(block);
                        toHave = iBlock.getCurrentPos();
                        break;
                    }
                }
            }
        }
        if (blockToMove != null && toHave != null) {
            return getNextMove(pz, nbToMove, nbCurr, voidPos, blocks);

        }
        else {
            return 5;
        }

    }

    private int getNextMove(Puzzle pz, int nbToMove, int nbCurr, Position voidPos, List<Block> blocks) {
        List<Puzzle> puzzles = calculatePuzzlesPossible(pz);
        for (Puzzle puzzle : puzzles) {
            for (Block block : puzzle.getBlocks()) {
                if (block.getNumber() == nbToMove) {
                    if (blocks.indexOf(block) < nbCurr) {
                        System.out.println("ok");
                        System.out.println(voidPos);
                        System.out.println(puzzle.getVoidBlock().getCurrentPos());
                        return AIUtils.calculateResult(voidPos, puzzle.getVoidBlock().getCurrentPos());
                    }
                }
            }
        }
        System.out.println("Rand, ");
        return new AIRandom().faireChoix(pz);
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
