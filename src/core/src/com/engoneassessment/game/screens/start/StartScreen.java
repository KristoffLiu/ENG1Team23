package com.engoneassessment.game.screens.start;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.engoneassessment.game.actors.CustomActor;
import com.engoneassessment.game.GameEntry;
import com.engoneassessment.game.ui.controls.ClickableUIElement;
import com.engoneassessment.game.ui.startui.PlayButton;

public class StartScreen implements Screen {

    private GameEntry gameEntry;
    private Texture logoTexture;
    private Stage stage;
    private CustomActor customActor;
    private TextField usernameTextField;
    private PlayButton playButton;

    private Label labelGameTitle;

    public StartScreen(final GameEntry gameEntry){

        this.gameEntry = gameEntry;

        stage = new Stage(new StretchViewport(GameEntry.VIEW_WIDTH, GameEntry.VIEW_HEIGHT));

        labelGameTitle = new Label("Auber Game",gameEntry.getStyle());
        labelGameTitle.setPosition(stage.getWidth()/2-labelGameTitle.getWidth()/2,800);

        //Creates the menu button and move it to the correct place
        playButton = new PlayButton(new TextureRegion(new Texture("Menu/Buttons/playNormal.jpg")),new TextureRegion(new Texture("Menu/Buttons/playHighlighted.jpg")));
        playButton.setPosition(stage.getWidth()/2-playButton.getWidth()/2,400);

        //Detects any inputs related to the play button
        playButton.addListener(new ClickListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                playButton.setButtonUIState(ClickableUIElement.ButtonUIState.hovered);
                super.enter(event, x, y, pointer, fromActor);
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                playButton.setButtonUIState(ClickableUIElement.ButtonUIState.normal);
                super.enter(event, x, y, pointer, fromActor);
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                playButton.setButtonUIState(ClickableUIElement.ButtonUIState.pressed);
                super.clicked(event, x, y);
            }
        });

        stage.addActor(labelGameTitle);
        stage.addActor(playButton);

        Gdx.input.setInputProcessor(stage);
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
        Gdx.gl.glClearColor(0.39f, 0.58f, 0.92f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
}
