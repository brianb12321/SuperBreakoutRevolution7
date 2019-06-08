
package com.sbr7.core;

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
import com.sbr7.core.objects.Player;

public class Hud implements Disposable {
    private final Stage stage;
    private final Label healthLabel;
    private final Label timeLabel;
    private final OrthographicCamera hudCam;
    private final LabelStyle healthLabelStyle;
    private final LabelStyle timerLabelStyle;
    private final Player player;
    private float totalTime;
    private boolean timeDown;
    private int timeDownCount;
    private int level;
    private LevelStateManager stateManager;
    public Hud(OrthographicCamera c, Player p, LevelStateManager sm, boolean td, int tdc, int l) {
        hudCam = c;
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
        healthLabel.setPosition(8, 8);
        if(timeDown) {
            timeLabel.setPosition((GameDetails.WIDTH / 2) - 64, (GameDetails.HEIGHT / 2) - 16);
        }
        else {
            timeLabel.setPosition((GameDetails.WIDTH / 2) - 32, 8);
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
        sb.setProjectionMatrix(hudCam.combined);
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