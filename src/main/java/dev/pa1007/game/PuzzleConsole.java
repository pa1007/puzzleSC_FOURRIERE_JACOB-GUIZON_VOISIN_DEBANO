package dev.pa1007.game;

import dev.pa1007.game.draw.BlockString;
import dev.pa1007.game.draw.BlockVoid;
import dev.pa1007.utils.LoadSaveException;
import dev.pa1007.utils.Save;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PuzzleConsole extends Puzzle {
    public PuzzleConsole(int maxX, int maxY) {
        super(maxX, maxY);
    }

    @Override
    public void init() {
        initStringBlock(maxX, maxY);
        initGame(maxX, maxY);
    }

    public void initStringBlock(int x, int y) {
        blocks = new ArrayList<>();
        int pos = 0;
        int max = x * y - 1;
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                blocks.add(new BlockString(pos, new Position(i, j)));
                pos++;
                if (pos == max) {
                    break;
                }
            }
        }
    }

    public void initGame(int x, int y) {
        Collections.shuffle(blocks);
        this.voidBlock = new BlockVoid(new Position(x - 1, y - 1));
        blocks.add(voidBlock);
        int tot = 0;
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                blocks.get(tot).setCurrentPos(new Position(i, j));
                tot++;
            }
        }
    }

    public void startGameLine() {
        Scanner sc = new Scanner(System.in);
        String  s;
        while (!this.isSolved()) {
            System.out.println(this.createString());
            System.out.println("[g] l | [d] → | [h] ↑ | [b] ↓ | [s] sauvegarder | [c] charger");
            s = sc.nextLine();
            while (!(s.equals("g") || s.equals("d") || s.equals("h") || s.equals("b") || s.equals("s") || s.equals("c"))) {
                System.out.println("Entrez une valeur correcte");
                s = sc.nextLine();
            }
            switch (s.toLowerCase()) {
                case "b" -> move(1, 0);
                case "h" -> move(-1, 0);
                case "d" -> move(0, 1);
                case "g" -> move(0, -1);
                case "s" -> {
                    try {
                        Save.save(this);
                    }
                    catch(IOException e){
                        System.out.println("Echec de la sauvegarde.");
                    }
                }
                case "c" -> {
                    try {
                        Puzzle load = Save.load();
                        PuzzleConsole t = (PuzzleConsole) load;
                        this.blocks = t.blocks;
                        this.voidBlock = t.voidBlock;
                        this.timer = t.timer;
                    }
                    catch(IOException | LoadSaveException e) {
                        System.out.println("Echec du chargement");
                    }

                }
            }
        }
    }

    public String createString() {
        StringBuilder sb = new StringBuilder(getPremiereLigne());
        var ref = new Object() { // Object for final usage in lambda
            int lineF = 0;
            int lineNb = 1;
            int lastNbLine = 0;
        };
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                int finalI = i;
                int finalJ = j;
                Block block = blocks.stream().filter(it -> it.getCurrentPos().equals(new Position(
                        finalI,
                        finalJ
                ))).findFirst().get();
                if (ref.lastNbLine != ref.lineNb) {
                    sb.append(makeNumberDoubleDigit(ref.lineNb)).append("|");
                    ref.lastNbLine = ref.lineNb;
                }
                int nb = block.getNumber();
                sb.append(" ").append(makeNumberDoubleDigit(nb));
                ref.lineF++;
                if (ref.lineF + 1 > maxX) {
                    sb.append(" \n");
                    ref.lineF = 0;
                    ref.lineNb++;
                }

            }
        }

        return sb.toString();

    }

    public List<Block> getAroundVoid() {
        List<Position> p = voidBlock.getCurrentPos().getSurrounding();
        return blocks.stream().filter(bCur -> p.stream().anyMatch((val) -> bCur.getCurrentPos().equals(val))).collect(
                Collectors.toList());
    }

    private String makeNumberDoubleDigit(int nb) {
        return nb < 10 && nb >= 0 ? "0" + nb : String.valueOf(nb);
    }

    private String getPremiereLigne() {
        char[]        alpha = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
        StringBuilder sb    = new StringBuilder("    ");
        int           t     = maxX;
        for (int i = 0; i < t; i++) {
            sb.append(" ").append(alpha[i]).append(" ");
        }
        sb.append("\n");
        return sb.toString();
    }
}
