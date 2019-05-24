/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbr7.core;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

/**
 *
 * @author gamec
 */
public class Box2dSprite {
    protected Body body;
    protected Texture texture;
    public Box2dSprite(Body b, Texture t) {
        body = b;
        texture = t;
    }
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(texture, GameDetails.scaleUp(body.getPosition().x) - texture.getWidth() / 2, GameDetails.scaleUp(body.getPosition().y) - texture.getHeight() / 2);
        sb.end();
    }
    public Body getBody() {
        return body;
    }
}