package com.engoneassessment.game.ui.layouts;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.engoneassessment.game.ui.IUIElement;
import com.engoneassessment.game.ui.UIElement;
import com.engoneassessment.game.ui.UIStage;

public class UIGroup extends Group implements IUIElement {

    UIStage parentStage = null;
    UIGroup parentGroup = null;
    UIElement.HorizontalAlignment horizontalAlignment = UIElement.HorizontalAlignment.leftAlignment;
    UIElement.VerticalAlignment verticalAlignment = UIElement.VerticalAlignment.bottomAlignment;
    float relativeX = 0;
    float relativeY = 0;


    public UIGroup(TextureRegion textureRegion) {

    }

    public UIGroup(UIStage stage, TextureRegion textureRegion) {
        setParentStage(stage);
        stage.addUIGroup(this);
    }

    public void setParentStage(UIStage _uiStage){
        this.parentStage = _uiStage;
    }

    public UIStage getParentStage() {
        return parentStage;
    }

    @Override
    public float getRelativeX() {
        return relativeX;
    }

    @Override
    public float getRelativeY() {
        return relativeY;
    }

    public void setRelativeX(float relativeX){
        if(parentStage != null){
            float offset = 0f;
            switch (this.horizontalAlignment){
                case leftAlignment:
                    this.setX(offset + relativeX);
                    break;
                case centreAlignment:
                    offset = this.parentStage.getWidth() / 2 - this.getWidth() / 2;
                    this.setX(offset + relativeX);
                    break;
                case rightAlignment:
                    offset = this.parentStage.getWidth() - this.getWidth();
                    this.setX(offset - relativeX);
                    break;
            }
        }
    }

    public void setRelativeY(float relativeY){
        if(parentStage != null) {
            float offset = 0f;
            switch (this.verticalAlignment) {
                case topAlignment:
                    offset = this.parentStage.getHeight() - this.getHeight();
                    this.setY(offset - relativeY);
                    break;
                case centreAlignment:
                    offset = this.parentStage.getHeight() / 2 - this.getHeight() / 2;
                    this.setY(offset + relativeY);
                    break;
                case bottomAlignment:
                    this.setY(offset + relativeY);
                    break;
            }
        }
    }

    @Override
    public UIElement.HorizontalAlignment getHorizontalAlignment() {
        return horizontalAlignment;
    }

    @Override
    public UIElement.VerticalAlignment getVerticalAlignment() {
        return verticalAlignment;
    }

    public void setHorizontalAlignment(UIElement.HorizontalAlignment alignment){
        horizontalAlignment = alignment;
        setRelativeX(relativeX);
    }

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
}
