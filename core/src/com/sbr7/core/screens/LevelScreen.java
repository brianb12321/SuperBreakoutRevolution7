/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbr7.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.sbr7.core.GameDetails;

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
    private final World world;
    private Box2DDebugRenderer debugRend;
    public LevelScreen(SpriteBatch sb, OrthographicCamera c, String map, World w) {
        batch = sb;
        camera = c;
        mapToLoad = map;
        world = w;
        debugRend = new Box2DDebugRenderer();
    }
    @Override
    public void show() {
        map = new TmxMapLoader().load(mapToLoad);
        renderer = new OrthogonalTiledMapRenderer(map);
        renderer.setView(camera);
        //===============================
        //Configure physics
        
        
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        renderer.render();
        debugRend.render(world, camera.combined);
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
    public Body createBlock(int x, int y) {
        BodyDef def = new BodyDef();
        FixtureDef fixDef = new FixtureDef();
        CircleShape circle = new CircleShape();
        circle.setRadius(GameDetails.PPM / 2);
        circle.setPosition(new Vector2(x / 2, y / 2));
        def.type = BodyType.DynamicBody;
        Body body = world.createBody(def);
        fixDef.shape = circle;
        fixDef.restitution = 1.0f;
        Fixture fixture = body.createFixture(fixDef);
        return body;
    }
}
