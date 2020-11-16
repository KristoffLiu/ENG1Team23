package com.engoneassessment.game.ui.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.engoneassessment.game.actors.characters.Player;
import com.engoneassessment.game.screens.RoomScreen;
import com.engoneassessment.game.ui.UIElement;
import com.engoneassessment.game.ui.UIStage;

public class HUDStage extends UIStage {
    private Player player;

    private MapButton mapButton;
    private BeamButton beamButton;
    private TeleportButton teleportButton;

    private HealthBar healthBar;
    private RoomIndicator roomIndicator;
    private Minimap minimap;

    public HUDStage(Viewport viewport, Player auber){
        super(viewport);

        mapButton = new MapButton(this);
        beamButton = new BeamButton(this);
        teleportButton = new TeleportButton(this);


        healthBar = new HealthBar(player);
        roomIndicator = new RoomIndicator(this);
        //minimap = new Minimap();

        mapButton.setRelativePosition(20,20, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.bottomAlignment);
        beamButton.setRelativePosition(120,20, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.bottomAlignment);
        teleportButton.setRelativePosition(220,20, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.bottomAlignment);

        this.addActor(healthBar.PlayerName);
        this.addActor(healthBar.hpBar);
        this.addActor(healthBar.hpIndicator);
        this.addActor(roomIndicator.Title);
        this.addActor(roomIndicator.RoomName);

    }

    public void updateRoomName(RoomScreen screen){
        roomIndicator.update(screen);
    }
}
