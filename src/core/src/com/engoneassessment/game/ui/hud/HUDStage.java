package com.engoneassessment.game.ui.hud;

import com.badlogic.gdx.Game;
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
import com.engoneassessment.game.ui.NonUIAnimationHelper;
import com.engoneassessment.game.ui.UIElement;
import com.engoneassessment.game.ui.UIStage;
import com.engoneassessment.game.ui.controls.ButtonClickListener;
import com.engoneassessment.game.ui.controls.ClickableUIElementClickListener;
import com.engoneassessment.game.ui.controls.Image;
import com.engoneassessment.game.utils.BlurShaderHelper;

public class HUDStage extends UIStage {

    private GameEntry gameEntry;
    private Player auber;
    private Actor invisibleActor;

    private BeamButton beamButton;
    private TeleportButton teleportButton;

    private HealthBar healthBar;
    private RoomIndicator roomIndicator;
    private Minimap minimap;
    private Image minimapMask;
    private Image minimapBackground;

    private boolean isMiniMapOpen;
    Animation<TextureRegion> loopAnimation;

    NonUIAnimationHelper hpIndicatorNonUIAnimationHelper    ;
    NonUIAnimationHelper RoomNameNonUIAnimationHelper       ;
    NonUIAnimationHelper PlayerNameNonUIAnimationHelper     ;
    NonUIAnimationHelper TitleNonUIAnimationHelper          ;

    public HUDStage(Viewport viewport, final GameEntry gameEntry){
        super(viewport);

        this.gameEntry = gameEntry;
        auber = gameEntry.getAuber();
        invisibleActor = new Actor();
        isMiniMapOpen = false;

        beamButton          = new BeamButton(this);
        teleportButton      = new TeleportButton(this);
        healthBar           = new HealthBar(auber);
        roomIndicator       = new RoomIndicator(this);

        this.addActor(invisibleActor);
        this.addActor(healthBar.PlayerName);
        this.addActor(healthBar.hpBar);
        this.addActor(healthBar.hpIndicator);
        this.addActor(roomIndicator.Title   );
        this.addActor(roomIndicator.RoomName);
        this.addActor(roomIndicator.RoomName);

        minimapMask = new Image(this,new TextureRegion(new Texture("UI/Colors/Black.jpg")));
        minimapMask.getColor().a = 0f;
        minimapBackground = new Image(this,new TextureRegion(new Texture("Background/space.gif")));
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

//        beamButton              .setVisible(false);
//        teleportButton          .setVisible(false);
//        healthBar.hpBar         .setVisible(false);
//        healthBar.PlayerName    .setVisible(false);
//        healthBar.hpIndicator   .setVisible(false);
//        roomIndicator.Title     .setVisible(false);
//        roomIndicator.RoomName  .setVisible(false);
//        roomIndicator.RoomName  .setVisible(false);
//        minimap                 .setVisible(false);
//        minimapMask             .setVisible(false);

        hpIndicatorNonUIAnimationHelper    = new NonUIAnimationHelper(healthBar.hpIndicator  );
        RoomNameNonUIAnimationHelper       = new NonUIAnimationHelper(roomIndicator.RoomName );
        PlayerNameNonUIAnimationHelper     = new NonUIAnimationHelper(healthBar.PlayerName   );
        TitleNonUIAnimationHelper          = new NonUIAnimationHelper(roomIndicator.Title    );



        beamButton          .setRelativePosition(20,20, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.bottomAlignment);
        teleportButton      .setRelativePosition(120,20, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.bottomAlignment);
        minimap             .setRelativePosition(20,10, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.topAlignment);

        minimap.addListener(new ClickListener(){
            /** Called any time the mouse cursor or a finger touch is moved over an actor. On the desktop, this event occurs even when no
             * mouse buttons are pressed (pointer will be -1).
             * @param fromActor May be null.
             * @see ClickListener */
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                AlphaAction mapButtonAlphaAction = Actions.alpha(0.7F, 0.2F);
                minimap.mapButton.addAction(mapButtonAlphaAction);
            }

            /** Called any time the mouse cursor or a finger touch is moved out of an actor. On the desktop, this event occurs even when no
             * mouse buttons are pressed (pointer will be -1).
             * @param toActor May be null.
             * @see ClickListener */
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                AlphaAction mapButtonAlphaAction = Actions.alpha(0F, 0.2F);
                minimap.mapButton.addAction(mapButtonAlphaAction);
            }

            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link Event#handle() handled}.
             * @see ClickListener */
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
        float duration = 0.30F;
        minimap.OpenMap(duration);

        AlphaAction minimapMaskAlphaAction = Actions.alpha(0.7F, duration);
        AlphaAction mapButtonDisappearAction = Actions.alpha(0F, duration);
        minimapMask.addAction(minimapMaskAlphaAction);
        minimap.mapButton.addAction(mapButtonDisappearAction);
        hideWhenMapOpen();
    }

