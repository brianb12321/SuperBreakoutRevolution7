package com.sbr7.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sbr7.core.screens.LevelScreen;
import com.sbr7.core.screens.TitleScreen;

public class SuperBreakoutRevolution7 extends Game {

        private SpriteBatch batch;
        private OrthographicCamera camera;
	@Override
	public void create () {
            batch = new SpriteBatch();
            camera = new OrthographicCamera();
            camera.setToOrtho(false, GameDetails.WIDTH, GameDetails.HEIGHT);
            super.setScreen(new LevelScreen(batch, camera, "raw/level1.tmx"));
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
