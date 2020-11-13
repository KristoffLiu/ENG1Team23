package com.engoneassessment.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.engoneassessment.game.GameEntry;
import com.engoneassessment.game.actors.characters.Player;
import com.engoneassessment.game.actors.characters.npcs.NonHostile;
import com.engoneassessment.game.actors.rooms.Room;
import com.engoneassessment.game.ui.HUD;

public class RoomScreen implements Screen {
    private final GameEntry gameEntry;
    protected String name;

    final Room floor;
    final Room walls;
    public Stage stage;
    public Player auber;
    public HUD hud;
    final int min_x;
    final int min_y;
    final int max_x;
    final int max_y;

    private Array<NonHostile> nonHostiles;


    public RoomScreen(GameEntry gameEntry){
        this.gameEntry = gameEntry;

        //Sets the boundaries of the room
        min_x = 424;
        min_y = 14;
        max_x = 1451;
        max_y = 795;
        nonHostiles = new Array<>();

        stage = new Stage(new StretchViewport(GameEntry.VIEW_WIDTH, GameEntry.VIEW_HEIGHT));

        //Creates the walls and floors of the rooms
        floor = new Room(new TextureRegion(new Texture("Rooms/General Square/Floor.png")));
        walls = new Room(new TextureRegion(new Texture("Rooms/General Square/Wall.png")));
        stage.addActor(floor);
        stage.addActor(walls);

        //Creates the non hostiles in the rooms and gives them a random starting position
        for (int i = 0;i < 100; i ++){
            nonHostiles.add(new NonHostile(new TextureRegion(new Texture("Characters/other/idle/idle.gif"))));
            nonHostiles.get(nonHostiles.size-1).setPosition(GameEntry.getRandom().nextInt(max_x-min_x)+min_x, GameEntry.getRandom().nextInt(max_y-min_y)+min_y);
            stage.addActor(nonHostiles.get(nonHostiles.size-1));
        }

        //Gets the auber from game entry and sets it to the centre of the room

        stage.addListener(gameEntry.getKeyboardInputHandler());
        Gdx.input.setInputProcessor(stage);

        hud = gameEntry.hud;
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
        //Moves the hostiles and non hostiles
        moveNPCS();
        //Sets the camera position to the centre of the player
        stage.getViewport().getCamera().position.set(auber.getX()+auber.getWidth()/2,auber.getY()+auber.getHeight()/2,0);
        stage.getViewport().getCamera().update();
        //Clears the screen
        Gdx.gl.glClearColor(0, 0, 0, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Acts and draws the stage
        stage.act();
        stage.draw();
        hud.act();
        hud.draw();
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
        if(auber.getY() > max_y){
            auber.setY(max_y);
        }
        if(auber.getY() < min_y){
            auber.setY(min_y);
        }
        if(auber.getX() < min_x){
            auber.setX(min_x);
        }
        if(auber.getX() > max_x){
            auber.setX(max_x);
        }
    }

    public void moveNPCS(){
        //Goes through every hostile in the non hostiles array and moves them
        for(NonHostile nonHostile:nonHostiles){
            nonHostile.randomMove(GameEntry.getRandom(),424,1451,14,795);
        }
    }
}
