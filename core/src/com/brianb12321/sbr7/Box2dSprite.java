/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brianb12321.sbr7;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Disposable;

/**
 *
 * @author gamec
 */
public class Box2dSprite implements Disposable {
    protected Body body;
    protected Texture texture;
    public Box2dSprite(Body b) {
        body = b;
    }
    public void setTexture(Texture t) {
        texture = t;
    }
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(texture, getTextureX(), getTextureY());
        sb.end();
    }
    public Body getBody() {
        return body;
    }
    public float getHeight() {
        return texture.getHeight();
    }
    public float getX() {
        return body.getPosition().x;
    }
    public float getTextureX() {
        return GameDetails.scaleUp(body.getPosition().x) - texture.getWidth() / 2;
    }
    public float getTextureY() {
        return GameDetails.scaleUp(body.getPosition().y) - texture.getHeight() / 2;
    }
    public float getY() {
        return body.getPosition().y;
    }
    public void update(float dt) {
        
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}