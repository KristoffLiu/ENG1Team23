package com.engoneassessment.game.actors.characters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.screens.RoomScreen;

public class Player extends Character{
    float speed;
    public Player(TextureRegion region, RoomScreen screen) {
        super(region,screen);
        speed = 1000;
    }
    // every time when Auber arrests a hostile,(which means Auber collides the hostile)
    // his health is reduced a little

    public void ChangeDirection(){

    }

    public float getSpeed(){
        return speed;
    }

}
