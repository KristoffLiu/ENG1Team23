package com.engoneassessment.game.ui.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.engoneassessment.game.GameEntry;
import com.engoneassessment.game.actors.characters.Player;
import com.engoneassessment.game.screens.RoomScreen;
import com.engoneassessment.game.ui.UIElement;
import com.engoneassessment.game.ui.UIStage;
import com.engoneassessment.game.ui.controls.ButtonClickListener;
import com.engoneassessment.game.ui.controls.ClickableUIElementClickListener;
import com.engoneassessment.game.utils.BlurShaderHelper;

public class HUDStage extends UIStage {
    private Player auber;

    private BeamButton beamButton;
    private TeleportButton teleportButton;

    private HealthBar healthBar;
    private RoomIndicator roomIndicator;
    private Minimap minimap;
    private Actor minimapMask;
    private Actor minimapBackground;

    private boolean isMiniMapOpen;
    Animation<TextureRegion> loopAnimation;

    public HUDStage(Viewport viewport, final GameEntry gameEntry){
        super(viewport);

        auber = gameEntry.getAuber();
        isMiniMapOpen = false;

        beamButton = new BeamButton(this);
        teleportButton = new TeleportButton(this);
        healthBar = new HealthBar(auber);
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

        minimap     = new Minimap(this, auber);

        beamButton          .setRelativePosition(20,20, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.bottomAlignment);
        teleportButton      .setRelativePosition(120,20, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.bottomAlignment);
        minimap             .setRelativePosition(20,10, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.topAlignment);

        minimap.addListener(new ClickListener(){
            /** Called any time the mouse cursor or a finger touch is moved over an actor. On the desktop, this event occurs even when no
             * mouse buttons are pressed (pointer will be -1).
             * @param fromActor May be null.
             * @see ClickableUIElementClickListener */
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                AlphaAction mapButtonAlphaAction = Actions.alpha(0.7F, 0.2F);
                minimap.mapButton.addAction(mapButtonAlphaAction);
            }

            /** Called any time the mouse cursor or a finger touch is moved out of an actor. On the desktop, this event occurs even when no
             * mouse buttons are pressed (pointer will be -1).
             * @param toActor May be null.
             * @see ClickableUIElementClickListener */
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                AlphaAction mapButtonAlphaAction = Actions.alpha(0F, 0.2F);
                minimap.mapButton.addAction(mapButtonAlphaAction);
            }

            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link Event#handle() handled}.
             * @see MapButton */
            @Override
            public void clicked (InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                openMiniMap();
//                if(RoomScreen.class.isInstance(gameEntry.getCargoScreen())){
//                    RoomScreen roomScreen = gameEntry.getCargoScreen();
//                            BlurShaderHelper.blur(roomScreen.stage);
//                }
            }
        });


    }

    public void updateRoomName(RoomScreen screen){
        roomIndicator.update(screen);
    }



    public void openMiniMap(){
        float duration = 0.30F;
        minimap.OpenMap(duration);
//        background.setWidth(this.getWidth());
//        background.setHeight(this.getHeight());
        AlphaAction minimapMaskAlphaAction = Actions.alpha(0.7F, duration);
        AlphaAction mapButtonDisappearAction = Actions.alpha(0F, duration);
        minimapMask.addAction(minimapMaskAlphaAction);
        minimap.mapButton.addAction(mapButtonDisappearAction);

        //AlphaAction minimapBackgroundAlphaAction = Actions.alpha(0.4F, duration);
        //minimapBackground.addAction(minimapBackgroundAlphaAction);
    }

    public void closeMiniMap(){
        float duration = 0.30F;
        minimap.CloseMap(duration);
        AlphaAction action = Actions.alpha(0F, duration);
        AlphaAction mapButtonAppearAction = Actions.alpha(1F, duration);
        minimapMask.addAction(action);
        minimap.mapButton.addAction(mapButtonAppearAction);

        //AlphaAction minimapBackgroundAlphaAction = Actions.alpha(0F, duration);
        //minimapBackground.addAction(minimapBackgroundAlphaAction);
    }


    public void teleport(){
        minimap.Teleport();
    }
}
