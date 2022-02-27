
package com.brianb12321.sbr7.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brianb12321.sbr7.GameScreen;
import com.brianb12321.sbr7.LevelStateManager;
import com.brianb12321.sbr7.ResourceManager;

public class ResultScreen extends GameScreen {

    private int currentNumber;
    public ResultScreen(GameScreen parent, int num) {
        super(parent);
        currentNumber = num;
    }
    @Override
    public void show() {
        if(resourceManager.hasLevelNumber(currentNumber + 1)) {
            ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelScreen(this, currentNumber + 1));
        }
        else {
            Gdx.app.log("Game", "Out of levels.");
            Gdx.app.exit();
        }
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