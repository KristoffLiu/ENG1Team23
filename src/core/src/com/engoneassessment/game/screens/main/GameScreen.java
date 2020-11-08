package com.engoneassessment.game.screens.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.engoneassessment.game.GameEntry;
import com.engoneassessment.game.actors.CustomActor;
import com.engoneassessment.game.actors.charactors.Player;

public class GameScreen implements Screen {

    private GameEntry gameEntry;
    public static GameScreen currentWorld;

    Player player;

    public GameScreen(GameEntry gameEntry){
        currentWorld = this;
        this.gameEntry = gameEntry;

        stage = new Stage(new StretchViewport(gameEntry.VIEW_WIDTH, gameEntry.VIEW_HEIGHT));

        Texture texture = new Texture(Gdx.files.internal("badlogic.jpg"));
        player = new Player(new TextureRegion(texture));
        player.addListener(new PlayerInputListener());


        stage.addActor(player);
    }

    public Stage stage;
    public Stage UIstage;

    /**
     * Called when this screen becomes the current screen for a Game.
     */
    @Override
    public void show() {

    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {

    }

    /**
     * @param width the width you want to resize with.
     * @param height the height you want to resize with.
     * @link ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     * @link ApplicationListener#pause()
     */
    @Override
    public void pause() {

    }

    /**
     * @link ApplicationListener#resume()
     */
    @Override
    public void resume() {

    }

    /**
     * Called when this screen is no longer the current screen for a Game.
     */
    @Override
    public void hide() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }

    public void addActor(CustomActor customActor){
        stage.addActor(customActor);
    }

    public void CollisionHandler(){

    }

    public class PlayerInputListener extends InputListener {
        @Override
        public boolean keyDown(InputEvent event, int keycode) {
            switch (keycode) {
                case Input.Keys.UP: {

                    break;
                }
                case Input.Keys.DOWN: {

                    break;
                }
                case Input.Keys.A: {

                    break;
                }
                case Input.Keys.ENTER: {

                    break;
                }
                default: {

                    break;
                }
            }
            return false;
        }
    }
}
