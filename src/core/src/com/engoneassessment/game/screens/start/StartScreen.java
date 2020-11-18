package com.engoneassessment.game.screens.start;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.engoneassessment.game.actors.CustomActor;
import com.engoneassessment.game.GameEntry;
import com.engoneassessment.game.ui.UIStage;
import com.engoneassessment.game.ui.controls.ButtonClickListener;
import com.engoneassessment.game.ui.controls.ClickableUIElementClickListener;
import com.engoneassessment.game.ui.controls.labels.LabelStyles;
import com.engoneassessment.game.ui.startui.PlayButton;

public class StartScreen implements Screen {

    private GameEntry gameEntry;
    private Texture logoTexture;
    public UIStage uiStage;
    private CustomActor customActor;
    private TextField usernameTextField;
    private PlayButton playButton;

    private Label labelGameTitle;

    public StartScreen(final GameEntry gameEntry){

        this.gameEntry = gameEntry;

        uiStage = new UIStage(new StretchViewport(GameEntry.VIEW_WIDTH, GameEntry.VIEW_HEIGHT));

        labelGameTitle = new Label("Auber Game", LabelStyles.getGameTitleLabelStyle());
        labelGameTitle.setPosition(uiStage.getWidth()/2-labelGameTitle.getWidth()/2,800);

        //Creates the menu button and move it to the correct place
        playButton = new PlayButton(this.uiStage);
        playButton.setWidth(playButton.getWidth()/1.5f);
        playButton.setHeight(playButton.getHeight()/1.5f);
        playButton.setPosition(uiStage.getWidth()/2-playButton.getWidth()/2,500);

        //Detects any inputs related to the play button
        playButton.setClickListener(new ButtonClickListener(){
            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link Event#handle() handled}.
             * @see ButtonClickListener */
            @Override
            public void clicked (InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                gameEntry.setScreen(gameEntry.getInfirmaryScreen());
            }
        });

        uiStage.addActor(labelGameTitle);
        uiStage.addActor(playButton);

        Gdx.input.setInputProcessor(uiStage);
    }

    /**
     * Called when this screen becomes the current screen for a Game.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(uiStage);
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
        uiStage.act();
        uiStage.draw();
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
