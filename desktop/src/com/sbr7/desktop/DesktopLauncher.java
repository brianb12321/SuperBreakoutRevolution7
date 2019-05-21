package com.sbr7.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sbr7.core.SuperBreakoutRevolution7;
import com.sbr7.core.GameDetails;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.title = GameDetails.TITLE;
                config.width = GameDetails.WIDTH;
                config.height = GameDetails.HEIGHT;
		new LwjglApplication(new SuperBreakoutRevolution7(), config);
	}
}
