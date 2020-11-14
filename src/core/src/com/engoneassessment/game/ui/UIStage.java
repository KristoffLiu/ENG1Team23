package com.engoneassessment.game.ui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class UIStage extends Stage implements IUIStage{

    public UIStage(Viewport viewport) {
        super(viewport);
    }

    public void addUIElement(UIElement uiElement) {
        super.addActor(uiElement);
        uiElement.setStage(this);
    }
}
