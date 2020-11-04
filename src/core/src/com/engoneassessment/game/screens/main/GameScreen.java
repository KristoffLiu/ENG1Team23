package com.engoneassessment.game.screens.main;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.engoneassessment.game.actors.CustomActor;

public class GameScreen implements Screen {

    public Stage stage;

    /**
     * Called when this screen becomes the current screen for a Game.
     */
    @Override
    public void show() {

    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {

    }

    /**
     * @param width the width you want to resize with.
     * @param height the height you want to resize with.
     * @link ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     * @link ApplicationListener#pause()
     */
    @Override
    public void pause() {

    }

    /**
     * @link ApplicationListener#resume()
     */
    @Override
    public void resume() {

    }

    /**
     * Called when this screen is no longer the current screen for a Game.
     */
    @Override
    public void hide() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }

    public void addActor(CustomActor customActor){
        stage.addActor(customActor);
    }

    public boolean isCollision(CustomActor customActor){
        for(Actor actor : stage.getActors()){
            if(((CustomActor)actor).getBounds().overlaps(customActor.getBounds())){
                return true;
            }
        }
        return false;
    }

    public boolean isCollidedWithAnotherActor(CustomActor customActor){
        for(Actor actor : stage.getActors()){
            if(((CustomActor)actor).getBounds().overlaps(customActor.getBounds())){
                return true;
            }
        }
        return false;
    }

    public void CollisionHandler(){

    }
}
