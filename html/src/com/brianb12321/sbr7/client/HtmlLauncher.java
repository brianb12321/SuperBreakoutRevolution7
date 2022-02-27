package com.brianb12321.sbr7.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.brianb12321.sbr7.GameDetails;
import com.brianb12321.sbr7.SuperBreakoutRevolution7;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                // Resizable application, uses available space in browser

                GameDetails.PPM = 110;
                GwtApplicationConfiguration config =  new GwtApplicationConfiguration(true);
                config.padHorizontal = 1;
                config.padVertical = 1;

                return config;
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new SuperBreakoutRevolution7();
        }
}