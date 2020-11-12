package com.engoneassessment.game.actors.characters.npcs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.engoneassessment.game.actors.buildings.Building;
import com.engoneassessment.game.actors.characters.Character;
import com.engoneassessment.game.actors.rooms.Room;

import java.util.Random;

public class NPC extends Character {

    private System targetSystem;
    private Room targetRoom;
    private Array<Float> path;
    private float movement_x;
    private float movement_y;
    private float movement_speed;

    public NPC(TextureRegion textureRegion) {
        super(textureRegion);
        movement_speed = (float)0.8;

    }

    public void setTargetSystem(System target){

    }

    public Boolean checkTarget(Building target){
        return true;
    }

    private void workOutPathToSystem(){

    }

    public void changeDirectionRandom(Random random){
        movement_x -= ((float)random.nextInt(3)-1)*random.nextFloat();
        movement_y -= ((float)random.nextInt(3)-1)*random.nextFloat();
    }

    public void randomMove(Random random, int x_min, int x_max, int y_min, int y_max){
        if(Math.random() < 0.05){
            this.changeDirectionRandom(random);
        }

        this.moveBy(movement_x*movement_speed,movement_y*movement_speed);

        if(this.getY() > y_max){
            this.setY(y_max);
            movement_y = -movement_y;
        }
        if(this.getY() < y_min){
            this.setY(y_min);
            movement_y = -movement_y;
        }
        if(this.getX() < x_min){
            this.setX(x_min);
            this.changeDirectionRandom(random);
            movement_x = -movement_x;

        }
        if(this.getX() > x_max){
            this.setX(x_max);
            this.changeDirectionRandom(random);
            movement_x = -movement_x;

        }
    }
}
