
package com.sbr7.core;

public interface StateRunnable {
    public void run(LevelState previousStates);
    public void transitionOut();
}