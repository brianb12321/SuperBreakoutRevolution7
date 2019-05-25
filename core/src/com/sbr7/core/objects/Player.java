/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbr7.core.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Disposable;
import com.sbr7.core.Box2dSprite;
import com.sbr7.core.GameDetails;
import com.sbr7.core.ResourceManager;

/**
 *
 * @author gamec
 */
public class Player extends Box2dSprite {
    private Body ballBody;
    private Texture ballTexture;
    private boolean isAttached = true;
    public Player(Body b, ResourceManager manager) {
        super(b);
        Texture paddleTexture = manager.get("paddle");
        ballTexture = manager.get("ball");
        setTexture(paddleTexture);
    }
    @Override
    public void render(SpriteBatch b) {
        b.begin();
        b.draw(texture, getTextureX(), getTextureY());
        if(isAttached) {
            ballBody.setTransform(getX(), getY() + GameDetails.scaleDown(32), ballBody.getAngle());
        }
        b.draw(ballTexture, GameDetails.scaleUp(ballBody.getPosition().x) - ballTexture.getWidth() / 2, GameDetails.scaleUp(ballBody.getPosition().y) - ballTexture.getHeight() / 2);
        b.end();
    }
    @Override
    public void update(float dt) {
        if(ballBody.getPosition().y < -2) {
            ballBody.setLinearVelocity(0, 0);
            ballBody.setType(BodyDef.BodyType.KinematicBody);
            ballBody.setTransform(getX(), getY() + GameDetails.scaleDown(32), ballBody.getAngle());
            isAttached = true;
        }
    }
    public void setBallBody(Body b) {
        ballBody = b;
    }
    public Body getBallBody() {
        return ballBody;
    }
    public void setIsAttached() {
        if(isAttached == true) {
            isAttached = false;
            ballBody.setType(BodyType.DynamicBody);
            getBallBody().applyForceToCenter(new Vector2(0, 1000), true);
        }
    }

    @Override
    public void dispose() {
        texture.dispose();
        ballTexture.dispose();
    }
}