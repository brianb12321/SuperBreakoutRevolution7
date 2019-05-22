
package com.sbr7.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sbr7.core.GameDetails;

/**
 *
 * @author gamec
 */
public class TitleScreen implements Screen {
    private Sprite _title;
    private Sprite _backgroundSprite;
    private Texture _titleTexture;
    private Texture _background;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    public TitleScreen(SpriteBatch sb, OrthographicCamera c) {
        batch = sb;
        camera = c;
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
