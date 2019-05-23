/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbr7.core;

/**
 *
 * @author gamec
 */
public class GameDetails {
    public static final String TITLE = "Super Breakout Revolution 7";
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final float PPM = 32f;
    public static float normalize(int value) {
        return value / 32;
    }
}