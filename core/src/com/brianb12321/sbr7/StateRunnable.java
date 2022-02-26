
package com.brianb12321.sbr7;

public interface StateRunnable {
    public void run(LevelState previousStates);
    public void transitionOut();
}