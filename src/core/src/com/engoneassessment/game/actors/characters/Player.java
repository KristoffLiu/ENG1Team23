package com.engoneassessment.game.actors.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends Character{
    float speed;
    public Player(TextureRegion region) {
        super(region, new Texture("Characters/Auber/run/run.png"),
                new Texture("Characters/Auber/idle/idle.png"));
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
