
package com.brianb12321.sbr7;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.brianb12321.sbr7.objects.Player;

public class Hud implements Disposable {
    private final Stage stage;
    private final Label healthLabel;
    private final Label timeLabel;
    private final OrthographicCamera hudCamera;
    private final LabelStyle healthLabelStyle;
    private final LabelStyle timerLabelStyle;
    private final Player player;
    private float totalTime;
    private boolean timeDown;
    private int timeDownCount;
    private int level;
    private LevelStateManager stateManager;
    public Hud(OrthographicCamera camera, Player p, LevelStateManager sm, boolean td, int tdc, int l) {
        hudCamera = camera;
        level = l;
        stateManager = sm;
        player = p;
        timeDown = td;
        timeDownCount = tdc;
        healthLabelStyle = new LabelStyle();
        healthLabelStyle.font = new BitmapFont();
        timerLabelStyle = new LabelStyle();
        timerLabelStyle.font = new BitmapFont();
        if(timeDown) {
            totalTime = tdc;
        }
        healthLabel = new Label("", healthLabelStyle);
        timeLabel = new Label("", timerLabelStyle);
        healthLabel.setPosition(8, 10);
        timeLabel.setPosition(Gdx.graphics.getWidth() - 32, 10);
        if(timeDown) {
            timeLabel.setColor(Color.YELLOW);
        }
        stage = new Stage();
        stage.addActor(healthLabel);
        stage.addActor(timeLabel);
    }
    public void render(SpriteBatch sb) {
        stateManager.addHandler(LevelState.TIME, new StateRunnable() {
            @Override
            public void run(LevelState previousStates) {
                timeLabel.setColor(Color.RED);
            }
            @Override
            public void transitionOut() {
                timeLabel.setColor(Color.WHITE);
            }
        });
        sb.setProjectionMatrix(hudCamera.combined);
        healthLabel.setText("Lives: " + player.getNumOfLives() + ", level: " + level);
        //Ten minutes has elapsed.
        if(!timeDown) {
            if((int)totalTime / 60 == 4 && (int)totalTime % 60 == 0) {
                stateManager.transitionState(LevelState.TIME);
            }
        }
        else {
            if((int)totalTime / 60 == 0 && (int)totalTime % 60 == 0) {
                Gdx.app.exit();
            }
        }
        timeLabel.setText("" + (int)(totalTime) / 60 + ":" + (int)(totalTime) % 60);
        stage.draw();
    }

    public void update(float delta) {
        if(!timeDown) {
            totalTime += delta;
        }
        else {
            totalTime -= delta;
        }
    }

    @Override
    public void dispose() {
    }
}