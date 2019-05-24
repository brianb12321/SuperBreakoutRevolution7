/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbr7.core.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.sbr7.core.Box2dSprite;

/**
 *
 * @author gamec
 */
public class Player extends Box2dSprite {
    
    public Player(Body b, Texture t) {
        super(b, t);
    }
    
}
