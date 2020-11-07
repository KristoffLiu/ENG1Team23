package com.engoneassessment.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Character extends CustomActor {
    Array<Texture> actionTextures;

    public int speed;
    // i set the speed as integer initially, because using double may be more complicated
    private String ability; // every hostile has a ability
    // abilities such as be invisible, speeding up...
    private Double health = 100.0;
    public String position; // variable type has to be changed to what we define later

    public Character(TextureRegion textureRegion) {
        super(textureRegion);
    }

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


    /*
    0:stand
    1:stand_left
     */

    public void movement_keyboard_event(){
        //if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
        //    this.changeX(- 1200 * Gdx.graphics.getDeltaTime());
        //    //this.setTexture();
        //}
        //if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
        //    this.changeX(1200 * Gdx.graphics.getDeltaTime());
        //    //this.setTexture();
        //}
        //if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
        //    this.changeY(1200 * Gdx.graphics.getDeltaTime());
        //    //this.setTexture();
        //}
        //if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
        //    this.changeY(-1200 * Gdx.graphics.getDeltaTime());
        //    //this.setTexture();
        //}
    }

}
