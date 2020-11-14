package com.engoneassessment.game.ui;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class UIStage extends Stage {

    public void addUIElement(UIElement uiElement) {
        super.addActor(uiElement);
        uiElement.setStage(this);
    }
}
