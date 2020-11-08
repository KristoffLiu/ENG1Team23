package com.engoneassessment.game.actors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.actors.charactors.npcs.NPC;

public class Hostile extends NPC {

    private boolean arrested = false; // hostile's initial status is not arrested

    public int NumOfDestroy = 0; // record the number of systems the hostile destroy

    public Hostile(TextureRegion region) {
        super(region);
    }

    public void attackPlayer(){

    }

    private Boolean checkIfPlayerInRange(){
        return true;
    }

    public void getCaught() {
        arrested = true;
    }


    public int getNumOfDestroy() {
        return NumOfDestroy;
    }

}



    /*
    private int speed;
    // i set the speed as integer initially, because using double may be more complicated
    private String ability; // every hostile has a ability
    // abilities such as be invisible, speeding up...
    private Double health = 100.0;
    private String position; // variable type has to be changed to what we define later
     */

    /*
    public String getPos() {
        return position;
    }

    public void setPos(String pos) {
        position = pos;
    }

    public void setHealth(Double hp) {
        health = hp;
    }

    public Double getHealth() {
        return health;
    }

    public void setSpeed(int s) {
        speed = s;
    }
    */
