package com.sbr7.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.sbr7.core.screens.LevelScreen;
import com.sbr7.core.screens.TitleScreen;

public class SuperBreakoutRevolution7 extends Game {

        private SpriteBatch batch;
        private OrthographicCamera camera;
        private ResourceManager manager;
	@Override
	public void create () {
            manager = new ResourceManager();
            manager.addTexture("paddle", "img/paddleNormal.png");
            manager.addTexture("ball", "img/ballNormal.png");
            manager.addTexture("powerUp_speed", "img/powerUps/speedPowerup.png");
            manager.addLevelFile("raw/level1.tmx");
            manager.addLevelFile("raw/level2.tmx");
            manager.addLevelFile("raw/level3.tmx");
            manager.addLevelFile("raw/level4.tmx");
            manager.addLevelFile("raw/level5.tmx");
            manager.addSfx("hit", "sfx/hit.mp3");
            manager.addSfx("explosion", "sfx/explosion.mp3");
            manager.addMusic("bg1", "music/Kevin_MacLeod___Presenterator-56pTKTfo3Rs.mp3");
            manager.addMusic("bgDangerous", "music/Evening of Chaos.mp3");
            batch = new SpriteBatch();
            camera = new OrthographicCamera();
            camera.setToOrtho(false, GameDetails.WIDTH, GameDetails.HEIGHT);
            manager.getMusic("bg1").setLooping(true);
            manager.getMusic("bg1").play();
            super.setScreen(new LevelScreen(batch, manager, camera, 1));
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
