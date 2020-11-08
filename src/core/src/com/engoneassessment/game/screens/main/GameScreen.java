package com.engoneassessment.game.screens.main;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.engoneassessment.game.GameEntry;
import com.engoneassessment.game.actors.CustomActor;
import com.engoneassessment.game.actors.Player;

public class GameScreen implements Screen {

    private GameEntry gameEntry;
    public static GameScreen currentWorld;

    Player player;
    public Stage stage;
    public Stage UIstage;

    public GameScreen(GameEntry gameEntry){
        currentWorld = this;
        this.gameEntry = gameEntry;

        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        Texture texture = new Texture(Gdx.files.internal("badlogic.jpg"));
        player = new Player(new TextureRegion(texture));

        stage = new Stage(new StretchViewport(gameEntry.VIEW_WIDTH, gameEntry.VIEW_HEIGHT));
        stage.addActor(player);

        Gdx.input.setInputProcessor(stage);
        stage.addListener(new PlayerInputListener());
    }



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
        Gdx.gl.glClearColor(0.39f, 0.58f, 0.92f, 1.0f);

        stage.act();
        stage.draw();
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

    private class PlayerInputListener extends InputListener {
        @Override
        public boolean keyDown(InputEvent event, int keycode) {
            switch (keycode) {
                case Input.Keys.RIGHT: {
                    player.MoveByAction();
                    Gdx.app.log("Tag", "right");
                    break;
                }
            }
            return false;
        }
    }
}
