package com.engoneassessment.game.actors.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.screens.RoomScreen;

public class Player extends Character{
    float speed;
    public Player(TextureRegion region, RoomScreen screen) {
        super(region,screen, new Texture("Characters/Auber/run/run.png"),
                new Texture("Characters/Auber/idle/idle.png"));
        speed = 1000;
    }
    // every time when Auber arrests a hostile,(which means Auber collides the hostile)
    // his health is reduced a little

    public float getSpeed(){
        return speed;
    }

}