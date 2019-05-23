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
        private World world;
	@Override
	public void create () {
            //Set gravity to earth.
            Vector2 gravity = new Vector2(0, -9.8f);
            world = new World(gravity, false);
            batch = new SpriteBatch();
            camera = new OrthographicCamera();
            camera.setToOrtho(false, GameDetails.WIDTH, GameDetails.HEIGHT);
            super.setScreen(new LevelScreen(batch, camera, "raw/level1.tmx", world));
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
