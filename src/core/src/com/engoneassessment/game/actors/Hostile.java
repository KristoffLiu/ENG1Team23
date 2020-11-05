package com.engoneassessment.game.actors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Hostile extends Character{

    private boolean arrested = false; // hostile's initial status is not arrested
    /*
    private int speed;
    // i set the speed as integer initially, because using double may be more complicated
    private String ability; // every hostile has a ability
    // abilities such as be invisible, speeding up...
    private Double health = 100.0;
    private String position; // variable type has to be changed to what we define later
     */

    public Hostile(TextureRegion region) {
        super(region);
    }

    public void getCaught() {
        arrested = true;
    }

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
}
