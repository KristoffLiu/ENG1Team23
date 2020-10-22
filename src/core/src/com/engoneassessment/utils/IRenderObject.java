package com.engoneassessment.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Shape2D;

public interface IRenderObject {
    public Shape2D getShape();
    public void Move(float x,float y);

    public void setRenderMode(boolean _isRendering);
    public void setTexture(Texture texture);
    public void Render(SpriteBatch batch);

    public void dispose();
}


