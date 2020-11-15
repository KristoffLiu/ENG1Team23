package com.engoneassessment.game.actors.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.utils.Array;
import com.engoneassessment.game.actors.CustomActor;
import com.engoneassessment.game.screens.RoomScreen;

public class Character extends CustomActor implements ICharacter {
    public enum FacingDirection{
        LEFT, RIGHT, UP, DOWN
    }

    public enum MovementState{
        IDLE, Walking, Running, Teleporting
    }

    Array<Texture> actionTextures;

    private float speed;
    private String ability; // every hostile has a ability, abilities such as be invisible, speeding up...

    private Double health = 100.0;
    private String position; // variable type has to be changed to what we define later
    private FacingDirection direction = FacingDirection.DOWN;
    public MovementState movementState = MovementState.IDLE;
    private CharacterCollisionDetector collisionDetector = new CharacterCollisionDetector();
    public Animation runAnimation;
    public Animation idleAnimation;
    public Animation currentAnimation;
    public float elapsedTime;
    public Texture runTexture;
    public Texture idleTexture;
    private RoomScreen currentScreen;

    public Character(TextureRegion textureRegion, RoomScreen screen, Texture runTexture, Texture idleTexture) {
        super(textureRegion);
        currentScreen = screen;
        speed = (float)0.8;
        
        this.runTexture = runTexture;
        this.idleTexture = idleTexture;

        // Animation things
        runAnimation = this.makeAnimation(runTexture, 1f/12f, 56,90);
        idleAnimation = this.makeAnimation(idleTexture, 1f/12f, 56,90);
    }

    public void changeX(int change){
    }

    public Animation makeAnimation(Texture img, float frameDuration, int tileWidth, int tileHeight){
        TextureRegion[][] tmpFrames = TextureRegion.split(img, tileWidth, tileHeight);

        int length = 0;
        for ( int i = 0; i < tmpFrames.length ; i ++){
            length += tmpFrames[i].length;
        }

        TextureRegion[] animationFrames = new TextureRegion[length];

        int index = 0;
        for (int i = 0; i < tmpFrames.length; i++) {
            for (int j = 0; j < tmpFrames[i].length; j++) {
                animationFrames[index++] = tmpFrames[i][j];
            }
        }

        return new Animation(frameDuration,(Object[]) animationFrames);
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

        switch (movementState) {
            case IDLE:
                currentAnimation = idleAnimation;
            case Running:
                currentAnimation = runAnimation;
        }

    }

    public void MoveByAction() {
        MoveByAction action = Actions.moveBy(100, 0, 2.0F);
        this.addAction(action);
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

    public void setSpeed(float s) {
        speed = s;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        elapsedTime += Gdx.graphics.getDeltaTime();
        batch.draw(
                (TextureRegion) currentAnimation.getKeyFrame(elapsedTime, true),
                getX(), getY()
        );
    }

    public float getSpeed() {
        return speed;
    }

    public RoomScreen getCurrentScreen(){
        return currentScreen;
    }

    public void setCurrentScreen(RoomScreen currentScreen) {
        this.currentScreen = currentScreen;
    }
}
