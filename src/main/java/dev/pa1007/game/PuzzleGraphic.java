package dev.pa1007.game;

import dev.pa1007.Test;
import dev.pa1007.game.draw.BlockGraphic;
import dev.pa1007.game.draw.BlockVoid;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PuzzleGraphic extends Puzzle {

    public PuzzleGraphic(int maxX, int maxY) {
        super(maxX, maxY);
    }


    public void initImage() throws IOException {
        blocks = new ArrayList<>();
        initGraphicBlock(Test.class.getResource("images/test.jpg").getFile());
        initGameGraph();
    }

    public void initGraphicBlock(String path) throws IOException {
        BufferedImage read = ImageIO.read(new File(path));
        int           h    = read.getHeight() / maxX - 2;
        int           w    = read.getWidth() / maxY - 2;
        System.out.println(h);
        System.out.println(maxX);
        System.out.println(w);
        System.out.println(maxY);
        int nb  = 0;
        int max = (maxX * maxY) - 1;
        for (int j = 0; j < read.getWidth() - w && nb != max; j += w) {
            for (int i = 0; i < read.getHeight() - h; i += h) {
                blocks.add(new BlockGraphic(nb, new Position(j / w, i / h), read.getSubimage(i, j, w, h)));
                nb++;
                if (nb == max) {
                    break;
                }
            }
        }
        System.out.println(blocks);
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
                }
                else {
                    child = new ImageView(SwingFXUtils.toFXImage(((BlockGraphic) block).getImage(), null));
                }
                child.setFitHeight(50);
                child.setFitWidth(50);
                gameG.add(child, j, i);
            }
        }

    }

    private void initGameGraph() {
        //Collections.shuffle(blocks);
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


}
