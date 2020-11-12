package com.engoneassessment.game.screens.main;
import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.engoneassessment.game.GameEntry;
import com.engoneassessment.game.actors.CustomActor;
import com.engoneassessment.game.actors.characters.Player;
import com.engoneassessment.game.io.ClickEventListener;
import com.engoneassessment.game.io.CustomInputProcessor;
import com.engoneassessment.game.ui.HUD;


//This screeen is never shown in game.
public class GameScreen implements Screen {

    private GameEntry gameEntry;
    public static GameScreen currentWorld;

    Player player;
    public Stage stage;

    public Stage UIstage;
    public Player auber;
    public HUD hud;
  
    public GameScreen(GameEntry gameEntry){
        currentWorld = this;
        this.gameEntry = gameEntry;
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        stage = new Stage(new StretchViewport(gameEntry.VIEW_WIDTH, gameEntry.VIEW_HEIGHT));
        auber = new Player(new TextureRegion(new Texture("Characters/Auber/idle/idle.gif")));
        stage.addActor(auber);
        Gdx.input.setInputProcessor(stage);
        stage.addListener(new PlayerInputListener());

        hud = new HUD(new StretchViewport(gameEntry.VIEW_WIDTH, gameEntry.VIEW_HEIGHT),auber);
    }
  
    /**
     * Called when this screen becomes the current screen for a Game.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        //Checks for movement keys being held
        keysPressed();
        //Sets the camera position to the centre of the player
        stage.getViewport().getCamera().position.set(auber.getX()+auber.getWidth()/2,auber.getY()+auber.getHeight()/2,0);
        stage.getViewport().getCamera().update();
        //Fills the screen
        Gdx.gl.glClearColor(0.39f, 0.58f, 0.92f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        hud.act();
        hud.draw();
    }

    /**
     * @param width the width you want to resize with.
     * @param height the height you want to resize with.
     * @link ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
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

    public void keysPressed(){
        //Moves the auber around
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            auber.moveBy(0,auber.getSpeed() * Gdx.graphics.getDeltaTime());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            auber.moveBy(- auber.getSpeed() * Gdx.graphics.getDeltaTime(),0);

        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            auber.moveBy(0,- auber.getSpeed() * Gdx.graphics.getDeltaTime());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            auber.moveBy(auber.getSpeed() * Gdx.graphics.getDeltaTime(),0);
        }

    }
}
