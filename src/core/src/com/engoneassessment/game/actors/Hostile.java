package com.engoneassessment.game.actors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Hostile extends Character{

    private Character hostile;

    private boolean arrested = false; // hostile's initial status is not arrested
    /*
    private int speed;
    // i set the speed as integer initially, because using double may be more complicated
    private String ability; // every hostile has a ability
    // abilities such as be invisible, speeding up...
    private Double health = 100.0;
    private String position; // variable type has to be changed to what we define later
     */
    public int NumOfDestroy = 0; // record the number of systems the hostile destroy

    public Hostile(TextureRegion region) {
        super(region);
        this.hostile = hostile;
    }

    public void getCaught() {
        arrested = true;
    }

    public int getNumOfDestroy() {
        return NumOfDestroy;
    }

    public void attackPlayer() {
        if (hostile.getPos() == Player.getPos()) {
            //then
            //Player.setHealth()
        }
    }

    public boolean checkPlayerRange() {
        return true;
    }

}