    public void closeMiniMap(){
        float duration = 0.30F;
        minimap.CloseMap(duration);
        AlphaAction action = Actions.alpha(0F, duration);
        minimapMask.addAction(action);
        minimap.mapButton.appear(duration);
        appearWhenMapClose();
        //AlphaAction minimapBackgroundAlphaAction = Actions.alpha(0F, duration);
        //minimapBackground.addAction(minimapBackgroundAlphaAction);
    }


    public void teleport(){
        minimap.Teleport();
    }

    public void appearAll(){
        beamButton          .fadeIn(0,50,0.5f,null);
        teleportButton      .fadeIn(0,50,0.5f,null);
        healthBar.hpBar     .fadeIn(0,50,0.5f,null);
        hpIndicatorNonUIAnimationHelper  .fadeIn(0,50,0.5f,null);
        PlayerNameNonUIAnimationHelper   .fadeIn(0,50,0.5f,null);

        RoomNameNonUIAnimationHelper     .fadeIn(0,-50,0.5f,null);
        TitleNonUIAnimationHelper        .fadeIn(0,-50,0.5f,null);
        minimap                          .fadeIn(0,-50,0.5f,null);
    }

    public void hideAll(){
        beamButton          .fadeOut(0,-50,0.5f,null);
        teleportButton      .fadeOut(0,-50,0.5f,null);
        healthBar.hpBar     .fadeOut(0,-50,0.5f,null);
        PlayerNameNonUIAnimationHelper   .fadeOut(0,-50,0.5f,null);
        hpIndicatorNonUIAnimationHelper  .fadeOut(0,-50,0.5f,null);

        RoomNameNonUIAnimationHelper     .fadeOut(0,50,0.5f,null);
        TitleNonUIAnimationHelper        .fadeOut(0,50,0.5f,null);
        minimap                          .fadeOut(0,50,0.5f,null);

        RunnableAction switchScreenAction = Actions.run(new Runnable() {
            @Override
            public void run() {
                Gdx.app.log("hhhhhhh", "演员的其他所有动作都已经执行完了");
                gameEntry.setScreen_Finished();
            }
        });
        //MoveToAction moveToAction = Actions.moveTo(50,50,0.6f);
        DelayAction action = Actions.delay(0.7f);
        AfterAction afterAction = Actions.after(switchScreenAction);
        invisibleActor.addAction(action);
        invisibleActor.addAction(afterAction);
    }

    public void appearWhenMapClose(){
        beamButton          .fadeIn(0,50,0.5f,null);
        teleportButton      .fadeIn(0,50,0.5f,null);
        healthBar.hpBar     .fadeIn(0,50,0.5f,null);
        hpIndicatorNonUIAnimationHelper  .fadeIn(0,50,0.5f,null);
        PlayerNameNonUIAnimationHelper   .fadeIn(0,50,0.5f,null);

        RoomNameNonUIAnimationHelper     .fadeIn(0,-50,0.5f,null);
        TitleNonUIAnimationHelper        .fadeIn(0,-50,0.5f,null);
    }

    public void hideWhenMapOpen(){
        beamButton          .fadeOut(0,-50,0.5f,null);
        teleportButton      .fadeOut(0,-50,0.5f,null);
        healthBar.hpBar     .fadeOut(0,-50,0.5f,null);
        PlayerNameNonUIAnimationHelper   .fadeOut(0,-50,0.5f,null);
        hpIndicatorNonUIAnimationHelper  .fadeOut(0,-50,0.5f,null);

        RoomNameNonUIAnimationHelper     .fadeOut(0,50,0.5f,null);
        TitleNonUIAnimationHelper        .fadeOut(0,50,0.5f,null);
    }

}
