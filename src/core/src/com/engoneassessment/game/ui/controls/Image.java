package com.engoneassessment.game.ui.controls;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.ui.UIElement;
import com.engoneassessment.game.ui.UIStage;

public class Image extends UIElement {

    public Image(TextureRegion textureRegion) {
        super(textureRegion);
    }

    public Image(UIStage stage, TextureRegion textureRegion) {
        super(stage, textureRegion);
    }
}
