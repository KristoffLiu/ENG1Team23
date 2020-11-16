package com.engoneassessment.game.ui.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.engoneassessment.game.GameEntry;
import com.engoneassessment.game.actors.characters.Player;
import com.engoneassessment.game.screens.RoomScreen;
import com.engoneassessment.game.ui.UIElement;
import com.engoneassessment.game.ui.UIStage;
import com.engoneassessment.game.ui.controls.ClickableUIElement;
import com.engoneassessment.game.ui.controls.Image;
import com.engoneassessment.game.ui.controls.labels.LabelStyles;
import com.engoneassessment.game.ui.minimap.Minimap;

import java.util.Map;

public class HUDStage extends UIStage {
    private final Player player;

    private MapButton mapButton;
    private BeamButton beamButton;
    private TeleportButton teleportButton;

    private HealthBar healthBar;
    private RoomIndicator roomIndicator;
    private SabotageIndicator sabotageIndicator;

    public HUDStage(Viewport viewport, Player player, final GameEntry gameEntry){
        super(viewport);
        this.player = player;

        mapButton = new MapButton(
                this,
                new TextureRegion(
                        new Texture("UI/Icons/Map.png")));
        beamButton = new BeamButton(
                this,
                new TextureRegion(
                        new Texture("UI/Icons/Beam.png")));
        teleportButton = new TeleportButton(
                this,
                new TextureRegion(
                        new Texture("UI/Icons/Teleport.png")));

        healthBar = new HealthBar(player);
        roomIndicator = new RoomIndicator(this);
        sabotageIndicator = new SabotageIndicator(this);

        mapButton.setRelativePosition(20,20, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.bottomAlignment);
        beamButton.setRelativePosition(120,20, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.bottomAlignment);
        teleportButton.setRelativePosition(220,20, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.bottomAlignment);

        this.addActor(healthBar.PlayerName);
        this.addActor(healthBar.hpBar);
        this.addActor(healthBar.hpIndicator);
        this.addActor(roomIndicator.Title);
        this.addActor(roomIndicator.RoomName);
        this.addActor(sabotageIndicator.RoomName);

        //Detects any inputs related to the play button
        beamButton.addListener(new ClickListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                beamButton.setButtonUIState(ClickableUIElement.ButtonUIState.hovered);
                super.enter(event, x, y, pointer, fromActor);
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                beamButton.setButtonUIState(ClickableUIElement.ButtonUIState.normal);
                super.enter(event, x, y, pointer, fromActor);
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                beamButton.setButtonUIState(ClickableUIElement.ButtonUIState.pressed);
                super.clicked(event, x, y);
                gameEntry.beamHostiles();
            }
        });
    }

    public void updateRoomName(RoomScreen screen){
        roomIndicator.update(screen);
    }

    public void updateLastSabotaged(RoomScreen screen) {sabotageIndicator.update(screen);};
}
