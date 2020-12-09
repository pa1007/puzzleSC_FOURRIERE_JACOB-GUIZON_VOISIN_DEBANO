package dev.pa1007.utils;

import dev.pa1007.MainApp;
import dev.pa1007.game.Puzzle;
import dev.pa1007.game.PuzzleGraphic;
import javax.imageio.ImageIO;
import java.io.*;

public class Save {

    private static final String PATH = "Save.dat";

    /**
     * default constructor
     */
    private Save() {}

    /**
     * Constructor with custom path
     * @param p SaveObject object who contains all game object
     * @param path Path where file is saved
     * @throws IOException
     */
    public static void save(SaveObject p, String path) throws IOException {
        ObjectOutputStream oIS = new ObjectOutputStream(new FileOutputStream(path));
        oIS.writeObject(p);
        oIS.close();
    }

    /**
     * Constructor with default path
     * @param p Puzzle object
     * @throws IOException
     */
    public static void save(Puzzle p) throws IOException {
        SaveObject saveObject;
        if (p instanceof PuzzleGraphic) {
            saveObject = new SaveObject(((PuzzleGraphic) p), new File(((PuzzleGraphic) p).getImagePath()));
        }
        else {
            saveObject = new SaveObject(p);
        }
        save(saveObject, PATH);
    }

    /**
     * Load the game with default path
     * @return Puzzle object
     * @throws IOException
     * @throws LoadSaveException
     */
    public static Puzzle load() throws IOException, LoadSaveException {
        return load(PATH);
    }

    /**
     * Load game with string path
     * @param path path of the file
     * @return Puzzle object
     * @throws IOException
     * @throws LoadSaveException
     */
    public static Puzzle load(String path) throws IOException, LoadSaveException {
        ObjectInputStream oIS = new ObjectInputStream(new FileInputStream(path));
        try {
            SaveObject o = (SaveObject) oIS.readObject();
            oIS.close();
            if (o.getGraph()) {
                o.getPuzzle().getVoidBlock().setImage(ImageIO.read(MainApp.class.getResource("images/void.png")));
                ((PuzzleGraphic) o.getPuzzle()).loadImage(o.getImage());
            }
            return o.getPuzzle();
        }
        catch (ClassNotFoundException | InvalidClassException e) {
            throw new LoadSaveException();
        }
    }
}
