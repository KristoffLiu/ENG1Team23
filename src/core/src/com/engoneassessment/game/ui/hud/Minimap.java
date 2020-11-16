package com.engoneassessment.game.ui.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.engoneassessment.game.actors.characters.Player;
import com.engoneassessment.game.ui.UIStage;
import com.engoneassessment.game.ui.controls.Image;

public class Minimap extends UIStage {
    Image miniMapImage;
    public Minimap(Viewport viewport, Player auber){
        super(viewport);
        miniMapImage = new Image(new TextureRegion(new Texture("")));
    }
}
