package dev.pa1007.game;

import java.util.List;
import java.util.Objects;

public class Position {

    /**
     * The x coordinate.
     */
    private int x;
    /**
     * The y coordinate.
     */
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return The x coordinate.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Sets the <code>x</code> field.
     *
     * @param x The x coordinate.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return The y coordinate.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Sets the <code>y</code> field.
     *
     * @param y The y coordinate.
     */
    public void setY(int y) {
        this.y = y;
    }

    public boolean isSup(Position o2) {
        return x > o2.x || y > o2.y;
    }

    public Position getNear(int x, int y) {
        return new Position(this.x + x, this.y + y);
    }

    public List<Position> getSurrounding() {
        return List.of(getNear(-1, 0), getNear(-1, 1), getNear(0, -1), getNear(1, 0));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return x == position.x &&
               y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position{" +
               "x=" + x +
               ", y=" + y +
               '}';
    }
}
