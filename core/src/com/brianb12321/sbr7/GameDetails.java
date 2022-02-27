/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brianb12321.sbr7;

/**
 *
 * @author gamec
 */
public class GameDetails {
    public static final String TITLE = "Super Breakout Revolution 7";
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 864;
    public static float PPM = 100f;
    public static final float MPP = 1 / PPM;
    public static final float WORLD_WIDTH = WIDTH / PPM;
    public static final float WORLD_HEIGHT = HEIGHT / PPM;
    public static final int STARTING_LIVES = 50;
    public static final int MIN_LIVES_TIL_WARNING = 5;
    public static final float VOLUME = 1f;
    public static float scaleDown(float value) {
        return value / PPM;
    }
    public static float scaleUp(float value) {
        return value * PPM;
    }
}