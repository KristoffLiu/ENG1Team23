package com.engoneassessment.game.ui.startui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.ui.UIStage;
import com.engoneassessment.game.ui.controls.Button;

public class QuitButton extends Button {

    /**
     * Constructor of a MapButton
     * the texture input will be its mapping texture.
     *
     * @param parentUIStage
     */
    public QuitButton(UIStage parentUIStage) {
        super(  parentUIStage,
                new TextureRegion(
                        new Texture("Menu/Buttons/quitNormal.jpg")),
                new TextureRegion(
                        new Texture("Menu/Buttons/quitHovered.jpg")),
                new TextureRegion(
                        new Texture("Menu/Buttons/quitPressed.jpg")),
                null);
    }
}
