package dev.pa1007.ai;

import dev.pa1007.game.Position;
import dev.pa1007.game.Puzzle;
import dev.pa1007.game.draw.BlockVoid;
import java.util.List;
import java.util.stream.Collectors;

public class AIRandom implements AI {

    @Override
    public int faireChoix(Puzzle pz) {
        BlockVoid voi        = pz.getVoidBlock();
        Position  currentPos = voi.getCurrentPos();
        List<Position> collect = currentPos.getSurrounding().stream().filter(position -> AIUtils.inRange(
                position,
                pz
        )).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(currentPos);
        Position move = collect.get(AIUtils.randBetween(0, collect.size()));
        System.out.println(move);
        return AIUtils.calculateResult(currentPos, move);
    }

    @Override
    public void setAuto(boolean auto) {
        //not used
    }
}
