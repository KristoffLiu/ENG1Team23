package com.engoneassessment.game.ui.controls;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface IClickableUIElement {
    //Switches the texture of the button to be normal state, hovered, or pressed.
    public void setButtonUIState(com.engoneassessment.game.ui.controls.Button.ButtonUIState buttonUIStateInput);

}
