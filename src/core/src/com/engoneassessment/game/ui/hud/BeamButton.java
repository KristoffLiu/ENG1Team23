package com.engoneassessment.game.ui.hud;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.ui.UIStage;
import com.engoneassessment.game.ui.controls.Button;

public class BeamButton extends Button {

    /**
     * Constructor of a BeamButton
     * in this case, the texture parameter will be normalTexture, hoveringTexture, pressedTexture at the same time.
     *
     * @param parentUIStage
     * @param texture
     */
    public BeamButton(UIStage parentUIStage, TextureRegion texture) {
        super(parentUIStage, texture);
    }

    /**
     * Constructor of a BeamButton
     * in this case, the texture parameter will be normalTexture, hoveringTexture, pressedTexture at the same time.
     * the pressed parameter will be normalTexture, hoveringTexture, pressedTexture at the same time.
     *
     * @param parentUIStage
     * @param normalTexture
     * @param pressedTexture
     */
    public BeamButton(UIStage parentUIStage, TextureRegion normalTexture, TextureRegion pressedTexture) {
        super(parentUIStage, normalTexture, pressedTexture);
    }

    /**
     * Constructor of a BeamButton
     * the texture input will be its mapping texture.
     *
     * @param parentUIStage
     * @param normalTexture
     * @param hoveringTexture
     * @param pressedTexture
     */
    public BeamButton(UIStage parentUIStage, TextureRegion normalTexture, TextureRegion hoveringTexture, TextureRegion pressedTexture) {
        super(parentUIStage, normalTexture, hoveringTexture, pressedTexture);
    }
}
