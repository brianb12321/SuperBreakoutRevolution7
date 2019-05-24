/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbr7.core;

import com.badlogic.gdx.graphics.Texture;
import java.util.HashMap;

/**
 *
 * @author gamec
 */
public class ResourceManager {
    private HashMap<String, Texture> _textures;
    public ResourceManager() {
        _textures = new HashMap<String, Texture>();
    }
    public void add(String name, String internalName) {
        _textures.put(name, new Texture(internalName));
    }
    public Texture get(String name) {
        return _textures.get(name);
    }
    public void dispose(String name) {
        _textures.get(name).dispose();
    }
}