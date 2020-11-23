package com.engoneassessment.game.ui.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.ui.controls.Button;
import com.engoneassessment.game.ui.controls.ButtonClickListener;

public class MapButton extends Button {

    /**
     * Constructor of a MapButton
     * the texture input will be its mapping texture.
     *
     * @param uiParent
     */
    public MapButton(Object uiParent) {
        super(  uiParent,
                new TextureRegion(
                        new Texture("UI/Icons/Map.png")),
                new TextureRegion(
                        new Texture("UI/Icons/MapHover.png")),
                new TextureRegion(
                        new Texture("UI/Icons/MapHover.png")),
                new TextureRegion(
                        new Texture("UI/Icons/MapNotAble.png")));

        setClickListener(new ButtonClickListener(){
        });
    }

}
