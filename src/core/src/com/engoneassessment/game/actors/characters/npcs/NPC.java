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

    public NPC(TextureRegion textureRegion, RoomScreen screen, Texture runTexture, Texture idleTexture) {
        super(textureRegion, screen, runTexture, idleTexture);
        //Used for generating random numbers
        random = new Random();
        movement_x -= ((float)random.nextInt(3)-1)*random.nextFloat();
        movement_y -= ((float)random.nextInt(3)-1)*random.nextFloat();

    }

    public void changeDirectionRandom(Random random){
        movement_x -= ((float)random.nextInt(3)-1)*random.nextFloat();
        movement_y -= ((float)random.nextInt(3)-1)*random.nextFloat();
    }

    public void randomMove(){
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
