
package com.brianb12321.sbr7.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.brianb12321.sbr7.*;
import com.brianb12321.sbr7.objects.Player;

public class LevelScreen extends GameScreen {

    private MapRenderer renderer;
    private TiledMap map;
    private final String mapToLoad;
    private final World world;
    private boolean timeDown = false;
    private int timeDownCount = 30;
    private Box2DDebugRenderer debugRend;
    private OrthographicCamera debugCamera;
    private int maxSpeed = 63;
    private OrthographicCamera hudCamera;
    private Hud hud;
    private Player player;
    private Array<Block> blocks;
    private BlockCollisionDetector cd;
    private String musicName;
    private int levelNumber;
    public LevelScreen(GameScreen parent, int ln) {
        super(parent);
        debugCamera = new OrthographicCamera();
        hudCamera = new OrthographicCamera();
        blocks = new Array<Block>();
        levelNumber = ln;
        //Set gravity to earth.
        Vector2 gravity = new Vector2(0, -9.8f);
        mapToLoad = resourceManager.getLevelFile(levelNumber);
        debugRend = new Box2DDebugRenderer();
        world = new World(gravity, true);
    }

    @Override
    public void show() {
        stateManager.addHandler(LevelState.NORMAL, new StateRunnable() {
            @Override
            public void run(LevelState previousStates) {
                resourceManager.getPlayingMusic().stop();
                if(musicName != null && !musicName.equals("")) {
                    resourceManager.getMusic(musicName).play();
                }
            }
            @Override
            public void transitionOut() {
                
            }
        });
        stateManager.transitionState(LevelState.NORMAL);
        hudCamera.setToOrtho(false, GameDetails.WIDTH, GameDetails.HEIGHT);
        debugCamera.setToOrtho(false, GameDetails.scaleDown(GameDetails.WIDTH), GameDetails.scaleDown(GameDetails.HEIGHT));
        map = new TmxMapLoader().load(Gdx.files.internal(mapToLoad).path());
        configureMusic();
        configureSettings();
        renderer = new OrthogonalTiledMapRenderer(map);
        renderer.setView(camera);
        spriteBatch.setProjectionMatrix(camera.combined);
        //===============================
        //Configure physics
        
        createBlock((TiledMapTileLayer)map.getLayers().get("blocks"), CollisionBits.BLOCK, false);
        createBlock((TiledMapTileLayer)map.getLayers().get("walls"), CollisionBits.WALL, false);
        createFloor();
        createPaddle();
        cd = new BlockCollisionDetector(resourceManager, player, blocks);
        hud = new Hud(hudCamera, player, stateManager, timeDown, timeDownCount, levelNumber);
        world.setContactListener(cd);
        createBall();
        Gdx.input.setInputProcessor(new GameInputProcessor(player, resourceManager, stateManager));
    }
    private void configureSettings() {
        if(map.getProperties().containsKey("LevelType")) {
            if(map.getProperties().get("LevelType").toString().equals("CountDown")) {
                timeDown = true;
                if(map.getProperties().containsKey("CountDownTime")) {
                    timeDownCount = (Integer)map.getProperties().get("CountDownTime");
                }
            }
        }
    }
    private void configureMusic() {
        if(map.getProperties().containsKey("LevelType")) {
            String type = map.getProperties().get("LevelType").toString();
            if(type.equals("Dangerous")) {
                musicName = "bgDangerous";
            }
            else if(type.equals("CountDown")) {
                musicName = "CountDown";
            }
            else {
                musicName = "bg1";
            }
        }
        else {
            musicName = "bg1";
        }
        resourceManager.getPlayingMusic().stop();
        Music m = resourceManager.getMusic(musicName);
        m.setLooping(true);
        m.setVolume(GameDetails.VOLUME);
        m.play();
    }
    
    private void createPaddle() {
        BodyDef def = new BodyDef();
        def.type = BodyType.DynamicBody;
        Vector2 bodyVec = new Vector2(GameDetails.scaleDown(GameDetails.WIDTH / 2), GameDetails.scaleDown(1));
        def.position.set(bodyVec);
        PolygonShape shape1 = new PolygonShape();
//        PolygonShape shape2 = new PolygonShape();
//        PolygonShape shape3 = new PolygonShape();
        FixtureDef fixDef1 = new FixtureDef();
//        FixtureDef fixDef2 = new FixtureDef();
//        FixtureDef fixDef3 = new FixtureDef();
        //=============
        //First fixture
        
        fixDef1.filter.categoryBits = CollisionBits.PADDLE;
        fixDef1.friction = 0;
        //fixDef1.density = 10.0f;
        //fixDef1.restitution = 0.1f;
        shape1.setAsBox(GameDetails.scaleDown(64), GameDetails.scaleDown(16));
        fixDef1.shape = shape1;
        //================
        //Second Fixture
        
//        fixDef2.filter.categoryBits = CollisionBits.PADDLE;
//        fixDef2.friction = 0;
//        shape2.setAsBox(GameDetails.scaleDown(16), GameDetails.scaleDown(16), new Vector2(16, 0), 0);
//        fixDef2.shape = shape1;
        //===================
        //Thrid Fixture
        
//        fixDef3.filter.categoryBits = CollisionBits.PADDLE;
//        fixDef3.friction = 0;
//        shape3.setAsBox(GameDetails.scaleDown(32), GameDetails.scaleDown(16), new Vector2(32, 0), 0);
//        fixDef3.shape = shape1;
        Body b = world.createBody(def);
        b.getMassData().mass = 1000f;
        b.createFixture(fixDef1);
//        b.createFixture(fixDef2);
//        b.createFixture(fixDef3);
        player = new Player(b, resourceManager);
    }
    private void createFloor() {
        BodyDef def = new BodyDef();
        def.type = BodyType.StaticBody;
        ChainShape shape = new ChainShape();
        FixtureDef fixDef = new FixtureDef();
        fixDef.filter.categoryBits = CollisionBits.PADDLE;
        fixDef.friction = 0;
        def.position.set(new Vector2(GameDetails.scaleDown(0), GameDetails.scaleDown(0)));
        shape.createChain(new Vector2[] {
            new Vector2(GameDetails.scaleDown(0), GameDetails.scaleDown(0)),
            new Vector2(GameDetails.scaleDown(GameDetails.WIDTH), GameDetails.scaleDown(0))
        });
        fixDef.shape = shape;
        fixDef.filter.categoryBits = CollisionBits.FLOOR;
        Body b = world.createBody(def);
        b.getMassData().mass = 10000;
        b.createFixture(fixDef);
    }
    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //==================
        //Configure cameras

