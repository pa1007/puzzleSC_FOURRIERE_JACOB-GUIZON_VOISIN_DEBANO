package dev.pa1007.utils;

import dev.pa1007.MainApp;
import dev.pa1007.game.Puzzle;
import dev.pa1007.game.PuzzleGraphic;
import javax.imageio.ImageIO;
import java.io.*;

public class Save {

    private static final String PATH = "Save.dat";

    private Save() {

    }

    public static void save(SaveObject p, String path) throws IOException {
        ObjectOutputStream oIS = new ObjectOutputStream(new FileOutputStream(path));
        oIS.writeObject(p);
        oIS.close();
    }


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

    public static Puzzle load() throws IOException, LoadSaveException {
        return load(PATH);
    }

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
