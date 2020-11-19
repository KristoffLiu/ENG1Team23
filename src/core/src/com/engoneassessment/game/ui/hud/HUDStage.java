package com.engoneassessment.game.ui.hud;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
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
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.engoneassessment.game.GameEntry;
import com.engoneassessment.game.actors.characters.Player;
import com.engoneassessment.game.screens.RoomScreen;
import com.engoneassessment.game.ui.NonUIAnimationHelper;
import com.engoneassessment.game.ui.UIElement;
import com.engoneassessment.game.ui.UIStage;
import com.engoneassessment.game.ui.controls.ClickableUIElement;
import com.engoneassessment.game.ui.controls.Image;
import com.engoneassessment.game.ui.controls.labels.LabelStyles;
import com.engoneassessment.game.ui.hud.minimap.MiniMap;

public class HUDStage extends UIStage {
    private final Player player;

    private GameEntry gameEntry;
    private Player auber;
    private Actor invisibleActor;

    private BeamButton beamButton;
    private TeleportButton teleportButton;

    private HealthBar healthBar;
    private RoomIndicator roomIndicator;
    private SabotageIndicator sabotageIndicator;

    NonUIAnimationHelper hpIndicatorNonUIAnimationHelper  ;
    NonUIAnimationHelper RoomNameNonUIAnimationHelper     ;
    NonUIAnimationHelper PlayerNameNonUIAnimationHelper   ;
    NonUIAnimationHelper TitleNonUIAnimationHelper        ;

    Image minimapMask;
    MiniMap minimap;

    private Image minimapBackground;
    private Label teleportingIndicator;

    private RoomScreen currentRoomScreen;

    private boolean isMiniMapOpen;
    Animation<TextureRegion> loopAnimation;

