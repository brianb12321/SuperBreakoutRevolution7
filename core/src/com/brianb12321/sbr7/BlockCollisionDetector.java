
package com.brianb12321.sbr7;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;
import com.brianb12321.sbr7.objects.Player;

public class BlockCollisionDetector implements ContactListener {

    private Array<Block> blocksToRemove;
    private Array<Block> blocks;
    private ResourceManager manager;
    private Player player;
    @Override
    public void beginContact(Contact cntct) {
        
    }

    public BlockCollisionDetector(ResourceManager m, Player p, Array<Block> b) {
        blocksToRemove = new Array<Block>();
        blocks = b;
        manager = m;
        player = p;
    }
    @Override
    public void endContact(Contact cntct) {
        Fixture fa = cntct.getFixtureA();
        Fixture fb = cntct.getFixtureB();
        Block b = null;
        if(fa != null && fa.getUserData() instanceof Block && (fb != null && fb.getFilterData().categoryBits == CollisionBits.BALL)) {
            b = (Block)fa.getUserData();
        }
        else if(fb != null && fb.getUserData() instanceof Block && (fa != null && fa.getFilterData().categoryBits == CollisionBits.BALL)) {
            b = (Block)fb.getUserData();
        }
        if(b != null) {
            Body ball = player.getBallBody();
            boolean remove = true;
            if(b.getMask() == CollisionBits.BLOCK) {
                //Do special stuff for special blocks.
                if(b.getCell() != null && b.getCell().getTile() != null && b.getCell().getTile().getProperties().containsKey("BlockType")) {
                    String type = b.getCell().getTile().getProperties().get("BlockType").toString();
                    if(type.equals(Block.BLOCK_DEATH)) {
                        player.setHealth(0);
                    }
                    else if(type.equals(Block.BLOCK_STONE)) {
                        remove = false;
                    }
                    else if(type.equals(Block.BLOCK_SEMI_DEATH)) {
                        int hp = (b.getCell().getTile().getProperties().containsKey("PlayerHP")) ? (Integer)b.getCell().getTile().getProperties().get("PlayerHP") : 90000;
                        player.setHealth(hp);
                    }
                    else if(type.equals(Block.BLOCK_EXPLOSION)) {
                        manager.playSfx("explosion");
                        int max = (Integer)b.getCell().getTile().getProperties().get("NumOfAffectedBlocks");
                        for(int i = 0; i < max; i++) {
                            Block affectedBlock = blocks.get((int)(Math.random() * blocks.size));
                            blocksToRemove.add(affectedBlock);
                        }
                    }
                }
                if(remove) blocksToRemove.add(b);
                manager.playSfx("hit");
            }
        }
    }

    public Array<Block> getBuffer() {
        return blocksToRemove;
    }
    @Override
    public void preSolve(Contact cntct, Manifold mnfld) {
    }

    @Override
    public void postSolve(Contact cntct, ContactImpulse ci) {
        
    }
    
}
