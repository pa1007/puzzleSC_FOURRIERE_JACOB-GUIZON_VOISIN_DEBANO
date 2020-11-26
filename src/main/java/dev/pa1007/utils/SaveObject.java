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

    public SaveObject(Puzzle puzzle, File pathImage) throws IOException {
        this.puzzle = puzzle;
        saveImage(pathImage);
        graph = true;
    }

    public SaveObject(Puzzle puzzle) { this.puzzle = puzzle; }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public boolean getGraph() {
        return graph;
    }

    public Image getImage() throws IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(image);
        File   file         = new File("savedImage.jpg");
        FileUtils.writeByteArrayToFile(file, decodedBytes);
        return ImageIO.read(file);
    }

    private void saveImage(File path) throws IOException {
        byte[] fileContent = FileUtils.readFileToByteArray(path);
        image = Base64.getEncoder().encodeToString(fileContent);
    }
}
