package com.engoneassessment.game.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.actors.CustomActor;

public class UIElement extends CustomActor implements IUIElement{
    UIStage uiStage;
    HorizontalAlignment horizontalAlignment;
    VerticalAlignment verticalAlignment;
    float relativeX;
    float relativeY;

    public UIElement(TextureRegion textureRegion) {
        super(textureRegion);
    }

    public void setStage(UIStage _uiStage){
        this.uiStage = _uiStage;
    }

    public enum Visibility{
        visible, collapsed
    }

    public void setRelativeX(float relativeX){
        float offset = 0f;
        switch (this.horizontalAlignment){
            case leftAlignment:
                this.setX(offset + relativeX);
                break;
            case centreAlignment:
                offset = this.uiStage.getWidth() / 2 - this.getWidth() / 2;
                this.setX(offset + relativeX);
                break;
            case rightAlignment:
                offset = this.uiStage.getWidth();
                this.setX(offset - relativeX);
                break;
        }
    }

    public void setRelativeY(float relativeY){
        float offset = 0f;
        switch (this.verticalAlignment){
            case topAlignment:
                offset = this.uiStage.getHeight();
                this.setY(offset - relativeY);
                break;
            case centreAlignment:
                offset = this.uiStage.getHeight() / 2 - this.getWidth() / 2;
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
}
