package com.engoneassessment.game.ui.controls;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.ui.UIElement;

public class Button extends ClickableUIElement {

    public Button(TextureRegion normalTexture) {
        super(normalTexture, normalTexture, normalTexture);
    }
}
