package dev.pa1007.game;

import dev.pa1007.game.draw.BlockString;
import dev.pa1007.game.draw.BlockVoid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        int max = x * y - 2;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                blocks.add(new BlockString(pos, new Position(i, j)));
                pos++;
                if (pos == max) {
                    break;
                }
            }
        }
    }

    public void initGame(int x, int y) {
        blocks.remove(blocks.size() - 1);
        Collections.shuffle(blocks);
        this.voidBlock = new BlockVoid(new Position(x - 1, y - 1));
        blocks.add(voidBlock);
        int tot = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                blocks.get(tot).setCurrentPos(new Position(i, j));
                tot++;
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
        blocks.stream().sorted((o1, o2) -> {
            if (o1.getCurrentPos().equals(o2.getCurrentPos())) {
                return 0;
            }
            else {
                return o1.getCurrentPos().isSup(o2.getCurrentPos()) ? 1 : -1;
            }
        }).forEach((i) -> {
            if (ref.lastNbLine != ref.lineNb) {
                sb.append(makeNumberDoubleDigit(ref.lineNb)).append("|");
                ref.lastNbLine = ref.lineNb;
            }
            int nb = i.getNumber();
            sb.append(" ").append(makeNumberDoubleDigit(nb));
            ref.lineF++;
            if (ref.lineF + 1 > maxY) {
                sb.append(" \n");
                ref.lineF = 0;
                ref.lineNb++;
            }

        });

        return sb.toString();

    }

    private String makeNumberDoubleDigit(int nb) {
        return nb < 10 && nb >= 0 ? "0" + nb : String.valueOf(nb);
    }

    private String getPremiereLigne() {
        char[]        alpha = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
        StringBuilder sb    = new StringBuilder("    ");
        int           t     = maxY;
        for (int i = 0; i < t; i++) {
            sb.append(" ").append(alpha[i]).append(" ");
        }
        sb.append("\n");
        return sb.toString();
    }

    public List<Block> getAroundVoid() {
        List<Position> p = voidBlock.getCurrentPos().getSurrounding();
        return blocks.stream().filter(bCur -> p.stream().anyMatch((val) -> bCur.getCurrentPos().equals(val))).collect(Collectors.toList());
    }
}
