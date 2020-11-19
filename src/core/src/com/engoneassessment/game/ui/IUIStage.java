package com.engoneassessment.game.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public interface IUIStage {
    public void addUIElement(Actor uiElement);
    public void removeUIElement(UIElement uiElement);
    public Array<Actor> getUIElementsAll();
    public void hide();
    public void appear();
}