    public HUDStage(Viewport viewport, Player player, final GameEntry gameEntry){
        super(viewport);
        this.player = player;

        beamButton          = new BeamButton(this);
        teleportButton      = new TeleportButton(this);
        healthBar           = new HealthBar(auber);
        roomIndicator       = new RoomIndicator(this);

        healthBar = new HealthBar(player);
        roomIndicator = new RoomIndicator(this);
        sabotageIndicator = new SabotageIndicator(this);

        beamButton.setRelativePosition(120,20, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.bottomAlignment);
        teleportButton.setRelativePosition(220,20, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.bottomAlignment);
        this.gameEntry = gameEntry;
        auber = gameEntry.getAuber();
        invisibleActor = new Actor();

        this.addActor(invisibleActor);
        this.addActor(healthBar.PlayerName);
        this.addActor(healthBar.hpBar);
        this.addActor(healthBar.hpIndicator);
        this.addActor(roomIndicator.Title   );
        this.addActor(roomIndicator.RoomName);
        this.addActor(sabotageIndicator.RoomName);
        this.addActor(roomIndicator.RoomName);

        minimapMask = new Image(this,new TextureRegion(new Texture("UI/Colors/Black.jpg")));
        minimapMask.getColor().a = 0f;
        minimapMask.addListener(new ClickListener(){
            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link Event#handle() handled}.
             * @see MapButton */
            @Override
            public void clicked (InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                closeMiniMap();
            }
        });
        minimap     = new MiniMap(this, gameEntry);

        hpIndicatorNonUIAnimationHelper    = new NonUIAnimationHelper(healthBar.hpIndicator  );
        RoomNameNonUIAnimationHelper       = new NonUIAnimationHelper(roomIndicator.RoomName );
        PlayerNameNonUIAnimationHelper     = new NonUIAnimationHelper(healthBar.PlayerName   );
        TitleNonUIAnimationHelper          = new NonUIAnimationHelper(roomIndicator.Title    );

        beamButton          .setRelativePosition(20,20, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.bottomAlignment);
        teleportButton      .setRelativePosition(120,20, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.bottomAlignment);
        minimap             .setRelativePosition(20,10, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.topAlignment);


        beamButton                          .setAnimationOrigin(beamButton            .getX(), beamButton            .getY() );
        teleportButton                      .setAnimationOrigin(teleportButton        .getX(), teleportButton        .getY() );
        healthBar.hpBar                     .setAnimationOrigin(healthBar.hpBar       .getX(), healthBar.hpBar       .getY() );
        hpIndicatorNonUIAnimationHelper     .setAnimationOrigin(healthBar.hpIndicator .getX(), healthBar.hpIndicator .getY() );
        PlayerNameNonUIAnimationHelper      .setAnimationOrigin(healthBar.PlayerName  .getX(), healthBar.PlayerName  .getY() );
        RoomNameNonUIAnimationHelper        .setAnimationOrigin(roomIndicator.RoomName.getX(), roomIndicator.RoomName.getY() );
        TitleNonUIAnimationHelper           .setAnimationOrigin(roomIndicator.Title   .getX(), roomIndicator.Title   .getY() );
        minimap                             .setAnimationOrigin(minimap               .getX(), minimap               .getY() );

        minimap.addListener(new ClickListener(){
            /** Called any time the mouse cursor or a finger touch is moved over an actor. On the desktop, this event occurs even when no
             * mouse buttons are pressed (pointer will be -1).
             * @param fromActor May be null.
             * @see ClickListener */
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                if(!minimap.isOpen){
                    AlphaAction mapButtonAlphaAction = Actions.alpha(1.0F, 0.2F);
                    minimap.mapButton.addAction(mapButtonAlphaAction);

                    AlphaAction minimapAlphaAction = Actions.alpha(1.0F, 0.2F);
                    minimap.addAction(minimapAlphaAction);
                }
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
                if(!minimap.isOpen){
                    AlphaAction minimapAlphaAction = Actions.alpha(0.5F, 0.2F);
                    minimap.addAction(minimapAlphaAction);
                }

            }

            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link Event#handle() handled}.
             * @see ClickListener */
            @Override
            public void clicked (InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(minimap.isOpen){

                }
                else {
                    openMiniMap();
                }
            }
        });

    }

    public void updateRoomName(RoomScreen screen){
        roomIndicator.update(screen);
        beamButton                          .fadeIn(0,50,0.5f,null);
        teleportButton                      .fadeIn(0,50,0.5f,null);
        healthBar.hpBar                     .fadeIn(0,50,0.5f,null);
        hpIndicatorNonUIAnimationHelper     .fadeIn(0,50,0.5f,null);
        PlayerNameNonUIAnimationHelper      .fadeIn(0,50,0.5f,null);
        RoomNameNonUIAnimationHelper        .fadeIn(0,-50,0.5f,null);
        TitleNonUIAnimationHelper           .fadeIn(0,-50,0.5f,null);
        minimap                             .fadeIn(0,-50,0.5f,null);
        minimap.setSelectedRoomImage(screen);
    }

    public void openMiniMap(){
        float duration = 0.30F;
        minimap.OpenMap(duration);

        VisibleAction minimapVisibleAction = Actions.visible(true);
        AlphaAction minimapMaskAlphaAction = Actions.alpha(0.85F, duration);
        SequenceAction sequenceAction = Actions.sequence(minimapVisibleAction, minimapMaskAlphaAction);
        minimapMask.addAction(sequenceAction);

        AlphaAction mapButtonDisappearAction = Actions.alpha(0F, duration);
        minimap.mapButton.addAction(mapButtonDisappearAction);

        hideWhenMapOpen();
    }

    public void updateLastSabotaged(RoomScreen screen) {sabotageIndicator.update(screen);};
    public void closeMiniMap(){
        float duration = 0.30F;
        minimap.CloseMap(duration);

        AlphaAction alphaAction = Actions.alpha(0F, duration);
        VisibleAction visibleAction = Actions.visible(false);
        SequenceAction sequenceAction = Actions.sequence(alphaAction, visibleAction);
        minimapMask.addAction(sequenceAction);

        appearWhenMapClose();
    }

    RoomScreen nextTeleportingRoomScreen;
    
    public void teleport(Screen roomScreen){
        nextTeleportingRoomScreen = (RoomScreen) roomScreen;
        //MiniMap Teleporting Animation - Enlargement Step 1
        Interpolation firstInterpolation = Interpolation.pow3Out;
        float firstTarget_duration = 0.6f;
        float firstTarget_scale = 1.9f;
        float firstTarget_roomButtonWidth = minimap.getSelectedRoomImage().getWidth() * firstTarget_scale;
        float firstTarget_roomButtonHeight = minimap.getSelectedRoomImage().getHeight() * firstTarget_scale;
        float firstTarget_roomButtonX = minimap.getSelectedRoomImage().getRelativeX() * firstTarget_scale;
        float firstTarget_roomButtonY = minimap.getSelectedRoomImage().getRelativeY() * firstTarget_scale;
        float firstTarget_x = this.getWidth()/2 + firstTarget_roomButtonX + firstTarget_roomButtonWidth / 2;
        float firstTarget_y = this.getHeight()/2 + firstTarget_roomButtonY + firstTarget_roomButtonHeight / 2;

        ScaleToAction firstScaleToAction = Actions.scaleTo(firstTarget_scale,firstTarget_scale,firstTarget_duration,firstInterpolation);
        MoveToAction firstMoveToAction = Actions.moveTo(firstTarget_x,firstTarget_y,firstTarget_duration,firstInterpolation);
        ParallelAction firstParallelAction = Actions.parallel(firstScaleToAction,firstMoveToAction);


        //MiniMap Teleporting Animation - Enlargement Step 2
        Interpolation secondInterpolation = Interpolation.pow3Out;
        float secondTarget_duration = 0.8f;
        float secondTarget_scale = 60f;
        float secondTarget_roomButtonWidth = minimap.getSelectedRoomImage().getWidth() * secondTarget_scale;
        float secondTarget_roomButtonHeight = minimap.getSelectedRoomImage().getHeight() * secondTarget_scale;
        float secondTarget_roomButtonX = minimap.getSelectedRoomImage().getRelativeX() * secondTarget_scale;
        float secondTarget_roomButtonY = minimap.getSelectedRoomImage().getRelativeY() * secondTarget_scale;
        float secondTarget_x = this.getWidth()/2 + secondTarget_roomButtonX + secondTarget_roomButtonWidth / 2;
        float secondTarget_y = this.getHeight()/2 + secondTarget_roomButtonY + secondTarget_roomButtonHeight / 2;

        ScaleToAction secondScaleToAction = Actions.scaleTo(secondTarget_scale,secondTarget_scale,secondTarget_duration,secondInterpolation);
        MoveToAction secondMoveToAction = Actions.moveTo(secondTarget_x,secondTarget_y,secondTarget_duration,secondInterpolation);

        RunnableAction runnableAction = Actions.run(new Runnable() {
            @Override
            public void run() {
                gameEntry.setRoomScreen_Finished(nextTeleportingRoomScreen);
                teleportingIndicator = new Label(
                        "Teleporting to " + nextTeleportingRoomScreen.getName() + " Room",
                        LabelStyles.usingImpactFontStyle(
                                true,0.9f,
                                1f,1f,1f,1f));
                addActor(teleportingIndicator);

                teleportingIndicator.setPosition(
                        getWidth()/2 - teleportingIndicator.getWidth()/2,
                        getHeight()/2 - teleportingIndicator.getHeight()/2);

                AlphaAction fadeInAlphaAction = Actions.alpha(1.0f, 0.4f);
                AlphaAction fadeOutAlphaAction = Actions.alpha(0f, 0.4f);
                DelayAction fadeOutDelayAction = Actions.delay(0.8f,fadeOutAlphaAction);


                ParallelAction parallelAction = Actions.parallel(fadeOutDelayAction);
                SequenceAction teleportingIndicatorSequenceAction = Actions.sequence(fadeInAlphaAction,parallelAction);
                teleportingIndicator.addAction(teleportingIndicatorSequenceAction);

                AlphaAction minimapMaskAlphaAction = Actions.alpha(0F, 0.4f);
                VisibleAction minimapMaskVisibleAction = Actions.visible(false);
                SequenceAction minimapMaskSequenceAction = Actions.sequence(minimapMaskAlphaAction, minimapMaskVisibleAction);
                DelayAction minimapMaskDelayAction = Actions.delay(0.8f,minimapMaskSequenceAction);
                minimapMask.addAction(minimapMaskDelayAction);
            }
        });

        DelayAction delayAction = Actions.delay(0.3f,runnableAction);
        ParallelAction secondParallelAction = Actions.parallel(secondScaleToAction,secondMoveToAction, delayAction);


        //MiniMap Teleporting Animation - hiding animation step 1
        AlphaAction alphaAction = Actions.alpha(0,0.4f);
        RunnableAction secondRunnableAction = Actions.run(new Runnable() {
            @Override
            public void run() {
                minimap.isOpen = false;
                minimap.setRelativePosition(20,10, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.topAlignment);
                minimap.setScale(0.6f,0.6f);
                minimap.setAnimationOrigin(minimap.getX(), minimap.getY());
                minimap.removeActor(minimap.theSpaceStationLabel);
                beamButton                          .fadeIn(0,50,0.5f,null);
                teleportButton                      .fadeIn(0,50,0.5f,null);
                healthBar.hpBar                     .fadeIn(0,50,0.5f,null);
                hpIndicatorNonUIAnimationHelper     .fadeIn(0,50,0.5f,null);
                PlayerNameNonUIAnimationHelper      .fadeIn(0,50,0.5f,null);
                RoomNameNonUIAnimationHelper        .fadeIn(0,-50,0.5f,null);
                TitleNonUIAnimationHelper           .fadeIn(0,-50,0.5f,null);
                minimap                             .fadeIn(0,-50,0.5f,null);
                roomIndicator.update(nextTeleportingRoomScreen);
                AlphaAction alphaAction = Actions.alpha(0f);
                minimap.mapButton.setVisible(true);
                minimap.mapButton.addAction(alphaAction);
            }
        });
        SequenceAction thirdSequenceAction = Actions.sequence(alphaAction,secondRunnableAction);
        SequenceAction sequenceAction = Actions.sequence(firstParallelAction, secondParallelAction, thirdSequenceAction);
        minimap.addAction(sequenceAction);
    }

    public void hideExceptMiniMap(){
        beamButton                          .fadeOut(0,-50,0.5f,null);
        teleportButton                      .fadeOut(0,-50,0.5f,null);
        healthBar.hpBar                     .fadeOut(0,-50,0.5f,null);
        PlayerNameNonUIAnimationHelper      .fadeOut(0,-50,0.5f,null);
        hpIndicatorNonUIAnimationHelper     .fadeOut(0,-50,0.5f,null);

        RoomNameNonUIAnimationHelper        .fadeOut(0,50,0.5f,null);
        TitleNonUIAnimationHelper           .fadeOut(0,50,0.5f,null);
    }

    public void appearAll(){
        beamButton                          .fadeIn(0,50,0.5f,null);
        teleportButton                      .fadeIn(0,50,0.5f,null);
        healthBar.hpBar                     .fadeIn(0,50,0.5f,null);
        hpIndicatorNonUIAnimationHelper     .fadeIn(0,50,0.5f,null);
        PlayerNameNonUIAnimationHelper      .fadeIn(0,50,0.5f,null);
        RoomNameNonUIAnimationHelper        .fadeIn(0,-50,0.5f,null);
        TitleNonUIAnimationHelper           .fadeIn(0,-50,0.5f,null);
        minimap                             .fadeIn(0,-50,0.5f,null);
    }

    public void hideAll(){
        beamButton                          .fadeOut(0,-50,0.5f,null);
        teleportButton                      .fadeOut(0,-50,0.5f,null);
        healthBar.hpBar                     .fadeOut(0,-50,0.5f,null);
        PlayerNameNonUIAnimationHelper      .fadeOut(0,-50,0.5f,null);
        hpIndicatorNonUIAnimationHelper     .fadeOut(0,-50,0.5f,null);

        RoomNameNonUIAnimationHelper        .fadeOut(0,50,0.5f,null);
        TitleNonUIAnimationHelper           .fadeOut(0,50,0.5f,null);
        minimap                             .fadeOut(0,50,0.5f,null);

        RunnableAction switchScreenAction = Actions.run(new Runnable() {
            @Override
            public void run() {
                //gameEntry.setRoomScreen_Finished();
            }
        });
        //MoveToAction moveToAction = Actions.moveTo(50,50,0.6f);
        DelayAction action = Actions.delay(0.7f);
        AfterAction afterAction = Actions.after(switchScreenAction);
        invisibleActor.addAction(action);
        invisibleActor.addAction(afterAction);
    }

    public void appearWhenMapClose(){
        beamButton                       .fadeIn(0,50,0.5f,null);
        teleportButton                   .fadeIn(0,50,0.5f,null);
        healthBar.hpBar                  .fadeIn(0,50,0.5f,null);
        hpIndicatorNonUIAnimationHelper  .fadeIn(0,50,0.5f,null);
        PlayerNameNonUIAnimationHelper   .fadeIn(0,50,0.5f,null);

        RoomNameNonUIAnimationHelper     .fadeIn(0,-50,0.5f,null);
        TitleNonUIAnimationHelper        .fadeIn(0,-50,0.5f,null);
    }

    public void hideWhenMapOpen(){
        beamButton                       .fadeOut(0,-50,0.5f,null);
        teleportButton                   .fadeOut(0,-50,0.5f,null);
        healthBar.hpBar                  .fadeOut(0,-50,0.5f,null);
        PlayerNameNonUIAnimationHelper   .fadeOut(0,-50,0.5f,null);
        hpIndicatorNonUIAnimationHelper  .fadeOut(0,-50,0.5f,null);

        RoomNameNonUIAnimationHelper     .fadeOut(0,50,0.5f,null);
        TitleNonUIAnimationHelper        .fadeOut(0,50,0.5f,null);
    }

    public RoomScreen getCurrentRoomScreen(){
        return currentRoomScreen;
    }

    public void setCurrentRoomButton(RoomScreen roomScreen){
        currentRoomScreen = roomScreen;
    }

}
