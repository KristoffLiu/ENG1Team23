package com.engoneassessment.game.screens.rooms;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.engoneassessment.game.GameEntry;
import com.engoneassessment.game.screens.RoomScreen;

public class InfirmaryScreen extends RoomScreen {

    public InfirmaryScreen(GameEntry gameEntry, String name, int numNPCs) {
        super(gameEntry, name, numNPCs);

    }
    
    public void render(float delta) {
        if (auber.getHealth() < 100) {
            auber.setHealth(auber.getHealth()+delta/1);
        }
        //Checks for movement keys being held
        keysPressed();
        //Runs a function to spawn hostiles randomly in different rooms
        spawnHostiles();
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
    }
}
