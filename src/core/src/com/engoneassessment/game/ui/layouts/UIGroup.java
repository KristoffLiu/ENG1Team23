package com.engoneassessment.game.ui.layouts;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
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
}
