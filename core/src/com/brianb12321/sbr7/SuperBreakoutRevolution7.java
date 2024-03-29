package com.brianb12321.sbr7;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Music.OnCompletionListener;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.brianb12321.sbr7.screens.GameOverScreen;
import com.brianb12321.sbr7.screens.LevelScreen;
import com.brianb12321.sbr7.screens.TitleScreen;

public class SuperBreakoutRevolution7 extends Game {

        private SpriteBatch batch;
        private OrthographicCamera camera;
        private ResourceManager manager;
        private LevelStateManager stateManager;
	@Override
	public void create () {
            manager = new ResourceManager();
            //===================
            //Configure level states.
            
            stateManager = new LevelStateManager();
            stateManager.addHandler(LevelState.TIME, new StateRunnable() {
                @Override
                public void run(LevelState previousStates) {
                    manager.getPlayingMusic().stop();
                    Music m = manager.getMusic("time");
                    m.setVolume(GameDetails.VOLUME);
                    m.setOnCompletionListener(new OnCompletionListener() {
                        @Override
                        public void onCompletion(Music music) {
                            stateManager.transitionState(LevelState.TIME_ALMOST_GONE);
                        }
                    });
                    m.play();
                }
                @Override
                public void transitionOut() {
                
                }
            });
            stateManager.addHandler(LevelState.TIME_ALMOST_GONE, new StateRunnable() {
                @Override
                public void run(LevelState previousStates) {
                    manager.getPlayingMusic().stop();
                    Music m = manager.getMusic("timeAlmostGoine");
                    m.setVolume(GameDetails.VOLUME);
                    m.setOnCompletionListener(new OnCompletionListener() {
                        @Override
                        public void onCompletion(Music music) {
                            Gdx.app.log("Game", "Time ran out!!");
                            GameOverScreen gameOverScreen = new GameOverScreen();
                            gameOverScreen.spriteBatch = batch;
                            gameOverScreen.resourceManager = manager;
                            gameOverScreen.camera = camera;
                            gameOverScreen.stateManager = stateManager;
                            ((Game)Gdx.app.getApplicationListener()).setScreen(gameOverScreen);
                        }
                    });
                    m.play();
                }
                @Override
                public void transitionOut() {
                
                }
            });

            manager.addTexture("paddle", "img/paddleNormal.png");
            manager.addTexture("ball", "img/ballNormal.png");
            manager.addTexture("powerUp_speed", "img/powerUps/speedPowerUp.png");
            manager.addLevelFile("raw/level1.tmx");
            manager.addLevelFile("raw/level2.tmx");
            manager.addLevelFile("raw/level3.tmx");
            manager.addLevelFile("raw/level4.tmx");
            manager.addLevelFile("raw/level5.tmx");
            manager.addLevelFile("raw/level6.tmx");
            manager.addLevelFile("raw/level7.tmx");
            manager.addLevelFile("raw/level9.tmx");
            manager.addSfx("hit", "sfx/hit.mp3");
            manager.addSfx("explosion", "sfx/explosion.mp3");
            manager.addMusic("bg1", "music/Kevin_MacLeod___Presenterator-56pTKTfo3Rs.mp3");
            manager.addMusic("bgDangerous", "music/Evening of Chaos.mp3");
            manager.addMusic("time", "music/Movement Proposition.mp3");
            manager.addMusic("timeAlmostGoine", "music/There It Is.mp3");
            manager.addMusic("lives", "music/Final Count.mp3");
            manager.addMusic("CountDown", "music/Exit the Premises.mp3");
            manager.addMusic("titleScreen", "music/Local Forecast - Elevator.mp3");
            batch = new SpriteBatch();
            camera = new OrthographicCamera();
            camera.setToOrtho(false, GameDetails.WIDTH, GameDetails.HEIGHT);
            batch.setProjectionMatrix(camera.combined);
            Music m = manager.getMusic("bg1");
            m.setLooping(true);
            m.setVolume(GameDetails.VOLUME);
            m.play();
            TitleScreen titleScreen = new TitleScreen();
            titleScreen.spriteBatch = batch;
            titleScreen.resourceManager = manager;
            titleScreen.stateManager = stateManager;
            titleScreen.camera = camera;
            super.setScreen(titleScreen);
	}

	@Override
	public void render () {
            super.render();
	}
	
	@Override
	public void dispose () {
            super.dispose();
	}
    @Override
    public void resize(int width, int height) {
	    super.resize(width, height);
	    super.screen.resize(width, height);
    }
    @Override
    public void pause() {
        super.pause();
    }
    @Override
    public void resume() {
        super.resume();
    }
}
