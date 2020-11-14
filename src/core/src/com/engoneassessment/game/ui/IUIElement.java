package com.engoneassessment.game.ui;

public interface IUIElement {
    public void setParentStage(UIStage parentStage);
    public UIStage getParentStage();

    public void setRelativeX(float relativeX);
    public void setRelativeY(float relativeY);
    public void setHorizontalAlignment(UIElement.HorizontalAlignment alignment);
    public void setVerticalAlignment(UIElement.VerticalAlignment alignment);
}
