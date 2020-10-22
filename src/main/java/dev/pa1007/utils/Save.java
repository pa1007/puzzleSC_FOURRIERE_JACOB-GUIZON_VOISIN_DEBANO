package dev.pa1007.utils;

import dev.pa1007.game.Puzzle;
import java.io.*;

public class Save {

    private static final String PATH = "Save.dat";

    private Save() {

    }

    public static void save(Puzzle p, String path) throws IOException {
        ObjectOutputStream oIS = new ObjectOutputStream(new FileOutputStream(path));
        oIS.writeObject(p);
        oIS.close();
    }


    public static void save(Puzzle p) throws IOException {
        save(p, PATH);
    }

    public static Puzzle load() throws IOException, LoadSaveException {
        return load(PATH);
    }

    private static Puzzle load(String path) throws IOException, LoadSaveException {
        ObjectInputStream oIS = new ObjectInputStream(new FileInputStream(path));
        try {
            Puzzle o = (Puzzle) oIS.readObject();
            oIS.close();
            return o;
        }
        catch (ClassNotFoundException | InvalidClassException e) {
            throw new LoadSaveException();
        }
    }

}
