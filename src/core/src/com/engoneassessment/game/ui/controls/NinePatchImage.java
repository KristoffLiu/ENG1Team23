package com.engoneassessment.game.ui.controls;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.ui.UIStage;

public class NinePatchImage extends Image {

    NinePatch ninePatch;
    int left, right, top, bottom = 0;

    public NinePatchImage(TextureRegion textureRegion) {
        super(textureRegion);
    }

    public NinePatchImage(UIStage stage, TextureRegion textureRegion) {
        super(stage, textureRegion);
    }

    public void setNinePatch(int left, int right, int top, int bottom,
                             TextureRegion textureRegion){
        ninePatch = new NinePatch(textureRegion, left, right, top, bottom);
    }

    public void setMiddleWidth(float width){
        ninePatch.setMiddleWidth(width);
    }

    @Override
    public void setX(float X){
        super.setX(X);
        ninePatch.setPadLeft(X);
    }

    @Override
    public void setY(float Y){
        super.setY(Y);
        ninePatch.setPadBottom(Y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        ninePatch.draw(batch, 0,0,ninePatch.getTotalWidth(),ninePatch.getTotalHeight());
    }
}
