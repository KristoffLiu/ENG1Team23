package com.engoneassessment.game.screens.rooms;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.engoneassessment.game.GameEntry;
import com.engoneassessment.game.actors.characters.Player;
import com.engoneassessment.game.actors.rooms.Room;
import com.engoneassessment.game.ui.HUD;

public class InfirmaryScreen implements Screen {
    private final GameEntry gameEntry;

    final Room floor;
    final Room walls;
    public Stage stage;
    public Player auber;
    final String stageTitle;
    final Label LabelStageTitle;

    public InfirmaryScreen(GameEntry gameEntry){
        this.gameEntry = gameEntry;
        stageTitle = "Infirmary";

        stage = new Stage(new StretchViewport(GameEntry.VIEW_WIDTH, GameEntry.VIEW_HEIGHT));

        floor = new Room(new TextureRegion(new Texture("Rooms/General Square/Floor.png")),stageTitle);
        walls = new Room(new TextureRegion((new Texture("Rooms/General Square/Wall.png"))),stageTitle);
        stage.addActor(floor);
        stage.addActor(walls);

        LabelStageTitle = new Label(stageTitle,gameEntry.getStyle());
        LabelStageTitle.setPosition(stage.getWidth()/2-LabelStageTitle.getWidth()/2,stage.getHeight()/2);
        stage.addActor(LabelStageTitle);



        stage.addListener(gameEntry.getKeyboardInputHandler());
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void show() {
        auber = gameEntry.getAuber();
        stage.addActor(auber);
        auber.setPosition(stage.getWidth()/2-auber.getWidth()/2,stage.getHeight()/2-auber.getHeight()/2);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        //Checks for movement keys being held
        keysPressed();
        //Sets the camera position to the centre of the player
        stage.getViewport().getCamera().position.set(auber.getX()+auber.getWidth()/2,auber.getY()+auber.getHeight()/2,0);
        stage.getViewport().getCamera().update();
        Gdx.gl.glClearColor(0, 0, 0, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
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
        gameEntry.setAuber(auber);
    }

    @Override
    public void dispose() {

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

        //If the auber is out of bounds, move him back in
        if(auber.getY() > 795){
            auber.setY(795);
        }
        if(auber.getY() < 14){
            auber.setY(15);
        }
        if(auber.getX() < 424){
            auber.setX(424);
        }
        if(auber.getX() > 1451){
            auber.setX(1441);
        }
    }
}
