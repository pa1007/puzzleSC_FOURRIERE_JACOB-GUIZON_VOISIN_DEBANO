package dev.pa1007.game;

import dev.pa1007.game.draw.BlockVoid;
import dev.pa1007.game.draw.StopwatchTimer;
import javafx.scene.text.Text;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public abstract class Puzzle implements Serializable {


    /**
     * The list of main blocks in the puzzle game.
     */
    protected List<Block> blocks;


    /**
     * The max X.
     */
    protected int maxX;


    /**
     * The min Y.
     */
    protected int       maxY;
    protected BlockVoid voidBlock;

    /**
     * the timer.
     */
    protected StopwatchTimer timer;

    public Puzzle(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        timer = new StopwatchTimer();
    }

    public BlockVoid getVoidBlock() {
        return voidBlock;
    }

    /**
     * @return the timer.
     */
    public StopwatchTimer getTimer() {
        return this.timer;
    }


    public void startTimer(Text clock) {
        if (timer.isStop()) {
            timer.setText(clock);
            timer.startTimer(timer.getTime());
        }
    }

    public void stopTimer() {
        timer.stopTimer(timer.getTime());
    }


    /**
     * @return The min Y.
     */
    public int getMaxY() {
        return this.maxY;
    }

    /**
     * Sets the <code>maxY</code> field.
     *
     * @param maxY The min Y.
     */
    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    /**
     * @return The max X.
     */
    public int getMaxX() {
        return this.maxX;
    }

    /**
     * Sets the <code>maxX</code> field.
     *
     * @param maxX The max X.
     */
    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

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
        return blocks.stream().allMatch(Block::goodPlace);
    }

    public abstract void init();

    /**
     * Switch between void block and block at position passed in param
     *
     * @param x row
     * @param y column
     */
    public void move(int x, int y) {
        Position p = voidBlock.getCurrentPos().getNear(x, y).clone();
        Block    b = blocks.stream().filter(bCur -> bCur.getCurrentPos().equals(p)).findFirst().orElse(null);
        if (b != null) {
            b.setCurrentPos(voidBlock.getCurrentPos().clone());
            voidBlock.setCurrentPos(p);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Puzzle puzzle = (Puzzle) o;
        return maxX == puzzle.maxX
               && maxY == puzzle.maxY
               && Objects.equals(blocks, puzzle.blocks)
               && Objects.equals(voidBlock, puzzle.voidBlock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blocks, maxX, maxY, voidBlock, timer);
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", Puzzle.class.getSimpleName() + "[", "]")
                .add("blocks=" + blocks)
                .add("maxX=" + maxX)
                .add("maxY=" + maxY)
                .add("voidBlock=" + voidBlock)
                .toString();
    }
}
