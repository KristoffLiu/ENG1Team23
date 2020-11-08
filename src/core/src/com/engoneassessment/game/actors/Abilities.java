package com.engoneassessment.game.actors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.Random;

public class Abilities extends Hostile {

    private boolean iMode = true; // when mode = true means hostile is currently invisible
    private boolean sMode = false; // initially not speeding up

    public Abilities(TextureRegion region) {
        super(region);
    }

    public void iModeChange() {
        if (iMode = true) {
            iMode = false;
        }else{
            iMode = true;
        }

    }

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

    public void transfer() {
        Random location = new Random(100); // random number input, need to be changed
        String loc = location.toString();
        setPos(loc);
    }

}
