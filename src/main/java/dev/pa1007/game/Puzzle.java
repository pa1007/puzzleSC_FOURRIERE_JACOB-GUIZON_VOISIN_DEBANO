package dev.pa1007.game;

import java.util.List;

public class Puzzle {


    /**
     * The list of main blocks in the puzzle game.
     */
    private List<Block> blocks;

    /**
     * @return The list of main blocks in the puzzle game.
     */
    public List<Block> getBlocks() {
        return this.blocks;
    }

    /**
     * Sets the <code>blocks</code> field.
     *
     * @param blocks The list of main blocks in the puzzle game.
     */
    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    /**
     * Check if puzzle has been solved.
     *
     * @return True if the puzzle has been solved and false otherwise.
     */
    public boolean isSolved() {
        for(Block b : blocks) {
            if(!b.goodPlace())
                return false;
        }
        return true;
    }
}
