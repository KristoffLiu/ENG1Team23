package com.engoneassessment.game.actors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.actors.characters.npcs.Hostile;

import java.util.Random;

public class Abilities{

    private boolean iMode = true; // when mode = true means hostile is currently invisible
    private boolean sMode = false; // initially not speeding up

    public Abilities() {
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
        }
    }

    public void transfer() {
        Random location = new Random(100); // random number input, need to be changed
        String loc = location.toString();
    }

}
