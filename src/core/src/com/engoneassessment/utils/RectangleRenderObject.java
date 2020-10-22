package com.engoneassessment.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Rectangle;

public class RectangleRenderObject extends RenderObject {
    public RectangleRenderObject(Texture _texture){
        super(_texture, (Shape2D) new Rectangle());
    }

    public float getWidth(){
        return thisRectangle().width;
    }
    public float getHeight(){
        return thisRectangle().height;
    }
    public float getX(){
        return thisRectangle().x;
    }
    public float getY(){
        return thisRectangle().y;
    }

    public void setWidth(float width){
        thisRectangle().width = width;
    }
    public void setHeight(float height){
        thisRectangle().height = height;
    }
    public void setX(float x){
        thisRectangle().x = x;
    }
    public void setY(float y){
        thisRectangle().y = y;
    }

    public void changeWidth(float delta_width){
        thisRectangle().setWidth(thisRectangle().getWidth() + delta_width);
    }
    public void changeHeight(float delta_height){
        thisRectangle().setHeight(thisRectangle().getHeight() + delta_height);
    }
    public void changeX(float delta_x){
        thisRectangle().setX(thisRectangle().getX() + delta_x);
    }
    public void changeY(float delta_y){
        thisRectangle().setY(thisRectangle().getY() + delta_y);
    }

    @Override
    public Shape2D getShape() {
        return shape;
    }

    private Rectangle thisRectangle() {
        return (Rectangle)shape;
    }

    @Override
    public void Move(float x, float y) {
        Rectangle _movingObject = (Rectangle) shape;
        _movingObject.x += x;
        _movingObject.y += y;
    }

    @Override
    public void setTexture(Texture _texture) {
        texture = _texture;
    }

    @Override
    public void Render(SpriteBatch batch) {
        batch.draw(texture, thisRectangle().getX(), thisRectangle().getY());
    }
}
