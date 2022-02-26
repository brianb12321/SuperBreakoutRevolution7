
package com.brianb12321.sbr7;

import com.badlogic.gdx.utils.Array;
import java.util.HashMap;

public class LevelStateManager {
    private final HashMap<LevelState, Array<StateRunnable>> handlers;
    private LevelState currentState;
    public LevelStateManager() {
        handlers = new HashMap<LevelState, Array<StateRunnable>>();
    }
    public void addHandler(LevelState state, StateRunnable handler) {
        if(handlers.get(state) == null) {
            handlers.put(state, new Array<StateRunnable>());
        }
        if(!handlers.get(state).contains(handler, true)) {
            handlers.get(state).add(handler);
        }
    }
    public LevelState getCurrentState() {
        return currentState;
    }
    public void transitionState(LevelState state) {
        LevelState temp = currentState;
        if(currentState != state) {
            currentState = state;
            if(handlers.containsKey(state)) {
                Array<StateRunnable> h = handlers.get(state);
                for(StateRunnable r : h) {
                    r.run(temp);
                }
            }
        }
    }
}