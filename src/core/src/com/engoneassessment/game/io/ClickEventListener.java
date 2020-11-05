package com.engoneassessment.game.io;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ClickEventListener extends ClickListener {
    /**
     * 对象（演员/舞台）被点击时调用
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

        //Gdx.app.log(TAG, "被点击: " + x + ", " + y + "; Actor: " + actor.getClass().getSimpleName());
    }
}
