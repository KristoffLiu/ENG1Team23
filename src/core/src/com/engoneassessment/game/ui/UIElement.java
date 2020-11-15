package com.engoneassessment.game.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.actors.CustomActor;

public class UIElement extends CustomActor implements IUIElement{
    UIStage parentStage = null;
    UIElement.HorizontalAlignment horizontalAlignment = UIElement.HorizontalAlignment.leftAlignment;
    UIElement.VerticalAlignment verticalAlignment = UIElement.VerticalAlignment.bottomAlignment;
    float relativeX = 0;
    float relativeY = 0;


    public UIElement(TextureRegion textureRegion) {
        super(textureRegion);
    }

    public UIElement(UIStage stage, TextureRegion textureRegion) {
        super(textureRegion);
        setParentStage(stage);
        stage.addUIElement(this);
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
        else{
            setX(relativeX);
        }
        this.relativeX = relativeX;
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
        else{
            setY(relativeY);
        }
        this.relativeY = relativeY;
    }

    @Override
    public HorizontalAlignment getHorizontalAlignment() {
        return horizontalAlignment;
    }

    @Override
    public VerticalAlignment getVerticalAlignment() {
        return verticalAlignment;
    }

    public void setHorizontalAlignment(HorizontalAlignment alignment){
        horizontalAlignment = alignment;
        setRelativeX(relativeX);
    }

    public void setVerticalAlignment(VerticalAlignment alignment){
        verticalAlignment = alignment;
        setRelativeY(relativeY);
    }

    @Override
    public void setRelativePosition(float relativeX, float relativeY, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
        this.relativeX = relativeX;
        this.relativeY = relativeY;
        setHorizontalAlignment(horizontalAlignment);
        setVerticalAlignment(verticalAlignment);
    }

    public void setSize(float width, float height){
        this.setWidth(width);
        this.setHeight(height);
    }


    public enum HorizontalAlignment{
        leftAlignment, centreAlignment, rightAlignment
    }

    public enum VerticalAlignment{
        topAlignment, centreAlignment, bottomAlignment
    }

    public enum Visibility{
        visible, collapsed
    }
}
