package dev.pa1007.game;

import dev.pa1007.Test;
import dev.pa1007.game.draw.BlockGraphic;
import dev.pa1007.game.draw.BlockVoid;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class PuzzleGraphic extends Puzzle {

    public PuzzleGraphic(int maxX, int maxY) {
        super(maxX, maxY);
    }

    @Override
    public void init() {

    }


    public void initImage() throws IOException {
        blocks = new ArrayList<>();
        initGraphicBlock(Test.class.getResource("images/test.jpg").getFile());
        initGameGraph();
    }

    public void initGraphicBlock(String path) throws IOException {
        int width  = 600;
        int height = 600;
        Image scaledInstance = ImageIO.read(new File(path)).getScaledInstance(
                width,
                height,
                Image.SCALE_DEFAULT
        );
        BufferedImage read = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        read.getGraphics().drawImage(scaledInstance, 0, 0, null);
        int h   = read.getHeight() / maxX - 1;
        int w   = read.getWidth() / maxY - 1;
        int nb  = 0;
        int max = (maxX * maxY) - 1;
        for (int j = 0; j < width && nb != max; j += w) {
            for (int i = 0; i < height; i += h) {
                int dh = h;
                if (i + w > read.getWidth()) {
                   continue;
                }
                if (j + h > read.getHeight()) {
                    dh = read.getHeight() - j;
                }
                blocks.add(new BlockGraphic(nb, new Position(j / w, i / h), read.getSubimage(i, j, w, dh)));
                nb++;
                if (nb == max) {
                    break;
                }
            }
        }

    }

    public void update(GridPane gameG) {
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                int finalI = i;
                int finalJ = j;
                Block block = blocks.stream().filter(it -> it.getCurrentPos().equals(new Position(
                        finalI,
                        finalJ
                ))).findFirst().get();
                ImageView child;
                if (block instanceof BlockVoid) {
                    child = new ImageView(SwingFXUtils.toFXImage(((BlockVoid) block).getImage(), null));
                    child.setFitHeight(50);
                    child.setFitWidth(50);
                }
                else {
                    child = new ImageView(convertToFxImage(((BlockGraphic) block).getImage()));
                }
                gameG.add(child, j, i);
            }
        }
    }

    private void initGameGraph() {
        Collections.shuffle(blocks);
        this.voidBlock = new BlockVoid(new Position(maxX - 1, maxY - 1));
        blocks.add(voidBlock);
        int tot = 0;
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                blocks.get(tot).setCurrentPos(new Position(i, j));
                tot++;
            }
        }
    }

    /**
     * This method is a fix for a bug we got from SwingFXUtils.toFXImage, a new object was not created and everyting was not working properly
     *
     * @param image an image to convert
     * @return a javafx image
     */
    private static javafx.scene.image.Image convertToFxImage(BufferedImage image) {
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }

        return new ImageView(wr).getImage();
    }

}
