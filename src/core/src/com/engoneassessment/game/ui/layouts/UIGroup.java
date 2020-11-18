package com.engoneassessment.game.ui.layouts;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.engoneassessment.game.ui.IUIElement;
import com.engoneassessment.game.ui.IUIStage;
import com.engoneassessment.game.ui.UIElement;
import com.engoneassessment.game.ui.UIStage;

public class UIGroup extends Group implements IUIElement, IUIStage {
    Object uiParent = null;
    Array<Actor> uiElements = new Array<Actor>();
    UIElement.HorizontalAlignment horizontalAlignment = UIElement.HorizontalAlignment.leftAlignment;
    UIElement.VerticalAlignment verticalAlignment = UIElement.VerticalAlignment.bottomAlignment;
    float relativeX = 0;
    float relativeY = 0;

    public UIGroup(Object parent) {
        if(parent instanceof UIStage){
            UIStage _parent = (UIStage) parent;
            _parent.addUIElement(this);
            setUIParent(parent);
        }
        else if(parent instanceof UIGroup){
            UIGroup _parent = (UIGroup) parent;
            _parent.addActor(this);
            setUIParent(parent);
        }
    }

    public void setUIParent(Object _uiParent){
        this.uiParent = _uiParent;
    }

    public Object getUIParent() {
        return uiParent;
    }

    @Override
    public float getRelativeX() {
        return relativeX;
    }

    @Override
    public float getRelativeY() {
        return relativeY;
    }

    @Override
    public void setRelativeX(float relativeX){
        if(uiParent != null){
            float offset = 0f;
            if(uiParent instanceof UIStage){
                UIStage _parent = (UIStage) uiParent;
                switch (this.horizontalAlignment){
                    case leftAlignment:
                        this.setX(offset + relativeX);
                        break;
                    case centreAlignment:
                        offset = _parent.getWidth() / 2 - this.getWidth() / 2;
                        this.setX(offset + relativeX);
                        break;
                    case rightAlignment:
                        offset = _parent.getWidth() - this.getWidth();
                        this.setX(offset - relativeX);
                        break;
                }
            }
            else if(uiParent instanceof UIGroup){
                UIGroup _parent = (UIGroup) uiParent;
                switch (this.horizontalAlignment){
                    case leftAlignment:
                        this.setX(offset + relativeX);
                        break;
                    case centreAlignment:
                        offset = _parent.getWidth() / 2 - this.getWidth() / 2;
                        this.setX(offset + relativeX);
                        break;
                    case rightAlignment:
                        offset = _parent.getWidth() - this.getWidth();
                        this.setX(offset - relativeX);
                        break;
                }
            }
        }
        else{
            setX(relativeX);
        }
        this.relativeX = relativeX;
    }

    @Override
    public void setRelativeY(float relativeY){
        if(uiParent != null){
            float offset = 0f;
            if(uiParent instanceof UIStage){
                UIStage _parent = (UIStage) uiParent;
                switch (this.verticalAlignment) {
                    case topAlignment:
                        offset = _parent.getHeight() - this.getHeight();
                        this.setY(offset - relativeY);
                        break;
                    case centreAlignment:
                        offset = _parent.getHeight() / 2 - this.getHeight() / 2;
                        this.setY(offset + relativeY);
                        break;
                    case bottomAlignment:
                        this.setY(offset + relativeY);
                        break;
                }
            }
            else if(uiParent instanceof UIGroup){
                UIGroup _parent = (UIGroup) uiParent;
                switch (this.verticalAlignment) {
                    case topAlignment:
                        offset = _parent.getHeight() - this.getHeight();
                        this.setY(offset - relativeY);
                        break;
                    case centreAlignment:
                        offset = _parent.getHeight() / 2 - this.getHeight() / 2;
                        this.setY(offset + relativeY);
                        break;
                    case bottomAlignment:
                        this.setY(offset + relativeY);
                        break;
                }
            }
        }
        else{
            setY(relativeY);
        }
        this.relativeY = relativeY;
    }

    @Override
    public UIElement.HorizontalAlignment getHorizontalAlignment() {
        return horizontalAlignment;
    }

