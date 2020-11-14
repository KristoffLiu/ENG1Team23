package com.engoneassessment.game.ui.hud;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class HUDButton extends Button {
    public HUDButton(TextureRegion upTextureRegion, TextureRegion downTextureRegion ) {
        super(new ButtonStyle(){

        });
    }

    public static ButtonStyle generateButtonStyle(TextureRegion upTextureRegion, TextureRegion downTextureRegion){
        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(upTextureRegion);
        buttonStyle.down = new TextureRegionDrawable(downTextureRegion);
        return buttonStyle;
    }

    public


}
