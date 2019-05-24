/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbr7.core.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
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
    public Player(Body b, ResourceManager manager) {
        super(b);
        Texture paddleTexture = manager.get("paddle");
        ballTexture = manager.get("ball");
        setTexture(paddleTexture);
    }
    @Override
    public void render(SpriteBatch b) {
        b.begin();
        b.draw(texture, GameDetails.scaleUp(body.getPosition().x) - texture.getWidth() / 2, GameDetails.scaleUp(body.getPosition().y) - texture.getHeight() / 2);
        b.draw(ballTexture, GameDetails.scaleUp(ballBody.getPosition().x) - ballTexture.getWidth() / 2, GameDetails.scaleUp(ballBody.getPosition().y) - ballTexture.getHeight() / 2);
        b.end();
    }
    public void setBallBody(Body b) {
        ballBody = b;
    }
    public Body getBallBody() {
        return ballBody;
    }
}