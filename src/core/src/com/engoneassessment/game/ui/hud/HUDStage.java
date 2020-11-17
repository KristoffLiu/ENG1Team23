package com.engoneassessment.game.ui.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.engoneassessment.game.actors.characters.Player;
import com.engoneassessment.game.screens.RoomScreen;
import com.engoneassessment.game.ui.UIElement;
import com.engoneassessment.game.ui.UIStage;
import com.engoneassessment.game.ui.controls.ButtonClickListener;

public class HUDStage extends UIStage {
    private Player player;

    private MapButton mapButton;
    private BeamButton beamButton;
    private TeleportButton teleportButton;

    private HealthBar healthBar;
    private RoomIndicator roomIndicator;
    private Minimap minimap;
    private Actor minimapMask;
    private Actor minimapBackground;

    private boolean isMiniMapOpen;

    public HUDStage(Viewport viewport, Player auber){
        super(viewport);

        isMiniMapOpen = false;

        beamButton = new BeamButton(this);
        teleportButton = new TeleportButton(this);
        healthBar = new HealthBar(player);
        roomIndicator = new RoomIndicator(this);
        this.addActor(healthBar.PlayerName);
        this.addActor(healthBar.hpBar);
        this.addActor(healthBar.hpIndicator);
        this.addActor(roomIndicator.Title);
        this.addActor(roomIndicator.RoomName);
        this.addActor(roomIndicator.RoomName);

        minimapMask = new UIElement(this,new TextureRegion(new Texture("UI/Colors/Black.jpg")));
        minimapMask.getColor().a = 0f;

        minimapBackground = new UIElement(this,new TextureRegion(new Texture("Background/space.gif")));
        minimapBackground.getColor().a = 0f;
        minimapBackground.addListener(new ClickListener(){
            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link Event#handle() handled}.
             * @see MapButton */
            @Override
            public void clicked (InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                closeMiniMap();
            }
        });

        minimap = new Minimap(this, auber);
        mapButton = new MapButton(this);

        mapButton           .setRelativePosition(120 ,80, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.topAlignment);
        beamButton          .setRelativePosition(20,20, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.bottomAlignment);
        teleportButton      .setRelativePosition(120,20, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.bottomAlignment);
        minimap             .setRelativePosition(20,10, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.topAlignment);

        mapButton.setClickListener(new ButtonClickListener(){
            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link Event#handle() handled}.
             * @see MapButton */
            @Override
            public void clicked (InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                openMiniMap();
            }
        });
    }

    public void updateRoomName(RoomScreen screen){
        roomIndicator.update(screen);
    }

    public void openMiniMap(){
        float duration = 0.70F;
        minimap.OpenMap(duration);
//        background.setWidth(this.getWidth());
//        background.setHeight(this.getHeight());
        AlphaAction minimapMaskAlphaAction = Actions.alpha(0.7F, duration);
        AlphaAction mapButtonDisappearAction = Actions.alpha(0F, duration);
        minimapMask.addAction(minimapMaskAlphaAction);
        mapButton.addAction(mapButtonDisappearAction);

        AlphaAction minimapBackgroundAlphaAction = Actions.alpha(0.4F, 1.0F);
        //DelayAction delayAction = Actions.delay(duration - 0.45f,minimapBackgroundAlphaAction);
        minimapBackground.addAction(minimapBackgroundAlphaAction);
    }

    public void closeMiniMap(){
        float duration = 0.3F;
        minimap.CloseMap(duration);
        AlphaAction action = Actions.alpha(0F, duration);
        AlphaAction mapButtonAppearAction = Actions.alpha(1F, duration);
        minimapMask.addAction(action);
        mapButton.addAction(mapButtonAppearAction);

        AlphaAction minimapBackgroundAlphaAction = Actions.alpha(0F, duration);
        //DelayAction delayAction = Actions.delay(0.5f,minimapBackgroundAlphaAction);
        minimapBackground.addAction(minimapBackgroundAlphaAction);
    }

    public void teleport(){
        minimap.Teleport();
    }
}
