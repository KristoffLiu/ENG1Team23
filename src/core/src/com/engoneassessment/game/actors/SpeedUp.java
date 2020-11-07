package com.engoneassessment.game.actors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpeedUp extends Hostile {

    private boolean sMode = false; // initially not speeding up


    public SpeedUp(TextureRegion region) {super(region);}

    public void sModeChange() {
        if (sMode = false) {
            sMode = true;
        } else {
            sMode = false;
        }
    }

    public void sprint() {
        if (sMode = true) {
            setSpeed(5); // which is a faster speed
        }
    }

}
