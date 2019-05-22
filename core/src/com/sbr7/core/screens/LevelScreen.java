/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbr7.core.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 *
 * @author gamec
 */
public class LevelScreen implements Screen {

    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private MapRenderer renderer;
    private TiledMap map;
    private final String mapToLoad;
    public LevelScreen(SpriteBatch sb, OrthographicCamera c, String map) {
        batch = sb;
        camera = c;
        mapToLoad = map;
    }
    @Override
    public void show() {
        map = new TmxMapLoader().load(mapToLoad);
        renderer = new OrthogonalTiledMapRenderer(map);
        renderer.setView(camera);
    }

    @Override
    public void render(float f) {
        renderer.render();
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
