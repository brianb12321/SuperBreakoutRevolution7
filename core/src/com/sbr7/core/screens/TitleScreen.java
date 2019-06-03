
package com.sbr7.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sbr7.core.GameDetails;
import com.sbr7.core.LevelStateManager;
import com.sbr7.core.ResourceManager;

/**
 *
 * @author gamec
 */
public class TitleScreen implements Screen {
    private Sprite _title;
    private Sprite _backgroundSprite;
    private Texture _titleTexture;
    private Texture _background;
    private ResourceManager manager;
    private LevelStateManager stateManager;
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    public TitleScreen(SpriteBatch sb, OrthographicCamera c, ResourceManager m, LevelStateManager lm) {
        batch = sb;
        camera = c;
        manager = m;
        stateManager = lm;
    }
    @Override
    public void show() {
        _titleTexture = new Texture("img/sbr7Title.png");
        _title = new Sprite(_titleTexture);
        _background = new Texture("img/background.jpg");
        _backgroundSprite = new Sprite(_background);
        batch.setProjectionMatrix(camera.combined);
        _title.setSize(GameDetails.WIDTH, GameDetails.HEIGHT - 10);
        _backgroundSprite.setSize(GameDetails.WIDTH, GameDetails.HEIGHT);
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.begin();
        _backgroundSprite.draw(batch);
        _title.draw(batch);
        batch.end();
        if(Gdx.input.isKeyPressed(Keys.B)) {
            ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelScreen(batch, manager, camera, 1, stateManager));
        }
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
