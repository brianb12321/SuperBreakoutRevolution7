/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbr7.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.sbr7.core.objects.Player;

/**
 *
 * @author gamec
 */
public class GameInputProcessor implements InputProcessor {

    private final Player player;
    private final float speed = GameDetails.scaleDown(700);
    private final float turboSpeed = GameDetails.scaleDown(12000);
    public GameInputProcessor(Player p) {
        player = p;
    }
    @Override
    public boolean keyDown(int i) {
        //Gdx.app.log("Paddle X", new Float(player.getTextureX()).toString());
        //Gdx.app.log("Paddle X - Box2d", new Float(player.getX()).toString());
        if(i == Keys.A || i == Keys.LEFT) {
            player.getBody().setLinearVelocity(-speed, 0);
        }
        if(i == Keys.D || i == Keys.RIGHT) {
            player.getBody().setLinearVelocity(speed, 0);
        }
        if(i == Keys.Q) {
            player.getBody().setLinearVelocity(-turboSpeed, 0);
        }
        if(i == Keys.T) {
            player.getBody().setLinearVelocity(turboSpeed, 0);
        }
        if(i == Keys.ESCAPE) {
            Gdx.app.exit();
        }
        return true;
    }

    @Override
    public boolean keyUp(int i) {
        if(i == Keys.SPACE) {
            player.setIsAttached();
        }
        player.getBody().setLinearVelocity(0, 0);
        return true;
    }

    @Override
    public boolean keyTyped(char c) {
        return true;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return true;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return true;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return true;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return true;
    }

    @Override
    public boolean scrolled(int i) {
        return true;
    }
}
