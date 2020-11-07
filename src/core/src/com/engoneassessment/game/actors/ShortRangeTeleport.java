package com.engoneassessment.game.actors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Random;

public class ShortRangeTeleport extends Hostile {

    public ShortRangeTeleport(TextureRegion region) {super(region);}

    public void transfer() {
        Random location = new Random(100); // random number input, need to be changed
        String loc = location.toString();
        setPos(loc);
    }

}
