package com.engoneassessment.game.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.engoneassessment.game.actors.CustomActor;
import com.engoneassessment.game.ui.layouts.UIGroup;

public class UIStage extends Stage implements IUIStage{

    Array<Actor> uiElements = new Array<Actor>();
    public UIStage(Viewport viewport) {
        super(viewport);
    }

    @Override
    public void addUIElement(Actor uiElement) {
        super.addActor(uiElement);
        this.uiElements.add(uiElement);
    }

    public void addUIGroup(UIGroup uiGroup) {
        super.addActor(uiGroup);
    }

    @Override
    public void removeUIElement(UIElement uiElement) {

    }

    @Override
    public Array<Actor> getUIElementsAll() {
        return uiElements;
    }
}
