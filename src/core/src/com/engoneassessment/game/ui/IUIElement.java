package com.engoneassessment.game.ui;

public interface IUIElement {
    Object uiParent = null;
    UIElement.HorizontalAlignment horizontalAlignment = UIElement.HorizontalAlignment.leftAlignment;
    UIElement.VerticalAlignment verticalAlignment = UIElement.VerticalAlignment.bottomAlignment;
    float relativeX = 0;
    float relativeY = 0;

    public void setUIParent(Object parentStage);
    public Object getParent();

    public float getRelativeX();
    public float getRelativeY();
    public void setRelativeX(float relativeX);
    public void setRelativeY(float relativeY);
    public UIElement.HorizontalAlignment getHorizontalAlignment();
    public UIElement.VerticalAlignment getVerticalAlignment();
    public void setHorizontalAlignment(UIElement.HorizontalAlignment alignment);
    public void setVerticalAlignment(UIElement.VerticalAlignment alignment);
    public void setRelativePosition(float relativeX,float relativeY,UIElement.HorizontalAlignment horizontalAlignment,UIElement.VerticalAlignment verticalAlignment);
}
