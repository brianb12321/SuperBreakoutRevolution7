
package com.sbr7.core;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Disposable;
import com.sbr7.core.objects.Player;
import javafx.scene.Scene;

public class Hud implements Disposable {
    private final Stage stage;
    private final Label healthLabel;
    private final Label timeLabel;
    private final OrthographicCamera hudCam;
    private final LabelStyle healthLabelStyle;
    private final LabelStyle timerLabelStyle;
    private final Player player;
    private float totalTime;
    private LevelStateManager stateManager;
    public Hud(OrthographicCamera c, Player p, LevelStateManager sm) {
        hudCam = c;
        stateManager = sm;
        player = p;
        healthLabelStyle = new LabelStyle();
        healthLabelStyle.font = new BitmapFont();
        timerLabelStyle = new LabelStyle();
        timerLabelStyle.font = new BitmapFont();
        healthLabel = new Label("", healthLabelStyle);
        timeLabel = new Label("", timerLabelStyle);
        healthLabel.setPosition(8, 8);
        timeLabel.setPosition((GameDetails.WIDTH / 2) - 32, 8);
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
        healthLabel.setText("Lives: " + player.getNumOfLives());
        //Ten minutes has elapsed.
        if((int)totalTime / 60 == 5 && (int)totalTime % 60 == 0) {
            stateManager.transitionState(LevelState.TIME);
        }
        timeLabel.setText("" + (int)(totalTime) / 60 + ":" + (int)(totalTime) % 60);
        stage.draw();
    }
    public void update(float delta) {
        totalTime += delta;
    }

    @Override
    public void dispose() {
    }
}