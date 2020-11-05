package com.engoneassessment.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.engoneassessment.game.screens.main.GameScreen;
import com.engoneassessment.game.screens.setting.SettingScreen;
import com.engoneassessment.game.screens.start.StartScreen;
import com.engoneassessment.game.actors.Player;

/**
 * Since it may not only be a single screen/stage game,
 * then we should extend the whole game based on Game class,
 * instead of extending from ApplicationAdaptor interface.
 * This allows it to switch the screens/stages,
 * and control their rendering process from this top class.
 *
 * - Zhikang Liu 2020/11/01
 */

public class GameEntry extends Game {

    public static final float VIEW_WIDTH = 1920;
    public static final float VIEW_HEIGHT = 1080;

    //Screens Objects
    private StartScreen startScreen;
    private SettingScreen settingScreen;
    private GameScreen gameScreen;


    /**
     * Called when the game is first created.
     */
    @Override
    public void create() {
        // Create StartScreen
        startScreen = new StartScreen(this);
        stage = new Stage(new StretchViewport(gapmeEntry.VIEW_WIDTH, gameEntry.VIEW_HEIGHT));
        player = Player(new TextureRegion(new Texture("badlogic.png")));


        // Create MainGameScreen
        gameScreen = new GameScreen();

        setScreen(gameScreen);
    }

    public void switchScreen(){

    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0.39f, 0.58f, 0.92f, 1.0f);

        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        // super.dispose() I think you can't remove this line,
        // because there's some essential action in superclass's method.
        super.dispose();


        // When the game finished, dispose everything
        if (startScreen != null) {
            startScreen.dispose();
            startScreen = null;
        }
        if (gameScreen != null) {
            gameScreen.dispose();
            gameScreen = null;
        }
    }
}
