package dev.pa1007.game.draw;

import dev.pa1007.game.Block;
import dev.pa1007.game.Position;

public class BlockVoid extends Block {

    public BlockVoid(Position startPos) {
        super(-1, startPos);
        this.setCurrentPos(startPos);
    }
}
