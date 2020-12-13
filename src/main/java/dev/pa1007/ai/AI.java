package dev.pa1007.ai;

import dev.pa1007.game.Puzzle;

public interface AI {

    /**
     * This is where the decision is taken, can take some times
     *
     * @param pz the puzzle used
     * @return a number,  1 is on top,2 is on the right,3 is besides 4 is on the left, 0 are the same,5 anything,6 if not possible
     */
    int faireChoix(Puzzle pz);

    /**
     * for caching purposes, can be not used by classes
     *
     * @param auto a boolean
     */
    void setAuto(boolean auto);
}
