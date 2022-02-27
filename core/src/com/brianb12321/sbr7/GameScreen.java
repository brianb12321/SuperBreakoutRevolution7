package com.brianb12321.sbr7;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class GameScreen implements Screen {
    protected OrthographicCamera camera;
    protected SpriteBatch spriteBatch;
    protected ResourceManager resourceManager;
    protected LevelStateManager stateManager;

    protected GameScreen()
    {

    }
    protected GameScreen(GameScreen parent) {
        this.camera = parent.camera;
        this.spriteBatch = parent.spriteBatch;
        this.resourceManager = parent.resourceManager;
        this.stateManager = parent.stateManager;
    }
}
