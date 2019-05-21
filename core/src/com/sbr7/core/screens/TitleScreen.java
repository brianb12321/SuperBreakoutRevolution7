
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
    private Texture _titleTexture;
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
        batch.setProjectionMatrix(camera.combined);
        _title.setSize(GameDetails.WIDTH, GameDetails.HEIGHT - 10);
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.begin();
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
