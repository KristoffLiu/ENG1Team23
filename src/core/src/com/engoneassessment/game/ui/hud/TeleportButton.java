package com.engoneassessment.game.ui.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.ui.UIStage;
import com.engoneassessment.game.ui.controls.Button;
import com.engoneassessment.game.ui.controls.ButtonClickListener;

public class TeleportButton extends Button {

    /**
     * Constructor of a MapButton
     * the texture input will be its mapping texture.
     *
     * @param parentUIStage
     */
    public TeleportButton(UIStage parentUIStage) {
        super(  parentUIStage,
                new TextureRegion(
                        new Texture("UI/Icons/Teleport.png")),
                new TextureRegion(
                        new Texture("UI/Icons/TeleportHover.png")),
                new TextureRegion(
                        new Texture("UI/Icons/TeleportHover.png")),
                new TextureRegion(
                        new Texture("UI/Icons/TeleportNotAble.png")));
        setButtonUIState(ButtonUIState.notActivated);
        setClickListener(new ButtonClickListener(){

        });
    }
}
