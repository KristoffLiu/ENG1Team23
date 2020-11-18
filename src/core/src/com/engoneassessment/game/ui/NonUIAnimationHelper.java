package com.engoneassessment.game.ui;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.utils.Null;

public class NonUIAnimationHelper {

    Actor actor;

    public NonUIAnimationHelper(Actor actor){
        this.actor = actor;
    }

    public void hide(Actor actor){
        AlphaAction uiElementAlphaAction = Actions.alpha(0f,0f);
        VisibleAction uiElementVisibleAction = Actions.visible(false);
        actor.addAction(uiElementAlphaAction);
        actor.addAction(uiElementVisibleAction);
    }

    public void hide(Actor actor, float duration){
        AlphaAction uiElementAlphaAction = Actions.alpha(0,duration);
        VisibleAction uiElementVisibleAction = Actions.visible(false);
        DelayAction uiElementDelayAction = Actions.delay(duration,uiElementVisibleAction);
        actor.addAction(uiElementAlphaAction);
        actor.addAction(uiElementDelayAction);
    }

    public void appear(Actor actor){
        VisibleAction uiElementVisibleAction = Actions.visible(true);
        AlphaAction uiElementAlphaAction = Actions.alpha(1f,0f);
        actor.addAction(uiElementVisibleAction);
        actor.addAction(uiElementAlphaAction);
    }

    public void appear(Actor actor, float duration){
        VisibleAction uiElementVisibleAction = Actions.visible(true);
        AlphaAction uiElementAlphaAction = Actions.alpha(1f,duration);
        actor.addAction(uiElementVisibleAction);
        actor.addAction(uiElementAlphaAction);
    }

    float last_x = -999;
    float last_y = -999;

    public void fadeOut(float duration){
        fadeOut(0f, 0f, duration, null);
    }

    public void fadeOut(float value, boolean isHorizontalShift, float duration){
        if(isHorizontalShift) fadeOut(value, 0f, duration, null); else fadeOut(0f, value, duration, null);

    }

    public void fadeOut(float x, float y, float duration){
        fadeOut(x, y, duration, null);
    }



    public void fadeOut(float x, float y, float duration, @Null Interpolation interpolation){
        if(actor.isVisible()){

        }
        last_x = actor.getX();
        last_y = actor.getY();
        AlphaAction uiElementAlphaAction = Actions.alpha(0f, duration, interpolation);
        MoveByAction uiElementMoveByAction = Actions.moveBy(x, y, duration, interpolation);
        //VisibleAction uiElementVisibleAction = Actions.visible(false);
        //DelayAction uiElementDelayAction = Actions.delay(duration,uiElementVisibleAction);
        actor.addAction(uiElementAlphaAction);
        actor.addAction(uiElementMoveByAction);
        //this.addAction(uiElementDelayAction);
    }

    public void fadeIn(float duration){
        fadeIn(0f, 0f, duration, null);
    }

    public void fadeIn(float value, boolean isHorizontalShift, float duration){
        if(isHorizontalShift) fadeIn(value, 0f, duration, null); else fadeIn(0f, value, duration, null);

    }

    public void fadeIn(float x, float y, float duration){
        fadeIn(x, y, duration, null);
    }

    public void fadeIn(float x, float y, float duration, @Null Interpolation interpolation){
        if(!actor.isVisible()){

        }
        actor.setVisible(true);
        float start_x;
        float start_y;
        if(last_x == -999){
            start_x = actor.getX() - x;
            start_y = actor.getY() - y;
        }
        else{
            start_x = last_x - x;
            start_y = last_y - y;
        }
        //VisibleAction uiElementVisibleAction = Actions.visible(true);
        //this.addAction(uiElementVisibleAction);
        actor.setX(start_x);
        actor.setY(start_y);
        AlphaAction uiElementAlphaAction = Actions.alpha(1f, duration, interpolation);
        MoveByAction uiElementMoveByAction = Actions.moveBy(x, y, duration, interpolation);
        actor.addAction(uiElementAlphaAction);
        actor.addAction(uiElementMoveByAction);
    }
}
