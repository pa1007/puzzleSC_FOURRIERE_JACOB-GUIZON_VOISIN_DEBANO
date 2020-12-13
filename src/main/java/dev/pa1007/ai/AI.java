package dev.pa1007.ai;

import dev.pa1007.game.Puzzle;

public interface AI {

    int faireChoix(Puzzle pz);

    void setAuto(boolean auto);
}
