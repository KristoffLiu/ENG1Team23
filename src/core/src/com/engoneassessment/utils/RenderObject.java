package com.engoneassessment.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Shape2D;

public abstract class RenderObject implements IRenderObject {
    boolean isRendering;
    Texture texture;
    Shape2D shape;

    public RenderObject(Texture _texture,Shape2D _shape){
        texture = _texture;
        shape = _shape;
    }

    @Override
    public abstract void Move(float x, float y);

    @Override
    public void setRenderMode(boolean _isRendering) {
        isRendering = _isRendering;
    }

    @Override
    public abstract Shape2D getShape();

    @Override
    public abstract void setTexture(Texture texture);

    @Override
    public abstract void Render(SpriteBatch batch);

    @Override
    public void dispose(){
        texture.dispose();
    }
}
