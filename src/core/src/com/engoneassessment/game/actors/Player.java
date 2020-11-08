package com.engoneassessment.game.actors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends Character {

    public Player(TextureRegion region) {
        super(region);
    }
    // every time when Auber arrests a hostile,(which means Auber collides the hostile)
    // his health is reduced a little
}
