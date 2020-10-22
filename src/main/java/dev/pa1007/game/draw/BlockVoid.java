package dev.pa1007.game.draw;

import dev.pa1007.MainApp;
import dev.pa1007.game.Block;
import dev.pa1007.game.Position;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BlockVoid extends Block {

    private transient BufferedImage image;

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

    public BufferedImage getImage() {
        return image;
    }
}
