
package com.brianb12321.sbr7.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brianb12321.sbr7.GameDetails;
import com.brianb12321.sbr7.GameScreen;
import com.brianb12321.sbr7.LevelStateManager;
import com.brianb12321.sbr7.ResourceManager;

/**
 *
 * @author gamec
 */
public class TitleScreen extends GameScreen {
    private Sprite _title;
    private Sprite _backgroundSprite;
    private Texture _titleTexture;
    private Texture _background;
    public TitleScreen(GameScreen screen) {
        super(screen);
    }
    public TitleScreen() {
        super();
    }
    @Override
    public void show() {
        resourceManager.switchMusic("titleScreen");
        _titleTexture = new Texture("img/sbr7Title.png");
        _title = new Sprite(_titleTexture);
        _background = new Texture("img/background.jpg");
        _backgroundSprite = new Sprite(_background);
        _title.setSize(GameDetails.WIDTH, GameDetails.HEIGHT - 10);
        _backgroundSprite.setSize(GameDetails.WIDTH, GameDetails.HEIGHT);
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        spriteBatch.begin();
        _backgroundSprite.draw(spriteBatch);
        _title.draw(spriteBatch);
        spriteBatch.end();
        if(Gdx.input.isKeyPressed(Keys.B)) {
            ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelScreen(this, 1));
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
