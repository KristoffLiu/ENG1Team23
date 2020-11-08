package com.engoneassessment.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.utils.Array;
import com.engoneassessment.game.actors.CustomActor;
import com.engoneassessment.game.actors.charactors.CharacterCollisionDetector;
import com.engoneassessment.game.actors.charactors.ICharacter;

public class Character extends CustomActor implements ICharacter {
    public enum FacingDirection{
        LEFT, RIGHT, UP, DOWN
    }

    public enum MovementState{
        IDLE, Walking, Running, Teleporting
    }

    Array<Texture> actionTextures;

    private int speed; // i set the speed as integer initially, because using double may be more complicated
    private String ability; // every hostile has a ability, abilities such as be invisible, speeding up...

    private Double health = 100.0;
    private String position; // variable type has to be changed to what we define later
    private FacingDirection direction = FacingDirection.DOWN;
    private MovementState movementState = MovementState.IDLE;
    private CharacterCollisionDetector collisionDetector = new CharacterCollisionDetector();


    public Character(TextureRegion textureRegion) {
        super(textureRegion);
    }


    public void changeX(int change){

    }




    /**
     * logic handler of the actor
     * we need handle the collision detection here.
     *
     * @param delta
     *		the change of time from the last rendered frame to the current rendering frame,
     *	    or we call it the rendering time step / time difference.
     *	    the unite is second.
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        //you can put actions of the character here.
        if(movementState == MovementState.Walking || movementState == MovementState.Running)
        {
            if(collisionDetector.checkCollision(this.getBounds(),direction)){
                collisionDetector.handleCollisionIssue(this,this.direction);
            }
        }
    }

    public void MoveByAction(){
        MoveByAction action = Actions.moveBy(100, 0, 2.0F);
        this.addAction(action);
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
}
