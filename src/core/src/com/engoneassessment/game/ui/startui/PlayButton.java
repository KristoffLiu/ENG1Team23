package com.engoneassessment.game.ui.startui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.ui.UIStage;
import com.engoneassessment.game.ui.controls.Button;

public class PlayButton extends Button {


    /**
     * Constructor of a Button
     * in this case, the texture parameter will be normalTexture, hoveringTexture, pressedTexture at the same time.
     *
     * @param parentUIStage
     * @param texture
     */
    public PlayButton(UIStage parentUIStage, TextureRegion texture) {
        super(parentUIStage, texture, texture, texture);
    }

    /**
     * Constructor of a Button
     * in this case, the texture parameter will be normalTexture, hoveringTexture, pressedTexture at the same time.
     * the pressed parameter will be normalTexture, hoveringTexture, pressedTexture at the same time.
     *
     * @param parentUIStage
     * @param normalTexture
     * @param pressedTexture
     */
    public PlayButton(UIStage parentUIStage, TextureRegion normalTexture,
                  TextureRegion pressedTexture) {
        super(parentUIStage, normalTexture, normalTexture, pressedTexture);
    }

    /**
     * Constructor of a Button
     * the texture input will be its mapping texture.
     *
     * @param parentUIStage
     * @param normalTexture
     * @param hoveringTexture
     * @param pressedTexture
     */
    public PlayButton(UIStage parentUIStage, TextureRegion normalTexture, TextureRegion hoveringTexture,
                  TextureRegion pressedTexture) {
        super(parentUIStage, normalTexture, hoveringTexture, pressedTexture);
    }
}
