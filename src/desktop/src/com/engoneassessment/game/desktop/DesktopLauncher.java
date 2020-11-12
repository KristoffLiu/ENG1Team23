package com.engoneassessment.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.engoneassessment.game.GameEntry;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		float scale = 0.6F;
		config.title = "Auber Game - York CS ENG1 Assessment - Team 23";
		config.
		config.width = (int) (1920 * scale);
		config.height = (int) (1080 * scale);

		config.resizable = true;
		new LwjglApplication(new GameEntry(), config);
	}
}
