package com.engoneassessment.game.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.actors.CustomActor;

public class UIElement extends CustomActor implements IUIElement{
    UIStage parentStage;
    HorizontalAlignment horizontalAlignment;
    VerticalAlignment verticalAlignment;
    float relativeX;
    float relativeY;

    public UIElement(TextureRegion textureRegion) {
        super(textureRegion);
    }

    public void setParentStage(UIStage _uiStage){
        this.parentStage = _uiStage;
    }

    public UIStage getParentStage() {
        return parentStage;
    }

    public void setRelativeX(float relativeX){
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
                offset = this.parentStage.getWidth();
                this.setX(offset - relativeX);
                break;
        }
    }

    public void setRelativeY(float relativeY){
        float offset = 0f;
        switch (this.verticalAlignment){
            case topAlignment:
                offset = this.parentStage.getHeight();
                this.setY(offset - relativeY);
                break;
            case centreAlignment:
                offset = this.parentStage.getHeight() / 2 - this.getWidth() / 2;
                this.setY(offset + relativeY);
                break;
            case bottomAlignment:
                this.setY(offset + relativeY);
                break;
        }
    }

    public void setHorizontalAlignment(HorizontalAlignment alignment){
        horizontalAlignment = alignment;
        setX(this.relativeX);
    }

    public void setVerticalAlignment(VerticalAlignment alignment){
        verticalAlignment = alignment;
        setY(this.relativeY);
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
