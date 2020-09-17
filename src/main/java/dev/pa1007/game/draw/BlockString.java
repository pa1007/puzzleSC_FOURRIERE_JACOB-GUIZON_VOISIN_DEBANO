package dev.pa1007.game.draw;

import dev.pa1007.game.Block;
import dev.pa1007.game.Position;

public class BlockString extends Block implements Drawable<StringBuilder> {

    public BlockString(int number, Position currentPos) {
        super(number, currentPos);
    }

    @Override
    public StringBuilder draw(StringBuilder on) {
        return on;
    }
}