        //Some arbitrary values. Doesn't really matter right now.
        world.step(f, 6, 3);
        limitSpeed();
        removeBlocks();
        checkWinCondition();
        player.update(f);
        hud.update(f);
        //=================
        //Begin rendering

        camera.update();
        renderer.render();
        player.render(spriteBatch);
        hud.render(spriteBatch);
        //debugRend.render(world, debugCamera.combined);
    }

    @Override
    public void resize(int width, int height) {

    }

    private void limitSpeed() {
        Body ball = player.getBallBody();
        Vector2 velocity = ball.getLinearVelocity();
        float speed = velocity.len();
        //Reduce speed
        if(speed < maxSpeed) {
           ball.setLinearDamping(0.0f);
        }
        else if(speed > maxSpeed) {
            ball.setLinearDamping(0.5f);
        }
    }
    private void checkWinCondition() {
        //If true, we won!!!
        if(blocks.size == 0) {
            ((Game)Gdx.app.getApplicationListener()).setScreen(new ResultScreen(this, levelNumber));
        }
        else if(player.getNumOfLives() <= 0) {
            ((Game)Gdx.app.getApplicationListener()).setScreen(new GameOverScreen(this));
        }
    }
    private void removeBlocks() {
        Array<Block> buffer = cd.getBuffer();
        if(buffer != null) {
            for(Block b : buffer) {
                b.hit();
                if(b.getDPTolerance() <= 0) {
                    b.getCell().setTile(null);
                    world.destroyBody(b.getBody());
                    blocks.removeValue(b, true);
                }
            }
            buffer.clear();
        }
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
        spriteBatch.dispose();
        player.dispose();
        hud.dispose();
        world.dispose();
        resourceManager.dispose();
    }
    public void createBall() {
        BodyDef def = new BodyDef();
        def.type = BodyType.KinematicBody;
        FixtureDef fixDef = new FixtureDef();
        fixDef.friction = 0;
        fixDef.restitution = 1f;
        fixDef.density = 1.0f;
        CircleShape circle = new CircleShape();
        circle.setRadius(GameDetails.scaleDown(16));
        def.position.set(player.getX(), player.getY() + GameDetails.scaleDown(32));
        fixDef.shape = circle;
        fixDef.filter.categoryBits = CollisionBits.BALL;
        fixDef.filter.maskBits = CollisionBits.BLOCK | CollisionBits.WALL | CollisionBits.PADDLE;
        Body b = world.createBody(def);
        b.createFixture(fixDef);
        b.getMassData().mass = 5000;
        player.setBallBody(b);
    }
    public void createBlock(TiledMapTileLayer layer, byte bits, boolean isDense) {
        float tileSize = layer.getTileWidth();
        BodyDef def = new BodyDef();
        def.type = BodyType.StaticBody;
        FixtureDef fixDef = new FixtureDef();
        fixDef.filter.categoryBits = bits;
        fixDef.friction = 0.0f;
        if(isDense) {
            fixDef.density = 10.0f;
            fixDef.restitution = 0.1f;
        }
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
                PolygonShape box = new PolygonShape();
                //Copied code. Binds three vertices to a tile.
                Vector2[] verticies = {
                    new Vector2(GameDetails.scaleDown(-tileSize / 2), GameDetails.scaleDown(-tileSize / 2)),
                    new Vector2(GameDetails.scaleDown(-tileSize / 2), GameDetails.scaleDown(tileSize / 2)),
                    new Vector2(GameDetails.scaleDown(tileSize / 2), GameDetails.scaleDown(tileSize / 2)),
                    new Vector2(GameDetails.scaleDown(tileSize / 2), GameDetails.scaleDown(-tileSize / 2))
                };
                polygon.createChain(verticies);
                box.setAsBox(GameDetails.scaleDown(tileSize / 2), GameDetails.scaleDown(tileSize / 2));
                fixDef.shape = box;
                Body b = world.createBody(def);
                Fixture f = b.createFixture(fixDef);
                Block block = new Block(cell, b, bits);
                f.setUserData(block);
                String type = cell.getTile().getProperties().containsKey("BlockType") ? cell.getTile().getProperties().get("BlockType").toString() : "";
                if(bits == CollisionBits.BLOCK &&
                        !type.equals(Block.BLOCK_DEATH) &&
                        !type.equals(Block.BLOCK_STONE) &&
                        !type.equals(Block.BLOCK_SEMI_DEATH) &&
                        !type.equals(Block.BLOCK_EXPLOSION)) {
                    blocks.add(block);
                }
            }
        }
    }
}