    @Override
    public UIElement.VerticalAlignment getVerticalAlignment() {
        return verticalAlignment;
    }

    @Override
    public void setHorizontalAlignment(UIElement.HorizontalAlignment alignment){
        horizontalAlignment = alignment;
        setRelativeX(relativeX);
    }

    @Override
    public void setVerticalAlignment(UIElement.VerticalAlignment alignment){
        verticalAlignment = alignment;
        setRelativeY(relativeY);
    }

    @Override
    public void setRelativePosition(float relativeX, float relativeY, UIElement.HorizontalAlignment horizontalAlignment, UIElement.VerticalAlignment verticalAlignment) {
        this.relativeX = relativeX;
        this.relativeY = relativeY;
        setHorizontalAlignment(horizontalAlignment);
        setVerticalAlignment(verticalAlignment);
    }

    @Override
    public void addUIElement(Actor uiElement) {
        super.addActor(uiElement);
        this.uiElements.add(uiElement);
    }

    @Override
    public void removeUIElement(UIElement uiElement) {

    }

    @Override
    public Array<Actor> getUIElementsAll() {
        return uiElements;
    }

    public void hide(){
        AlphaAction uiElementAlphaAction = Actions.alpha(0f,0f);
        VisibleAction uiElementVisibleAction = Actions.visible(false);
        this.addAction(uiElementAlphaAction);
        this.addAction(uiElementVisibleAction);
    }

    public void hide(float duration){
        AlphaAction uiElementAlphaAction = Actions.alpha(0,duration);
        VisibleAction uiElementVisibleAction = Actions.visible(false);
        DelayAction uiElementDelayAction = Actions.delay(duration,uiElementVisibleAction);
        this.addAction(uiElementAlphaAction);
        this.addAction(uiElementDelayAction);
    }

    public void appear(){
        VisibleAction uiElementVisibleAction = Actions.visible(true);
        AlphaAction uiElementAlphaAction = Actions.alpha(1f,0f);
        this.addAction(uiElementVisibleAction);
        this.addAction(uiElementAlphaAction);
    }

    public void appear(float duration){
        VisibleAction uiElementVisibleAction = Actions.visible(true);
        AlphaAction uiElementAlphaAction = Actions.alpha(1f,duration);
        this.addAction(uiElementVisibleAction);
        this.addAction(uiElementAlphaAction);
    }

    public void fadeOut(float duration){
        fadeOut(0f, 0f, duration, null);
    }

    public void fadeOut(float value, boolean isHorizontalShift, float duration){
        if(isHorizontalShift) fadeOut(value, 0f, duration, null); else fadeOut(0f, value, duration, null);

    }

    public void fadeOut(float x, float y, float duration){
        fadeOut(x, y, duration, null);
    }

    float last_x = -999;
    float last_y = -999;

    public void fadeOut(float x, float y, float duration, @Null Interpolation interpolation){
        if(this.isVisible()){

        }
        last_x = this.getX();
        last_y = this.getY();
        AlphaAction uiElementAlphaAction = Actions.alpha(0f, duration, interpolation);
        MoveByAction uiElementMoveByAction = Actions.moveBy(x, y, duration, interpolation);
        //VisibleAction uiElementVisibleAction = Actions.visible(false);
        //DelayAction uiElementDelayAction = Actions.delay(duration,uiElementVisibleAction);
        this.addAction(uiElementAlphaAction);
        this.addAction(uiElementMoveByAction);
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
        if(!this.isVisible()){

        }
        float start_x;
        float start_y;
        if(last_x == -999){
            start_x = this.getX() - x;
            start_y = this.getY() - y;
        }
        else{
            start_x = last_x - x;
            start_y = last_y - y;
        }
        //VisibleAction uiElementVisibleAction = Actions.visible(true);
        //this.addAction(uiElementVisibleAction);
        this.setX(start_x);
        this.setY(start_y);
        AlphaAction uiElementAlphaAction = Actions.alpha(1f, duration, interpolation);
        MoveByAction uiElementMoveByAction = Actions.moveBy(x, y, duration, interpolation);
        this.addAction(uiElementAlphaAction);
        this.addAction(uiElementMoveByAction);
    }
}
