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
import com.engoneassessment.game.actors.characters.npcs.Hostile;
import com.engoneassessment.game.actors.characters.npcs.NonHostile;
import com.engoneassessment.game.actors.rooms.Room;

public class RoomScreen implements Screen {
    private final GameEntry gameEntry;

    final Room floor;
    final Room walls;
    public Stage stage;
    public Player auber;
    final int minX;
    final int minY;
    final int maxX;
    final int maxY;
    public Array<NonHostile> nonHostiles;
    public Array<Hostile> hostiles;
    private String name;


    public RoomScreen(GameEntry gameEntry,String name, int numNPCs){
        this.gameEntry = gameEntry;
        //Sets the boundaries of the room
        minX = 424;
        minY = 14;
        maxX = 1451;
        maxY = 795;

        //Sets the name of the room for use in the UI
        this.name = name;

        //Initialises nonHostiles and hostiles Arrays
        nonHostiles = new Array<>();
        hostiles = new Array<>();

        stage = new Stage(new StretchViewport(GameEntry.VIEW_WIDTH, GameEntry.VIEW_HEIGHT));

        //Creates the walls and floors of the rooms
        floor = new Room(new TextureRegion(new Texture("Rooms/General Square/Floor.png")));
        walls = new Room(new TextureRegion(new Texture("Rooms/General Square/Wall.png")));
        stage.addActor(floor);
        stage.addActor(walls);

        //Creates the non hostiles in the rooms and gives them a random starting position
        for (int i = 1;i <= numNPCs; i ++){
            nonHostiles.add(new NonHostile(new TextureRegion(new Texture("Characters/other/idle/idle.gif")),this));
            nonHostiles.get(nonHostiles.size-1).setPosition(GameEntry.getRandom().nextInt(this.getMaxX()-this.getMinX())+this.getMinX(), GameEntry.getRandom().nextInt(this.getMaxY()-this.getMinY())+this.getMinY());
            stage.addActor(nonHostiles.get(nonHostiles.size-1));
        }

        stage.addListener(gameEntry.getKeyboardInputHandler());
        Gdx.input.setInputProcessor(stage);



    }

    @Override
    public void show() {
        //Gets the auber from game entry and sets it to the centre of the room
        auber = gameEntry.getAuber();
        stage.addActor(auber);
        auber.setPosition(stage.getWidth()/2-auber.getWidth()/2,stage.getHeight()/2-auber.getHeight()/2);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        //Checks for movement keys being held
        keysPressed();
        //Runs a function to spawn hostiles randomly in different rooms
        spawnHostiles();
        //Moves the hostiles and non hostiles
        moveNPCS();
        //Uses the hostile abilities
        useAbilities();
        //Sets the camera position to the centre of the player
        stage.getViewport().getCamera().position.set(auber.getX()+auber.getWidth()/2,auber.getY()+auber.getHeight()/2,0);
        stage.getViewport().getCamera().update();
        //Clears the screen
        Gdx.gl.glClearColor(0, 0, 0, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Acts and draws the stage
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
        //gameEntry.setAuber(auber);
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
        if(auber.getY() > maxY){
            auber.setY(maxY);
        }
        if(auber.getY() < minY){
            auber.setY(minY);
        }
        if(auber.getX() < minX){
            auber.setX(minX);
        }
        if(auber.getX() > maxX){
            auber.setX(maxX);
        }
    }

    public void moveNPCS(){
        //Goes through every non hostile and hostile and moves them
        for(NonHostile nonHostile:nonHostiles){
            nonHostile.randomMove();
        }

        for(Hostile hostile:hostiles) {
            hostile.randomMove();
        }
    }

    public void spawnHostiles(){
        if(System.currentTimeMillis() - gameEntry.getSpawnTime() > 1) {
            //System.out.println("Sabotage");
            gameEntry.sabotage();
            gameEntry.setSpawnTime(System.currentTimeMillis());
        }
    }

    public void useAbilities(){
        for(Hostile hostile:hostiles) {
            //If the ability is on cooldown decrease the cooldown by one, otherwise use the ability
            if(hostile.getAbilityCooldown()!=0){
                hostile.setAbilityCooldown(hostile.getAbilityCooldown()-1);
            }

            else {
                hostile.useAbility(auber, GameEntry.getRandom());
            }

            if(hostile.getAbilityTimer()!=0){
                hostile.setAbilityTimer(hostile.getAbilityCooldown()-1);
            }
            else if(hostile.getAbilityActivated()){
                hostile.deactivateAbility();
            }

        }
    }

    public int getMinX() {
        return minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxY() {
        return maxY;
    }

    public String getName(){
        return name;
    }
}
