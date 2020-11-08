package com.engoneassessment.game.screens.start;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
import com.engoneassessment.game.ui.MenuButton;

public class StartScreen implements Screen {

    private GameEntry gameEntry;
    private Texture logoTexture;
    private Stage stage;
    private CustomActor customActor;
    private TextField usernameTextField;
    private MenuButton playButton;

    private Label labelGameTitle;
    BitmapFont font;


    public StartScreen(final GameEntry gameEntry){
        this.gameEntry = gameEntry;

        stage = new Stage(new StretchViewport(gameEntry.VIEW_WIDTH, gameEntry.VIEW_HEIGHT));

        font = new BitmapFont(Gdx.files.internal("font/ImpactFont.fnt"));
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear); //Make the font much clearer on the edge
        font.getData().setScale(2.0f);
        font.setColor(100,256,256,256);
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = font;

        labelGameTitle = new Label("Auber Game",style);
        labelGameTitle.setPosition(0,0);

        //Creates the menu button and move it to the correct place
        playButton = new MenuButton(new TextureRegion(new Texture("Menu/Buttons/playNormal.jpg")),new TextureRegion(new Texture("Menu/Buttons/playHighlighted.jpg")));
        playButton.setX(stage.getWidth()/2-playButton.getWidth()/2);
        playButton.setY(600);

        //Detects any inputs related to the play button
        playButton.addListener(new ClickListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                playButton.switchHighlighted();
                super.enter(event, x, y, pointer, fromActor);
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                playButton.switchHighlighted();
                super.enter(event, x, y, pointer, fromActor);
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameEntry.startGame();
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
