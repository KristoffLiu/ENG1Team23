package com.engoneassessment.game.actors.characters.npcs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.actors.characters.Character;
import com.engoneassessment.game.screens.RoomScreen;

import java.util.Random;

public class NPC extends Character {

    private float movement_x;
    private float movement_y;
    private static Random random;

    /**
     *
     * @param textureRegion texture of the NPC
     * @param screen the screen the NPC is being created on
     * @param runTexture the texture for running
     * @param idleTexture the texture for idling
     */
    public NPC(TextureRegion textureRegion, RoomScreen screen, Texture runTexture, Texture idleTexture) {
        super(textureRegion, screen, runTexture, idleTexture);
        //Used for generating random numbers
        random = new Random();
        movement_x -= ((float)random.nextInt(3)-1)*random.nextFloat();
        movement_y -= ((float)random.nextInt(3)-1)*random.nextFloat();

    }

    /**
     *
     * @param random random number generator
     */
    public void changeDirectionRandom(Random random){
        movement_x -= ((float)random.nextInt(2)-1)*random.nextFloat();
        movement_y -= ((float)random.nextInt(2)-1)*random.nextFloat();
    }

    public void randomMove(){
        //If the random number generator gets a float < 0.05 it changes the direction the NPC is moving in
        if(Math.random() < 0.05){
            this.changeDirectionRandom(random);
        }

        this.moveBy(movement_x*getSpeed(),movement_y*getSpeed());

        //Checks if the npc has gone out of bounds and turns them around if they are
        if(this.getY() > getCurrentScreen().getMaxY()){
            this.setY(getCurrentScreen().getMaxY());
            movement_y = -movement_y;
        }

        if(this.getY() < getCurrentScreen().getMinY()){
            this.setY(getCurrentScreen().getMinY());
            movement_y = -movement_y;
        }

        if(this.getX() < getCurrentScreen().getMinX()){
            this.setX(getCurrentScreen().getMinX());
            movement_x = -movement_x;
        }

        if(this.getX() > getCurrentScreen().getMaxX()){
            this.setX(getCurrentScreen().getMaxX());
            movement_x = -movement_x;
        }
    }

}
