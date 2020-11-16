package com.engoneassessment.game.ui.controls;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.engoneassessment.game.ui.UIElement;
import com.engoneassessment.game.ui.UIStage;

/**
 * A button class which extends from ClickableUIElement class
 */
public class Button extends ClickableUIElement implements IButton{

    /**
     * Constructor of a Button
     * in this case, the texture parameter will be normalTexture, hoveringTexture, pressedTexture at the same time.
     *
     * @param parentUIStage
     * @param texture
     */
    public Button(UIStage parentUIStage, TextureRegion texture) {
        super(parentUIStage, texture, texture, texture, texture);
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
    public Button(UIStage parentUIStage,
                  TextureRegion normalTexture,
                  TextureRegion pressedTexture) {
        super(parentUIStage, normalTexture, pressedTexture,
                pressedTexture, null);
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
    public Button(UIStage parentUIStage,
                  TextureRegion normalTexture, TextureRegion hoveringTexture,
                  TextureRegion pressedTexture, TextureRegion notActivatedTexture) {
        super(parentUIStage, normalTexture, hoveringTexture, pressedTexture, notActivatedTexture);
    }


}
