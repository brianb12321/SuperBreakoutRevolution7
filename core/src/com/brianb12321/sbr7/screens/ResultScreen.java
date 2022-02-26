
package com.brianb12321.sbr7.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brianb12321.sbr7.LevelStateManager;
import com.brianb12321.sbr7.ResourceManager;

public class ResultScreen implements Screen {

    private int currentNumber;
    private ResourceManager manager;
    private OrthographicCamera cam;
    private SpriteBatch batch;
    private LevelStateManager stateManager;
    public ResultScreen(int num, ResourceManager m, OrthographicCamera c, SpriteBatch b, LevelStateManager lm) {
        currentNumber = num;
        stateManager = lm;
        manager = m;
        cam = c;
        batch = b;
    }
    @Override
    public void show() {
        if(manager.hasLevelNumber(currentNumber + 1)) {
            ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelScreen(batch, manager, cam, currentNumber + 1, stateManager));
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