
package com.sbr7.core;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.physics.box2d.Body;

public class Block {
    public static final String BLOCK_DEATH = "Death";
    public static final String BLOCK_STONE = "Stone";
    public static final String BLOCK_SEMI_DEATH = "SemiDeath";
    public static final String BLOCK_EXPLOSION = "Explosion";
    private final Cell tile;
    private final Body blockBody;
    private final byte mask;
    private int dpTolerance = 1;
    public Block(Cell t, Body b, byte m) {
        tile = t;
        blockBody = b;
        mask = m;
        if(t.getTile().getProperties().containsKey("DPTolerance")) {
            dpTolerance = (Integer)t.getTile().getProperties().get("DPTolerance");    
        }
    }
    public void hit() {
        dpTolerance--;
    }
    public int getDPTolerance() {
        return dpTolerance;
    }
    public Cell getCell() {
        return tile;
    }
    public Body getBody() {
        return blockBody;
    }
    public byte getMask() {
        return mask;
    }
    public float getX() {
        return blockBody.getPosition().x;
    }
    public float getY() {
        return blockBody.getPosition().y;
    }
}