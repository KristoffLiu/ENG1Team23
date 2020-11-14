package com.engoneassessment.game.ui.hud;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.ui.controls.Button;

public class TeleportButton extends Button {

    /**
     * Constructor of a TeleportButton
     * in this case, the texture parameter will be normalTexture, hoveringTexture, pressedTexture at the same time.
     *
     * @param texture
     */
    public TeleportButton(TextureRegion texture) {
        super(texture);
    }

    /**
     * Constructor of a TeleportButton
     * in this case, the texture parameter will be normalTexture, hoveringTexture, pressedTexture at the same time.
     * the pressed parameter will be normalTexture, hoveringTexture, pressedTexture at the same time.
     *
     * @param normalTexture
     * @param pressedTexture
     */
    public TeleportButton(TextureRegion normalTexture, TextureRegion pressedTexture) {
        super(normalTexture, pressedTexture);
    }

    /**
     * Constructor of a TeleportButton
     * the texture input will be its mapping texture.
     * @param normalTexture
     * @param hoveringTexture
     * @param pressedTexture
     */
    public TeleportButton(TextureRegion normalTexture, TextureRegion hoveringTexture, TextureRegion pressedTexture) {
        super(normalTexture, hoveringTexture, pressedTexture);
    }
}
