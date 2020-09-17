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
}
