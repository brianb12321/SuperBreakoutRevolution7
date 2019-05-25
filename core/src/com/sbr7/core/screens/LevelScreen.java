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
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.sbr7.core.CollisionBits;
import com.sbr7.core.GameDetails;
import com.sbr7.core.GameInputProcessor;
import com.sbr7.core.ResourceManager;
import com.sbr7.core.objects.Player;

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
    private Player player;
    private ResourceManager manager;
    public LevelScreen(SpriteBatch sb, String map, ResourceManager m) {
        gameCamera = new OrthographicCamera();
        debugCamera = new OrthographicCamera();
        manager = m;
        //Set gravity to earth.
        Vector2 gravity = new Vector2(0, -9.8f);
        batch = sb;
        mapToLoad = map;
        debugRend = new Box2DDebugRenderer();
        world = new World(gravity, true);
    }
    @Override
    public void show() {
        manager.add("paddle", "img/paddleNormal.png");
        manager.add("ball", "img/ballNormal.png");
        //Do not show the floor tiles.
        gameCamera.setToOrtho(false, GameDetails.WIDTH, GameDetails.HEIGHT);
        debugCamera.setToOrtho(false, GameDetails.scaleDown(GameDetails.WIDTH), GameDetails.scaleDown(GameDetails.HEIGHT));
        map = new TmxMapLoader().load(mapToLoad);
        renderer = new OrthogonalTiledMapRenderer(map);
        //===============================
        //Configure physics
        
        createBlock((TiledMapTileLayer)map.getLayers().get("blocks"), CollisionBits.BLOCK);
        createBlock((TiledMapTileLayer)map.getLayers().get("walls"), CollisionBits.WALL);
        createBlock((TiledMapTileLayer)map.getLayers().get("floor"), CollisionBits.FLOOR);
        createPaddle();
        createBall();
        Gdx.input.setInputProcessor(new GameInputProcessor(player));
    }
    private void createPaddle() {
        BodyDef def = new BodyDef();
        def.type = BodyType.DynamicBody;
        PolygonShape shape = new PolygonShape();
        FixtureDef fixDef = new FixtureDef();
        fixDef.filter.categoryBits = CollisionBits.PADDLE;
        fixDef.friction = 0;
        def.position.set(new Vector2(GameDetails.scaleDown(GameDetails.WIDTH / 2), GameDetails.scaleDown(50)));
        shape.setAsBox(GameDetails.scaleDown(64), GameDetails.scaleDown(16));
        fixDef.shape = shape;
        Body b = world.createBody(def);
        b.getMassData().mass = 10000;
        b.createFixture(fixDef);
        player = new Player(b, manager);
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
        player.update(f);
        //=================
        //Begin rendering

        gameCamera.update();
        renderer.render();
        player.render(batch);
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
        map.dispose();
        batch.dispose();
        player.dispose();
        world.dispose();
        manager.dispose();
    }
    public void createBall() {
        BodyDef def = new BodyDef();
        def.type = BodyType.KinematicBody;
        FixtureDef fixDef = new FixtureDef();
        fixDef.friction = 0;
        fixDef.restitution = 1.0f;
        CircleShape circle = new CircleShape();
        circle.setRadius(GameDetails.scaleDown(16));
        def.position.set(player.getX(), player.getY() + GameDetails.scaleDown(32));
        fixDef.shape = circle;
        fixDef.filter.categoryBits = CollisionBits.BALL;
        fixDef.filter.maskBits = CollisionBits.BLOCK | CollisionBits.WALL | CollisionBits.PADDLE;
        Body b = world.createBody(def);
        b.createFixture(fixDef);
        player.setBallBody(b);
    }
    public void createBlock(TiledMapTileLayer layer, byte bits) {
        float tileSize = layer.getTileWidth();
        BodyDef def = new BodyDef();
        def.type = BodyType.StaticBody;
        FixtureDef fixDef = new FixtureDef();
        fixDef.filter.categoryBits = bits;
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