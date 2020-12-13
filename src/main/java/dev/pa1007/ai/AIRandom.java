package dev.pa1007.ai;

import dev.pa1007.game.Position;
import dev.pa1007.game.Puzzle;
import dev.pa1007.game.draw.BlockVoid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Random AI, used to show the Strategy pattern and test if the code is working
 */
public class AIRandom implements AI {

    /**
     * Make a random choices between the possible move
     *
     * @param pz the puzzle used
     * @return a random possible move
     */
    @Override
    public int faireChoix(Puzzle pz) {
        BlockVoid voi        = pz.getVoidBlock();
        Position  currentPos = voi.getCurrentPos();
        List<Position> collect = currentPos.getSurrounding().stream().filter(position -> AIUtils.inRange(
                position,
                pz
        )).collect(Collectors.toList());
        Position move = collect.get(AIUtils.randBetween(0, collect.size()));
        return AIUtils.calculateResult(currentPos, move);
    }

    /**
     * Not used
     *
     * @param auto a boolean
     */
    @Override
    public void setAuto(boolean auto) {
        //not used
    }
}
