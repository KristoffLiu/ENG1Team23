package com.engoneassessment.game.ui.controls;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.ui.UIElement;

/**
 * A button class which extends from ClickableUIElement class
 */
public class Button extends ClickableUIElement {

    /**
     * Constructor of a Button
     * in this case, the texture parameter will be normalTexture, hoveringTexture, pressedTexture at the same time.
     * @param texture
     */
    public Button(TextureRegion texture) {
        super(texture, texture, texture);
    }

    /**
     * Constructor of a Button
     * in this case, the texture parameter will be normalTexture, hoveringTexture, pressedTexture at the same time.
     * the pressed parameter will be normalTexture, hoveringTexture, pressedTexture at the same time.
     * @param normalTexture
     * @param pressedTexture
     */
    public Button(TextureRegion normalTexture,
                  TextureRegion pressedTexture) {
        super(normalTexture, normalTexture, pressedTexture);
    }

    /**
     * Constructor of a Button
     * @param normalTexture
     * @param hoveringTexture
     * @param pressedTexture
     */
    public Button(TextureRegion normalTexture, TextureRegion hoveringTexture,
                  TextureRegion pressedTexture) {
        super(normalTexture, hoveringTexture, pressedTexture);
    }
}
