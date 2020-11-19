package com.engoneassessment.game.screens.end;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.engoneassessment.game.GameEntry;
import com.engoneassessment.game.ui.UIStage;
import com.engoneassessment.game.ui.controls.Button;
import com.engoneassessment.game.ui.controls.ClickableUIElement;
import com.engoneassessment.game.ui.controls.labels.LabelStyles;
import com.engoneassessment.game.ui.startui.PlayButton;

public class EndScreen implements Screen {
    private GameEntry gameEntry;

    private UIStage uiStage;
    private Button menuButton;
    private Label labelResult;


    public EndScreen(final GameEntry gameEntry){
        this.gameEntry = gameEntry;

        uiStage = new UIStage(new StretchViewport(GameEntry.VIEW_WIDTH, GameEntry.VIEW_HEIGHT));

        labelResult = new Label("You Lose", LabelStyles.getGameTitleLabelStyle());
        labelResult.setPosition(uiStage.getWidth() / 2 - labelResult.getWidth() / 2, 800);



        //Creates the menu button and move it to the correct place
        menuButton = new Button(
                this.uiStage,
                new TextureRegion(new Texture("Menu/Buttons/menuNormal.jpg")),
                new TextureRegion(new Texture("Menu/Buttons/menuHighlighted.jpg")),
                new TextureRegion(new Texture("Menu/Buttons/menuClicked.jpg")),
                        null);
        menuButton.setPosition(uiStage.getWidth()/2- menuButton.getWidth()/2,400);

        //Detects any inputs related to the play button
        menuButton.addListener(new ClickListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                menuButton.setButtonUIState(ClickableUIElement.ButtonUIState.hovered);
                super.enter(event, x, y, pointer, fromActor);
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                menuButton.setButtonUIState(ClickableUIElement.ButtonUIState.normal);
                super.enter(event, x, y, pointer, fromActor);
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuButton.setButtonUIState(ClickableUIElement.ButtonUIState.pressed);
                gameEntry.setScreen(gameEntry.getStartScreen());
                gameEntry.create();
                super.clicked(event, x, y);
            }
        });

        uiStage.addActor(labelResult);
        uiStage.addActor(menuButton);

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

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void updateGameWon(){
        labelResult.setText("YOU WIN");
    }
}
