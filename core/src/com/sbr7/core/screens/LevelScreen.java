/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbr7.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.sbr7.core.GameDetails;
import com.sbr7.core.ResourceManager;

/**
 *
 * @author gamec
 */
public class LevelScreen implements Screen {

    private final SpriteBatch batch;
    private MapRenderer renderer;
    private TiledMap map;
    private final String mapToLoad;
    private final World world;
    private Box2DDebugRenderer debugRend;
    private OrthographicCamera gameCamera;
    private OrthographicCamera debugCamera;
    private ResourceManager manager;
    private Sprite paddleSprite;
    public LevelScreen(SpriteBatch sb, String map, ResourceManager m) {
        gameCamera = new OrthographicCamera();
        debugCamera = new OrthographicCamera();
        manager = m;
        //Set gravity to earth.
        Vector2 gravity = new Vector2(0, -9.8f);
        batch = sb;
        mapToLoad = map;
        debugRend = new Box2DDebugRenderer();
        world = new World(gravity, false);
    }
    @Override
    public void show() {
        manager.add("paddle", "img/paddleNormal.png");
        paddleSprite = new Sprite(manager.get("paddle"));
        paddleSprite.setSize(128, 32);
        //Center the paddle.
        paddleSprite.setPosition(GameDetails.WIDTH / 2 - paddleSprite.getWidth(), 0);
        gameCamera.setToOrtho(false, GameDetails.WIDTH, GameDetails.HEIGHT);
        debugCamera.setToOrtho(false, GameDetails.scaleDown(GameDetails.WIDTH), GameDetails.scaleDown(GameDetails.HEIGHT));
        map = new TmxMapLoader().load(mapToLoad);
        renderer = new OrthogonalTiledMapRenderer(map);
        //===============================
        //Configure physics
        createBlock((TiledMapTileLayer)map.getLayers().get("blocks"));
        //createBall();
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //==================
        //Configure cameras
        
        renderer.setView(gameCamera);
        batch.setProjectionMatrix(gameCamera.combined);
        
        //Some arbitrary values. Doesn't really matter right now.
        world.step(f, 6, 2);
        //=================
        //Begin rendering
        
        
        gameCamera.update();
        renderer.render();
        batch.begin();
        paddleSprite.draw(batch);
        batch.end();
        debugRend.render(world, debugCamera.combined);
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
        world.dispose();
    }
    public void createBall() {
        BodyDef def = new BodyDef();
        def.type = BodyType.DynamicBody;
        FixtureDef fixDef = new FixtureDef();
        CircleShape circle = new CircleShape();
        circle.setRadius(2);
        def.position.set(GameDetails.scaleDown(25), 0);
        fixDef.shape = circle;
        world.createBody(def).createFixture(fixDef);
    }
    public void createBlock(TiledMapTileLayer layer) {
        float tileSize = layer.getTileWidth();
        BodyDef def = new BodyDef();
        def.type = BodyType.StaticBody;
        FixtureDef fixDef = new FixtureDef();
        //r = y
        for(int r = 0; r < layer.getHeight(); r++) {
            //c = x
            for(int c = 0; c < layer.getWidth(); c++) {
                Cell cell = layer.getCell(c, r);
                if(cell == null) continue;
                if(cell.getTile() == null) continue;
                //Can't use helper method to convert pixels to meters.
                def.position.set((c + 0.5f) * tileSize / GameDetails.PPM, (r + 0.5f) * tileSize / GameDetails.PPM);
                ChainShape polygon = new ChainShape();
                //Copied code. Binds three vertices to a tile.
                Vector2[] verticies = {
                    new Vector2(GameDetails.scaleDown(-tileSize / 2), GameDetails.scaleDown(-tileSize / 2)),
                    new Vector2(GameDetails.scaleDown(-tileSize / 2), GameDetails.scaleDown(tileSize / 2)),
                    new Vector2(GameDetails.scaleDown(tileSize / 2), GameDetails.scaleDown(tileSize / 2)),
                };
                polygon.createChain(verticies);
                fixDef.shape = polygon;
                world.createBody(def).createFixture(fixDef);
            }
        }
    }
}