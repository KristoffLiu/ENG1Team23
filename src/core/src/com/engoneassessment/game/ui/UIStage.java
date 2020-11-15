package com.engoneassessment.game.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.engoneassessment.game.actors.CustomActor;
import com.engoneassessment.game.ui.layouts.UIGroup;

public class UIStage extends Stage implements IUIStage{

    public UIStage(Viewport viewport) {
        super(viewport);
    }

    public void addUIElement(UIElement uiElement) {
        super.addActor(uiElement);
    }

    public void addUIGroup(UIGroup uiGroup) {
        super.addActor(uiGroup);
    }

    public void removeUIElement(UIElement uiElement) {

    }
}
