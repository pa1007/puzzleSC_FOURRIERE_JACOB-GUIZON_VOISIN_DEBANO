package dev.pa1007.game;

import dev.pa1007.game.draw.BlockString;
import dev.pa1007.game.draw.BlockVoid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Puzzle {


    /**
     * The list of main blocks in the puzzle game.
     */
    private List<Block> blocks;


    /**
     * The max X.
     */
    private int maxX;


    /**
     * The min Y.
     */
    private int maxY;


    private BlockVoid voidBlock;

    public Puzzle(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    /**
     * @return The min Y.
     */
    public int getMaxY() {
        return this.maxY;
    }

    /**
     * Sets the <code>maxY</code> field.
     *
     * @param maxY The min Y.
     */
    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    /**
     * @return The max X.
     */
    public int getMaxX() {
        return this.maxX;
    }

    /**
     * Sets the <code>maxX</code> field.
     *
     * @param maxX The max X.
     */
    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    /**
     * @return The list of main blocks in the puzzle game.
     */
    public List<Block> getBlocks() {
        return this.blocks;
    }

    /**
     * Sets the <code>blocks</code> field.
     *
     * @param blocks The list of main blocks in the puzzle game.
     */
    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    /**
     * Check if puzzle has been solved.
     *
     * @return True if the puzzle has been solved and false otherwise.
     */
    public boolean isSolved() {
        return blocks.stream().allMatch(Block::goodPlace);
    }

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
            System.out.println("[g] l | [d] → | [h] ↑ | [b] ↓");
            s = sc.nextLine();
            while (!(s.equals("g") || s.equals("d") || s.equals("h") || s.equals("b"))) {
                System.out.println("Entrez une valeur correcte");
                s = sc.nextLine();
            }
            switch (s) {
                case "b" -> move(1, 0);
                case "h" -> move(-1, 0);
                case "d" -> move(0, 1);
                case "g" -> move(0, -1);
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

    private void move(int x, int y) {
        Position p = voidBlock.getCurrentPos().getNear(x, y).clone();
        Block    b = blocks.stream().filter(bCur -> bCur.getCurrentPos().equals(p)).findFirst().orElse(null);
        if (b != null) {
            b.setCurrentPos(voidBlock.getCurrentPos().clone());
            voidBlock.setCurrentPos(p);
        }
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
