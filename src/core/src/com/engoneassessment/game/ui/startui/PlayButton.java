package com.engoneassessment.game.ui.startui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.ui.UIStage;
import com.engoneassessment.game.ui.controls.Button;

public class PlayButton extends Button {

    /**
     * Constructor of a MapButton
     * the texture input will be its mapping texture.
     *
     * @param parentUIStage
     */
    public PlayButton(UIStage parentUIStage) {
        super(  parentUIStage,
                new TextureRegion(
                        new Texture("Menu/Buttons/playNormal.jpg")),
                new TextureRegion(
                        new Texture("Menu/Buttons/playHovered.jpg")),
                new TextureRegion(
                        new Texture("Menu/Buttons/playPressed.jpg")),
                null);
    }
}
