
package com.brianb12321.sbr7.screens;

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
import com.brianb12321.sbr7.GameDetails;
import com.brianb12321.sbr7.GameScreen;
import com.brianb12321.sbr7.LevelStateManager;
import com.brianb12321.sbr7.ResourceManager;

public class GameOverScreen extends GameScreen {

    private Texture textTexture;
    private Sprite textSprite;
    private Stage scene;
    private Label label;
    
    public GameOverScreen(GameScreen parent) {
        super(parent);
        scene = new Stage();
        LabelStyle ls = new LabelStyle();
        ls.font = new BitmapFont();
        label = new Label("Press B to play again.\nPress ESC to quit.", ls);
        scene.addActor(label);
    }
    public GameOverScreen() {
        super();
    }
    
    @Override
    public void show() {
        Gdx.input.setInputProcessor(null);
        resourceManager.getPlayingMusic().stop();
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
        spriteBatch.begin();
        textSprite.draw(spriteBatch);
        spriteBatch.end();
        scene.draw();
        if(Gdx.input.isKeyPressed(Keys.B)) {
            ((Game)Gdx.app.getApplicationListener()).setScreen(new TitleScreen(this));
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