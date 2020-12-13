package dev.pa1007.game.draw;

import dev.pa1007.MainApp;
import dev.pa1007.game.Block;
import dev.pa1007.game.Position;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BlockVoid extends Block {

    private transient BufferedImage image;

    /**
     * @param startPos position of the block
     */
    public BlockVoid(Position startPos) {
        super(-1, startPos);
        this.setCurrentPos(startPos);
        try {
            image = ImageIO.read(MainApp.class.getResource("images/void.png"));
        }
        catch (IOException ignored) {
            image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
        }
    }

    public BlockVoid(BlockVoid voidBlock) {
        super(-1,voidBlock.getStartPos().clone());
        this.setCurrentPos(voidBlock.getCurrentPos().clone());
        image = voidBlock.image;
    }

    /**
     * @return BufferedImage
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * setter of the image
     *
     * @param image BufferedImage
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
