
package com.sbr7.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.sbr7.core.GameDetails;
import com.sbr7.core.LevelStateManager;
import com.sbr7.core.ResourceManager;

public class GameOverScreen implements Screen {

    private Texture textTexture;
    private Sprite textSprite;
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final Stage scene;
    private final Label label;
    private final ResourceManager manager;
    private final LevelStateManager stateManager;
    
    public GameOverScreen(SpriteBatch b, OrthographicCamera cam, ResourceManager m, LevelStateManager lm) {
        batch = b;
        manager = m;
        stateManager = lm;
        camera = cam;
        scene = new Stage();
        LabelStyle ls = new LabelStyle();
        ls.font = new BitmapFont();
        label = new Label("Press B to play again.\nPress ESC to quit.", ls);
        scene.addActor(label);
    }
    
    @Override
    public void show() {
        Gdx.input.setInputProcessor(null);
        manager.getPlayingMusic().stop();
        textTexture = new Texture("img/gameOver.png");
        textSprite = new Sprite(textTexture);
        textSprite.setPosition((GameDetails.WIDTH / 2) / 2, (GameDetails.HEIGHT / 2) / 2);
        textSprite.setSize(GameDetails.WIDTH / 2, GameDetails.HEIGHT / 2);
        label.setPosition(textSprite.getX(), textSprite.getY() - 96);
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.begin();
        textSprite.draw(batch);
        batch.end();
        scene.draw();
        if(Gdx.input.isKeyPressed(Keys.B)) {
            ((Game)Gdx.app.getApplicationListener()).setScreen(new TitleScreen(batch, camera, manager, stateManager));
        }
        else if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            Gdx.app.exit();
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