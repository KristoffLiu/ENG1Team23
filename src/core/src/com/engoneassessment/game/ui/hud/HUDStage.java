package com.engoneassessment.game.ui.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.engoneassessment.game.actors.characters.Player;
import com.engoneassessment.game.ui.UIStage;

import java.util.Map;

public class HUDStage extends UIStage {
    private Player player;

    private MapButton mapButton = new MapButton(
            new TextureRegion(
                    new Texture("UI/Icons/Map.png")));
    private BeamButton beamButton = new BeamButton(
            new TextureRegion(
                    new Texture("UI/Icons/Beam.png")));
    private TeleportButton teleportButton = new TeleportButton(
            new TextureRegion(
                    new Texture("UI/Icons/Teleport.png")));

    private Image hitbar;
    private Texture hitbarTexture;

    private int hitbar_frameRows = 10;
    private int hitbar_frameCols = 1;

    public HUDStage(Viewport viewport, Player auber){
        super(viewport);

        mapButton.setWidth(mapButton.getWidth() * 1.5f);
        mapButton.setHeight(mapButton.getHeight() * 1.5f);
        mapButton.setX(this.getWidth() - mapButton.getWidth() - 20);
        mapButton.setY(20);

        this.addActor(hitbar);
        this.addActor(mapButton);
        this.addActor(beamButton);
        this.addActor(teleportButton);
    }

    public void fadeIn(){

    }

    public void fadeOut(){

    }
}
