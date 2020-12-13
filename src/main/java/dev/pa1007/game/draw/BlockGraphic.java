package dev.pa1007.game.draw;

import dev.pa1007.game.Block;
import dev.pa1007.game.Position;
import java.awt.image.BufferedImage;

public class BlockGraphic extends Block {

    private transient BufferedImage image;

    /**
     * Constructor of graphic block
     *
     * @param number   number of the block
     * @param startPos Position of the block
     * @param image    BufferedImage
     */
    public BlockGraphic(int number, Position startPos, BufferedImage image) {
        super(number, startPos);
        this.image = image;
    }

    public BlockGraphic(BlockGraphic block) {
        super(block.getNumber(), block.getStartPos());
        this.setCurrentPos(block.getCurrentPos());
        this.image = block.image;
    }

    /**
     * @return BufferedImage
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * setter of image
     *
     * @param image BufferedImage
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
