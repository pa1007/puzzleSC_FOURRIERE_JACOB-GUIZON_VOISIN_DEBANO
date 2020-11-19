package dev.pa1007.game.draw;

import dev.pa1007.game.Block;
import dev.pa1007.game.Position;
import java.awt.image.BufferedImage;

public class BlockGraphic extends Block {

    private transient BufferedImage image;

    public BlockGraphic(int number, Position startPos, BufferedImage image) {
        super(number, startPos);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
