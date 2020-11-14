package com.engoneassessment.game.ui.startui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.ui.controls.Button;

public class PlayButton extends Button {

    /**
     * Constructor of a PlayButton
     * in this case, the texture parameter will be normalTexture, hoveringTexture, pressedTexture at the same time.
     *
     * @param texture
     */
    public PlayButton(TextureRegion texture) {
        super(texture);
    }

    /**
     * Constructor of a PlayButton
     * in this case, the texture parameter will be normalTexture, hoveringTexture, pressedTexture at the same time.
     * the pressed parameter will be normalTexture, hoveringTexture, pressedTexture at the same time.
     *
     * @param normalTexture
     * @param pressedTexture
     */
    public PlayButton(TextureRegion normalTexture, TextureRegion pressedTexture) {
        super(normalTexture, pressedTexture);
    }

    /**
     * Constructor of a PlayButton
     * the texture input will be its mapping texture.
     *
     * @param normalTexture
     * @param hoveringTexture
     * @param pressedTexture
     */
    public PlayButton(TextureRegion normalTexture, TextureRegion hoveringTexture, TextureRegion pressedTexture) {
        super(normalTexture, hoveringTexture, pressedTexture);
    }
}
