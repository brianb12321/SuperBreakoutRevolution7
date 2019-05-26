
package com.sbr7.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class GameOverScreen implements Screen {

    @Override
    public void show() {
        Gdx.app.log("Game", "Game over!!");
        Gdx.app.exit();
    }

    @Override
    public void render(float f) {
        
    }

    @Override
    public void resize(int i, int i1) {
        
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void hide() {
        
    }

    @Override
    public void dispose() {
        
    }
}