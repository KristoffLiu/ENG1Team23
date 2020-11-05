package com.engoneassessment.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
/**
 * Custom Actor class extends from interface Actor.
 * This custom actor offers actor the feature of collision detection.
 *
 * - Zhikang Liu 2020/11/03
 */
public class CustomActor extends Actor {
    // TextureRegion of this Customer Actor
    private TextureRegion textureRegion;
    private Rectangle Bounds;


    public CustomActor(TextureRegion textureRegion) {
        super();
        this.textureRegion = textureRegion;
        // Binding both of the width and height of the actor with the width and height of its texture region
        // notice: this procress is compulsory,
        // otherwise the width and height will be set as default value,
        // which is 0, and makes it invisible.
        setSize(this.textureRegion.getRegionWidth(), this.textureRegion.getRegionHeight());
        Bounds = new Rectangle((int)getX(),(int)getY(),(int)getWidth(),(int)getHeight());
        // Or you can also set the value separately :)
        // setWidth(this.region.getRegionWidth());
        // setHeight(this.region.getRegionHeight());
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public Rectangle getBounds(){
        return Bounds;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
        // reset the width and height after resetting the texture region.
        setSize(this.textureRegion.getRegionWidth(), this.textureRegion.getRegionHeight());
    }

    /**
     * logic handler of the actor
     *
     * @param delta
     *		the change of time from the last rendered frame to the current rendering frame,
     *	    or we call it the rendering time step / time difference.
     *	    the unite is second.
     */
    @Override
    public void act(float delta) {
        super.act(delta);

        // 现在这里一般没有什么逻辑要处理
        // Usually there's no more actions to deal with.
    }

    /**
     * 绘制演员
     *
     * @param batch
     * 		The Sprite Batch, 用于绘制演员封装的纹理。SpriteBatch 就是实现了 Batch 接口
     *
     * @param parentAlpha
     * 		父节点的透明度, 处理透明度和演员的颜色属性有关, 稍微复杂, 这里暂时先不处理
     * 		The Sprite Batch, is used to render the texture of actor encapsulation.
     *
     * @param parentAlpha
     * 		the transparent of parent
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (textureRegion == null || !isVisible()) {
            return;
        }
        batch.draw(
                textureRegion,
                getX(), getY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation()
        );
    }


}
