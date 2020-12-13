package dev.pa1007.game;

import dev.pa1007.game.draw.BlockGraphic;
import dev.pa1007.game.draw.BlockVoid;
import java.util.ArrayList;
import java.util.StringJoiner;

public class PuzzleAITemplate extends Puzzle {


    private PuzzleAITemplate(int maxX, int maxY) {
        super(maxX, maxY);
    }

    @Override
    public void init() {
        // nothing
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PuzzleAITemplate.class.getSimpleName() + "[\n", "]\n\t")
                .add("blocks=" + blocks)
                .add("\nvoidBlock=" + voidBlock)
                .add("\nsolved=" + isSolved())
                .add("\nnbBlock=" + blocks.size())
                .toString();
    }

    public static PuzzleAITemplate createFromPz(Puzzle puzzle) {
        PuzzleAITemplate puzzleAITemplate = new PuzzleAITemplate(puzzle.maxX, puzzle.maxY);
        puzzleAITemplate.blocks = new ArrayList<>();
        BlockVoid e = new BlockVoid(puzzle.voidBlock);
        for (Block block : puzzle.getBlocks()) {
            if (!(block instanceof BlockVoid)) {
                puzzleAITemplate.blocks.add(new BlockGraphic((BlockGraphic) block));
            }
            else {
                puzzleAITemplate.blocks.add(e);
            }
        }
        puzzleAITemplate.voidBlock = e;
        return puzzleAITemplate;
    }
}
