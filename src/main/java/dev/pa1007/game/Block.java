package dev.pa1007.game;

import java.io.Serializable;

public abstract class Block implements Serializable {


    /**
     * The number assigned to the block for counting the victory.
     */
    private int number;

    /**
     * The position.
     */
    private Position currentPos;


    /**
     * The stating position for the puzzle.
     */
    private Position startPos;

    public Block(int number, Position startPos) {
        this.number = number;
        this.startPos = startPos;
    }

    /**
     * @return The stating position for the puzzle.
     */
    public Position getStartPos() {
        return this.startPos;
    }

    /**
     * Sets the <code>startPos</code> field.
     *
     * @param startPos The stating position for the puzzle.
     */
    public void setStartPos(Position startPos) {
        this.startPos = startPos;
    }

    /**
     * Method to test the position of the block at a given moment
     *
     * @return A boolean knowing witch position is the block
     */
    public boolean goodPlace() {
        return startPos.equals(currentPos);
    }

    /**
     * @return The position.
     */
    public Position getCurrentPos() {
        return this.currentPos;
    }

    /**
     * Sets the <code>currentPos</code> field.
     *
     * @param currentPos The position.
     */
    public void setCurrentPos(Position currentPos) {
        this.currentPos = currentPos;
    }

    /**
     * @return The number assigned to the block for counting the victory.
     */
    public int getNumber() {
        return this.number;
    }

    /**
     * Sets the <code>number</code> field.
     *
     * @param number The number assigned to the block for counting the victory.
     */
    public void setNumber(int number) {
        this.number = number;
    }


    @Override
    public String toString() {
        return "Block{" +
                "number=" + number +
                ", currentPos=" + currentPos +
                ", startPos=" + startPos +
                '}';
    }
}
