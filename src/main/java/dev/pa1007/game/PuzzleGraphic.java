package dev.pa1007.game;

import dev.pa1007.MainApp;
import dev.pa1007.game.draw.BlockGraphic;
import dev.pa1007.game.draw.BlockVoid;
import dev.pa1007.game.draw.StopwatchTimer;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class PuzzleGraphic extends Puzzle {

    private static final int     WIDTH  = 600;
    private static final int     HEIGHT = 600;
    private              boolean numberOnly;
    private transient    Image   image;
    private              String  imagePath;

    /**
     * Create graphical puzzle with default image
     *
     * @param maxX number of row
     * @param maxY number of column
     */
    public PuzzleGraphic(int maxX, int maxY) {
        super(maxX, maxY);
        this.imagePath = URLDecoder.decode(
                MainApp.class.getResource("images/taquin.png").getPath(),
                StandardCharsets.UTF_8
        );
    }

    /**
     * Create graphical puzzle with custom image
     *
     * @param maxX      number of row
     * @param maxY      number of column
     * @param imagePath path of the image
     */
    public PuzzleGraphic(int maxX, int maxY, String imagePath) {
        super(maxX, maxY);
        this.imagePath = imagePath;
    }

    /**
     * Call initGraphicBlock and initGameGraph to initialise the game
     */
    @Override
    public void init() {
        blocks = new ArrayList<>();
        try {
            initGraphicBlock(imagePath);
            initGameGraph();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialise the graphical block array
     *
     * @param path Path of the image
     */
    public void initGraphicBlock(String path) throws IOException {
        Image scaledInstance = ImageIO.read(new File(path)).getScaledInstance(
                WIDTH,
                HEIGHT,
                Image.SCALE_DEFAULT
        );
        image = scaledInstance;
        BufferedImage read = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        read.getGraphics().drawImage(scaledInstance, 0, 0, null);
        int h   = read.getHeight() / maxX - 1;
        int w   = read.getWidth() / maxY - 1;
        int nb  = 1;
        int max = (maxX * maxY);
        for (int j = 0; j < WIDTH && nb != max; j += w) {
            for (int i = 0; i < HEIGHT; i += h) {
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

    /**
     * Update the UI
     *
     * @param gameG GridPane bind to the game
     * @param clock Clock timer bind to the game
     */
    public void update(GridPane gameG, Text clock) {
        gameG.getChildren().clear();
        List<Block> posVoid = this.getAroundVoid();
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                int finalI = i;
                int finalJ = j;
                Block block = blocks.stream().filter(it -> it.getCurrentPos().equals(new Position(
                        finalI,
                        finalJ
                ))).findFirst().get();
                Node child;
                if (block instanceof BlockVoid) {
                    child = new ImageView(SwingFXUtils.toFXImage(((BlockVoid) block).getImage(), null));
                    ((ImageView) child).setFitHeight(50);
                    ((ImageView) child).setFitWidth(50);
                }
                else {
                    if (numberOnly) {
                        Pane  child1 = new AnchorPane();
                        Label e      = new Label(String.valueOf(block.getNumber()));
                        e.getStyleClass().add("numberitem");
                        child1.getStyleClass().add("numberpane");
                        child1.setBorder(new Border(new BorderStroke(
                                Color.BLACK,
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(12),
                                new BorderWidths(25)
                        )));
                        child1.setBackground(new Background(new BackgroundFill(
                                Color.GREY,
                                new CornerRadii(30),
                                null
                        )));
                        e.setFont(new Font(30));
                        e.setMaxWidth(Double.MAX_VALUE);
                        AnchorPane.setLeftAnchor(e, 0.0);
                        AnchorPane.setTopAnchor(e, 0.0);
                        AnchorPane.setBottomAnchor(e, 0.0);
                        AnchorPane.setRightAnchor(e, 0.0);
                        e.setAlignment(Pos.CENTER);
                        child1.getChildren().add(e);
                        child = child1;
                    }
                    else {
                        child = new ImageView(convertToFxImage(((BlockGraphic) block).getImage()));
                    }
                    if (posVoid.contains(block)) {
                        EventHandler<? super MouseEvent> eventHandler = (EventHandler<MouseEvent>) event -> {
                            Position b = posVoid.get(posVoid.indexOf(block)).getCurrentPos().clone();
                            block.setCurrentPos(voidBlock.getCurrentPos().clone());
                            voidBlock.setCurrentPos(b);
                            startTimer(clock);
                            update(gameG, clock);
                        };
                        child.setOnMouseClicked(eventHandler);
                    }
                }
                gameG.add(child, j, i);
            }
        }
    }

    /**
     * @return Boolean is the game is number only or not
     */
    public boolean getNumberOnly() {
        return numberOnly;
    }

    /**
     * @param numberOnly set the boolean to make the game number only
     */
    public void setNumberOnly(boolean numberOnly) {
        this.numberOnly = numberOnly;
    }

    /**
     * @return Array of blocks who are around the void
     */
    public List<Block> getAroundVoid() {
        List<Position> p = voidBlock.getCurrentPos().getSurrounding();
        return blocks.stream().filter(bCur -> p.stream().anyMatch((val) -> bCur.getCurrentPos().equals(val))).collect(
                Collectors.toList());
    }

    /**
     * @return Path of the image
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * @param imagePath set the image path.
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * Set the graphical element with the image in param
     *
     * @param image image object
     */
    public void loadImage(Image image) {
        long t = this.timer.getTime();
        this.timer.stop();
        this.timer = new StopwatchTimer();
        timer.setTimel(t);
        Image scaledInstance = image.getScaledInstance(
                WIDTH,
                HEIGHT,
                Image.SCALE_DEFAULT
        );
        this.image = scaledInstance;
        BufferedImage read = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        int           h    = read.getHeight() / maxX - 1;
        int           w    = read.getWidth() / maxY - 1;
        read.getGraphics().drawImage(scaledInstance, 0, 0, null);
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                int finalJ = j;
                int finalI = i;
                Optional<Block> first = blocks.stream().filter((
                                                                       block -> (
                                                                               block.getStartPos().equals(
                                                                                       new Position(
                                                                                               finalI,
                                                                                               finalJ
                                                                                       ))
                                                                               && !(block instanceof BlockVoid)
                                                                       )
                                                               )).findFirst();
                if (first.isPresent()) {
                    ((BlockGraphic) first.get()).setImage(read.getSubimage(i * w, j * h, w, h));
                }
            }
        }
    }

    /**
     * Initialise the game board
     */
    private void initGameGraph() {
        this.voidBlock = new BlockVoid(new Position(maxX - 1, maxY - 1));
        blocks.add(voidBlock);
        Collections.shuffle(blocks);
        int tot = 0;
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                blocks.get(tot).setCurrentPos(new Position(i, j));
                tot++;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        PuzzleGraphic that = (PuzzleGraphic) o;
        return numberOnly == that.numberOnly && Objects.equals(image, that.image) && Objects.equals(
                imagePath,
                that.imagePath
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOnly, image, imagePath);
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
