package com.engoneassessment.game.screens.start;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.engoneassessment.game.actors.CustomActor;
import com.engoneassessment.game.GameEntry;
import com.engoneassessment.game.ui.UIStage;
import com.engoneassessment.game.ui.controls.ButtonClickListener;
import com.engoneassessment.game.ui.controls.labels.LabelStyles;
import com.engoneassessment.game.ui.startui.DemoButton;
import com.engoneassessment.game.ui.startui.PlayButton;
import com.engoneassessment.game.ui.startui.QuitButton;

public class StartScreen implements Screen {

    private GameEntry gameEntry;
    public UIStage uiStage;
    private PlayButton playButton;
    private DemoButton demoButton;
    private QuitButton quitButton;

    private Label labelGameTitle;

    SpriteBatch backgroundBatch;
    private TextureRegion[] backgroundFrames;
    private Animation walkAnimation;
    private TextureRegion currentFrame;


    public StartScreen(final GameEntry gameEntry){

        this.gameEntry = gameEntry;

        uiStage = new UIStage(new StretchViewport(GameEntry.VIEW_WIDTH, GameEntry.VIEW_HEIGHT));

        labelGameTitle = new Label("Auber Game", LabelStyles.getGameTitleLabelStyle());
        labelGameTitle.setPosition(uiStage.getWidth()/2-labelGameTitle.getWidth()/2,800);

        //Creates the play button and move it to the correct place
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
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                gameEntry.setScreen(gameEntry.getInfirmaryScreen());
                gameEntry.setSpawnTime(System.currentTimeMillis());
                gameEntry.setDemo(false);
            }
        });

        //Creates the demo button and move it to the correct place
        demoButton = new DemoButton(this.uiStage);
        demoButton.setWidth(demoButton.getWidth()/1.5f);
        demoButton.setHeight(demoButton.getHeight()/1.5f);
        demoButton.setPosition(uiStage.getWidth()/2-demoButton.getWidth()/2,280);

        //Detects any inputs related to the demo button
        demoButton.setClickListener(new ButtonClickListener(){
            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link Event#handle() handled}.
             * @see ButtonClickListener */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                gameEntry.setScreen(gameEntry.getInfirmaryScreen());
                gameEntry.setSpawnTime(System.currentTimeMillis());
                gameEntry.setDemo(true);
            }
        });

        //Creates the quit button and move it to the correct place
        quitButton = new QuitButton(this.uiStage);
        quitButton.setWidth(quitButton.getWidth()/1.5f);
        quitButton.setHeight(quitButton.getHeight()/1.5f);
        quitButton.setPosition(uiStage.getWidth()/2-quitButton.getWidth()/2,60);

        //Detects any inputs related to the demo button
        quitButton.setClickListener(new ButtonClickListener(){
            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link Event#handle() handled}.
             * @see ButtonClickListener */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.app.exit();

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

        uiStage.addActor(labelGameTitle);
        uiStage.addActor(playButton);

        Gdx.input.setInputProcessor(uiStage);
    }

    /**
     * Called when this screen becomes the current screen for a Game.
     */
    @Override
    public void show() {
        backgroundBatch = new SpriteBatch();
        Gdx.input.setInputProcessor(uiStage);
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
        backgroundBatch.draw(currentFrame, 0, 0);
        backgroundBatch.end();

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
