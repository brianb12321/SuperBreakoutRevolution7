/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brianb12321.sbr7.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Timer;
import com.brianb12321.sbr7.Box2dSprite;
import com.brianb12321.sbr7.GameDetails;
import com.brianb12321.sbr7.ResourceManager;

/**
 *
 * @author gamec
 */
public class Player extends Box2dSprite {
    private Body ballBody;
    private Texture ballTexture;
    private boolean isAttached = true;
    private int lives = GameDetails.STARTING_LIVES;
    public Player(Body b, ResourceManager manager) {
        super(b);
        Texture paddleTexture = manager.getTexture("paddle");
        ballTexture = manager.getTexture("ball");
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
            lives--;
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
        if(isAttached) {
            isAttached = false;
            ballBody.setType(BodyType.DynamicBody);
            getBallBody().applyForceToCenter(new Vector2(0, 63), true);
        }
    }
    public int getNumOfLives() {
        return lives;
    }
    public void setHealth(int h) {
        lives = h;
    }

    @Override
    public void dispose() {
        texture.dispose();
        ballTexture.dispose();
    }
}