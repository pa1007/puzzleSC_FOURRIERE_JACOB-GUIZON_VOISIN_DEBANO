package dev.pa1007.utils;

import dev.pa1007.game.Puzzle;
import org.apache.commons.io.FileUtils;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;

public class SaveObject implements Serializable {

    private Puzzle puzzle;

    private boolean graph;

    private String image;

    /**
     * Constructor of SaveObject with custom image path
     * @param puzzle Puzzle object
     * @param pathImage Image file path
     * @throws IOException
     */
    public SaveObject(Puzzle puzzle, File pathImage) throws IOException {
        this.puzzle = puzzle;
        saveImage(pathImage);
        graph = true;
    }

    /**
     * Constructor of SaveObject with puzzle
     * @param puzzle Puzzle object
     */
    public SaveObject(Puzzle puzzle) { this.puzzle = puzzle; }

    /**
     * @return puzzle value
     */
    public Puzzle getPuzzle() {
        return puzzle;
    }

    /**
     * @return graph value
     */
    public boolean getGraph() {
        return graph;
    }

    /**
     * @return Image object
     * @throws IOException
     */
    public Image getImage() throws IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(image);
        File   file         = new File("savedImage.jpg");
        FileUtils.writeByteArrayToFile(file, decodedBytes);
        return ImageIO.read(file);
    }

    /**
     * @param path Path where image is saved
     * @throws IOException
     */
    private void saveImage(File path) throws IOException {
        byte[] fileContent = FileUtils.readFileToByteArray(path);
        image = Base64.getEncoder().encodeToString(fileContent);
    }
}
