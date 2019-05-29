package com.sbr7.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Music.OnCompletionListener;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sbr7.core.screens.GameOverScreen;
import com.sbr7.core.screens.LevelScreen;
import com.sbr7.core.screens.TitleScreen;

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
                            ((Game)Gdx.app.getApplicationListener()).setScreen(new GameOverScreen());
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
            manager.addTexture("powerUp_speed", "img/powerUps/speedPowerup.png");
            manager.addLevelFile("raw/level1.tmx");
            manager.addLevelFile("raw/level2.tmx");
            manager.addLevelFile("raw/level3.tmx");
            manager.addLevelFile("raw/level4.tmx");
            manager.addLevelFile("raw/level5.tmx");
            manager.addLevelFile("raw/level6.tmx");
            manager.addLevelFile("raw/level7.tmx");
            manager.addSfx("hit", "sfx/hit.mp3");
            manager.addSfx("explosion", "sfx/explosion.mp3");
            manager.addMusic("bg1", "music/Kevin_MacLeod___Presenterator-56pTKTfo3Rs.mp3");
            manager.addMusic("bgDangerous", "music/Evening of Chaos.mp3");
            manager.addMusic("time", "music/Movement Proposition.mp3");
            manager.addMusic("timeAlmostGoine", "music/There It Is.mp3");
            manager.addMusic("lives", "music/Final Count.mp3");
            manager.addMusic("CountDown", "music/Exit the Premises.mp3");
            batch = new SpriteBatch();
            camera = new OrthographicCamera();
            camera.setToOrtho(false, GameDetails.WIDTH, GameDetails.HEIGHT);
            Music m = manager.getMusic("bg1");
            m.setLooping(true);
            m.setVolume(GameDetails.VOLUME);
            m.play();
            super.setScreen(new LevelScreen(batch, manager, camera, 1, stateManager));
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
