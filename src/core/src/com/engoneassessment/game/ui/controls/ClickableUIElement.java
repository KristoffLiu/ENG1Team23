package com.engoneassessment.game.ui.controls;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.engoneassessment.game.ui.UIElement;
import com.engoneassessment.game.ui.UIStage;

public class ClickableUIElement extends UIElement {
    TextureRegion normalTexture         ;
    TextureRegion hoveredTexture        ;
    TextureRegion pressedTexture        ;
    TextureRegion notActivatedTexture   ;
    boolean isEnabled;

    ClickableUIElementClickListener clickableUIElementClickListener;
    ButtonUIState buttonUIState = com.engoneassessment.game.ui.controls.Button.ButtonUIState.normal;

    public ClickableUIElement(Object uiParent,
                              TextureRegion normalTexture, TextureRegion hoveredTexture,
                              TextureRegion pressedTexture, TextureRegion notActivatedTexture) {
        super(uiParent, normalTexture);

        this.normalTexture = normalTexture;
        this.hoveredTexture = hoveredTexture;
        this.pressedTexture = pressedTexture;
        this.notActivatedTexture = notActivatedTexture;
        isEnabled = true;

        clickableUIElementClickListener = new ClickableUIElementClickListener();
        this.addListener(clickableUIElementClickListener);
    }

    public void setClickListener(ClickableUIElementClickListener clickListener){
        this.removeListener(clickableUIElementClickListener);
        clickableUIElementClickListener = clickListener;
        this.addListener(clickableUIElementClickListener);
    }

    public void isEnabled(boolean isEnabled){
        this.isEnabled = isEnabled;
        if(isEnabled){
            setButtonUIState(ButtonUIState.normal);
        }
        else {
            setButtonUIState(ButtonUIState.notActivated);
        }
    }

    //Switches the texture of the button to be highlighted or not highlighted
    public void setButtonUIState(com.engoneassessment.game.ui.controls.Button.ButtonUIState buttonUIStateInput){
        if(buttonUIStateInput != this.buttonUIState){
            float previousWidth  = this.getWidth();
            float previousHeight = this.getHeight();
            switch (buttonUIStateInput){
                case normal:
                    this.setTextureRegion(normalTexture);
                    break;
                case hovered:
                    this.setTextureRegion(hoveredTexture);
                    break;
                case pressed:
                    this.setTextureRegion(pressedTexture);
                    break;
                case notActivated:
                    this.setTextureRegion(notActivatedTexture);
                    break;
            }
            this.buttonUIState = buttonUIStateInput;
            this.setWidth(previousWidth);
            this.setHeight(previousHeight);
        }
    }

    public enum ButtonUIState{
        normal, hovered, pressed, notActivated
    }
}

