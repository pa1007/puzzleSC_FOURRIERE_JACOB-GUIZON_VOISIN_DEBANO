package dev.pa1007.game;

import dev.pa1007.game.draw.BlockVoid;
import dev.pa1007.game.draw.StopwatchTimer;
import javafx.scene.text.Text;
import java.util.List;

public abstract class Puzzle {


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
    private StopwatchTimer timer;

    public Puzzle(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        timer = new StopwatchTimer();
    }

    /**
     * @return the timer.
     */
    public StopwatchTimer getTimer() {
        return this.timer;
    }


    public void startTimer(Text clock) {
        if (!timer.isInterrupted() && !timer.isAlive()) {
            timer.setText(clock);
            timer.startTimer(0);
        }
        else if (timer.isInterrupted()) {
            restartTimer(clock);
        }
    }

    public void stopTimer() {
        timer.stopTimer(timer.getTime());
    }

    public void restartTimer(Text clock) {
        long t = timer.getTime();
        timer = new StopwatchTimer();
        timer.setText(clock);
        timer.startTimer(t);
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

    public void move(int x, int y) {
        Position p = voidBlock.getCurrentPos().getNear(x, y).clone();
        Block    b = blocks.stream().filter(bCur -> bCur.getCurrentPos().equals(p)).findFirst().orElse(null);
        if (b != null) {
            b.setCurrentPos(voidBlock.getCurrentPos().clone());
            voidBlock.setCurrentPos(p);
        }
    }
}
