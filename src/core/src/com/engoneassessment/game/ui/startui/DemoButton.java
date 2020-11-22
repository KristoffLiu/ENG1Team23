package com.engoneassessment.game.ui.startui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.ui.UIStage;
import com.engoneassessment.game.ui.controls.Button;

public class DemoButton extends Button {

    public DemoButton(UIStage parentUIStage) {
        super(  parentUIStage,
                new TextureRegion(
                        new Texture("Menu/Buttons/demoNormal.jpg")),
                new TextureRegion(
                        new Texture("Menu/Buttons/demoHovered.jpg")),
                new TextureRegion(
                        new Texture("Menu/Buttons/demoPressed.jpg")),
                null);
    }
}
