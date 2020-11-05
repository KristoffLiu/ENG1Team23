package com.engoneassessment.game.io;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ClickEventListener extends ClickListener {
    /**
     * used when actor/stage is clicked
     *
     * @param x
     * 		x coordination of click released, relative to the left-bottom of the clicked object.
     *
     * @param y
     * 		y coordination of click released, relative to the left-bottom of the clicked object.
     */
    @Override
    public void clicked(InputEvent event, float x, float y) {
        // get the clicked Actor
        //Actor actor = event.getListenerActor();


        //Gdx.app.log(TAG, "isclicked: " + x + ", " + y + "; Actor: " + actor.getClass().getSimpleName());
    }
}
