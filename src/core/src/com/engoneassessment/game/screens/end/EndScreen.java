package com.engoneassessment.game.screens.end;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

public class EndScreen implements Screen {
    private GameEntry gameEntry;

    private UIStage uiStage;
    private Button menuButton;
    private Label labelResult;

    SpriteBatch backgroundBatch;
    private TextureRegion[] backgroundFrames;
    private Animation walkAnimation;
    private TextureRegion currentFrame;

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
                gameEntry.create();
                gameEntry.setScreen(gameEntry.getStartScreen());
                super.clicked(event, x, y);
            }
        });


        int background_frameCols = 2;
        int background_frameRows = 2;
        Texture hitbarTexture = new Texture(Gdx.files.internal("Background/space.png"));
        int perCellWidth = hitbarTexture.getWidth() / background_frameCols;
        int perCellHeight = hitbarTexture.getHeight() / background_frameRows;
        TextureRegion[][] background_cellRegions = TextureRegion.split(hitbarTexture, perCellWidth, perCellHeight);
        backgroundFrames = new TextureRegion[background_frameRows * background_frameCols];
        int index = 0;
        for (int row = 0; row < background_frameRows; row++) {
            for (int col = 0; col < background_frameCols; col++) {
                backgroundFrames[index++] = background_cellRegions[row][col];
            }
        }
        walkAnimation = new Animation(0.5F, backgroundFrames);
        walkAnimation.setPlayMode(Animation.PlayMode.LOOP);

        uiStage.addActor(labelResult);
        uiStage.addActor(menuButton);

        Gdx.input.setInputProcessor(uiStage);
    }

    /**
     * Called when this screen becomes the current screen for a Game.
     */
    @Override
    public void show() {
        backgroundBatch = new SpriteBatch();
        Gdx.input.setInputProcessor(uiStage);
        if(gameEntry.isDemo()){
            gameEntry.create();
            gameEntry.setScreen(gameEntry.getInfirmaryScreen());
            gameEntry.setSpawnTime(System.currentTimeMillis());
            gameEntry.setDemo(true);
        }
    }

    float stateTime = 0f;

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.39f, 0.58f, 0.92f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = (TextureRegion) walkAnimation.getKeyFrame(stateTime);
        backgroundBatch.begin();
        backgroundBatch.draw(currentFrame, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        backgroundBatch.end();

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
