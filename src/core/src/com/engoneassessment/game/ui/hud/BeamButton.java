package com.engoneassessment.game.ui.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.ui.UIStage;
import com.engoneassessment.game.ui.controls.Button;
import com.engoneassessment.game.ui.controls.ButtonClickListener;

public class BeamButton extends Button {

    /**
     * Constructor of a MapButton
     * the texture input will be its mapping texture.
     *
     * @param parentUIStage
     */
    public BeamButton(UIStage parentUIStage) {
        super(  parentUIStage,
                new TextureRegion(
                        new Texture("UI/Icons/Beam.png")),
                new TextureRegion(
                        new Texture("UI/Icons/BeamHover.png")),
                new TextureRegion(
                        new Texture("UI/Icons/BeamHover.png")),
                new TextureRegion(
                        new Texture("UI/Icons/BeamNotAble.png")));

        setClickListener(new ButtonClickListener(){

        });
    }

}
