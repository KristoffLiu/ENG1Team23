package com.engoneassessment.game.ui.hud.minimap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.engoneassessment.game.GameEntry;
import com.engoneassessment.game.actors.characters.Player;
import com.engoneassessment.game.screens.RoomScreen;
import com.engoneassessment.game.screens.rooms.*;
import com.engoneassessment.game.ui.UIElement;
import com.engoneassessment.game.ui.UIStage;
import com.engoneassessment.game.ui.controls.Button;
import com.engoneassessment.game.ui.controls.ClickableUIElement;
import com.engoneassessment.game.ui.controls.Image;
import com.engoneassessment.game.ui.controls.labels.LabelStyles;
import com.engoneassessment.game.ui.hud.MapButton;
import com.engoneassessment.game.ui.layouts.UIGroup;

public class MiniMap extends UIGroup {
    GameEntry gameEntry;
    Player auber;
    Array<RoomButton> RoomImages;

    public boolean isOpen;
    boolean isTeleportEnable;

    Image MapBackground;

    RoomButton BrigRoomImage         ;
    RoomButton CargoRoomImage        ;
    RoomButton CommandRoomImage      ;
    RoomButton ElectricalRoomImage   ;
    RoomButton EngineRoomImage       ;
    RoomButton HangerRoomImage       ;
    RoomButton InfirmaryRoomImage    ;
    RoomButton OxygenRoomImage       ;
    RoomButton QuartersRoomImage     ;
    RoomButton WeaponsRoomImage      ;
    Color originalRoomColor     ;

    Label CurrentRoomName;

    public MapButton mapButton;

    public Label theSpaceStationLabel;

    float original_x;
    float original_y;
    float original_width;
    float original_height;
    float closed_x;
    float closed_y;
    float closedScale;
    float opened_x;
    float opened_y;
    float openedScale;




    public MiniMap(UIStage uiStage, GameEntry gameEntry){
        super(uiStage);
        this.gameEntry = gameEntry;
        this.auber = gameEntry.getAuber();

        isOpen = false;
        isTeleportEnable = false;

        closedScale = 0.5f;
        closed_x = uiStage.getWidth() - 20;
        closed_y = uiStage.getHeight() - 10;

        MapBackground         = new Image(this, new TextureRegion(new Texture("Ship/ShipPlain_ThickBorder.png")));

        //room 5
        EngineRoomImage       = new RoomButton(
                this, "EngineRoom",
                new TextureRegion(new Texture("Ship/Room5_Normal.png")),
                new TextureRegion(new Texture("Ship/Room5_Hovered.png")),
                new TextureRegion(new Texture("Ship/Room5_Pressed.png")),
                null
        );
        //room 1
        BrigRoomImage         = new RoomButton(
                this, "BrigRoom",
                new TextureRegion(new Texture("Ship/Room1_Normal.png")),
                new TextureRegion(new Texture("Ship/Room1_Hovered.png")),
                new TextureRegion(new Texture("Ship/Room1_Pressed.png")),
                null
                );
        //room 2
        CargoRoomImage        = new RoomButton(
                this,"CargoRoom",
                new TextureRegion(new Texture("Ship/Room2_Normal.png")),
                new TextureRegion(new Texture("Ship/Room2_Hovered.png")),
                new TextureRegion(new Texture("Ship/Room2_Pressed.png")),
                null
        );
        //room 6
        HangerRoomImage       = new RoomButton(
                this, "HangerRoom",
                new TextureRegion(new Texture("Ship/Room6_Normal.png")),
                new TextureRegion(new Texture("Ship/Room6_Hovered.png")),
                new TextureRegion(new Texture("Ship/Room6_Pressed.png")),
                null
                );
        //room 7
        InfirmaryRoomImage    = new RoomButton(
                this,"InfirmaryRoom",
                new TextureRegion(new Texture("Ship/Room7_Normal.png")),
                new TextureRegion(new Texture("Ship/Room7_Hovered.png")),
                new TextureRegion(new Texture("Ship/Room7_Pressed.png")),
                null
                );
        //room 8
        OxygenRoomImage       = new RoomButton(
                this,"OxygenRoom",
                new TextureRegion(new Texture("Ship/Room8_Normal.png")),
                new TextureRegion(new Texture("Ship/Room8_Hovered.png")),
                new TextureRegion(new Texture("Ship/Room8_Pressed.png")),
                null
                );
        //room 4
        ElectricalRoomImage   = new RoomButton(
                this,"ElectricalRoom",
                new TextureRegion(new Texture("Ship/Room4_Normal.png")),
                new TextureRegion(new Texture("Ship/Room4_Hovered.png")),
                new TextureRegion(new Texture("Ship/Room4_Pressed.png")),
                null
        );
        //room 3
        CommandRoomImage      = new RoomButton(
                this,"CommandRoom",
                new TextureRegion(new Texture("Ship/Room3_Normal.png")),
                new TextureRegion(new Texture("Ship/Room3_Hovered.png")),
                new TextureRegion(new Texture("Ship/Room3_Pressed.png")),
                null
        );

        theSpaceStationLabel  = new Label("The Space Station Map", LabelStyles.usingImpactFontStyle(
                true, 0.5f,
                1,1,1,1
        ));
        //QuartersRoomImage     = new Image(new TextureRegion(new Texture("Ship/Room9.png")));
        //WeaponsRoomImage      = new Image(new TextureRegion(new Texture("Ship/Room0.png")));
        originalRoomColor = BrigRoomImage.getColor();

        mapButton             = new MapButton(this);
        mapButton.setScale(2.0f,2.0f);
        mapButton.getColor().a = 0f;

        mapButton             .setRelativePosition(270 ,180, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.topAlignment);
        MapBackground         .setRelativePosition(0,0, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.topAlignment);

        BrigRoomImage         .setRelativePosition(135,62, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.topAlignment);
        CargoRoomImage        .setRelativePosition(34,159, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.topAlignment);
        CommandRoomImage      .setRelativePosition(135,263, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.topAlignment);
        ElectricalRoomImage   .setRelativePosition(326,251, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.topAlignment);
        EngineRoomImage       .setRelativePosition(326,84, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.topAlignment);
        HangerRoomImage       .setRelativePosition(218,148, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.topAlignment);
        InfirmaryRoomImage    .setRelativePosition(326,161, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.topAlignment);
        OxygenRoomImage       .setRelativePosition(445,161, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.topAlignment);
        theSpaceStationLabel.setPosition(-420,-420);

        original_x      = MapBackground.getX();
        original_y      = MapBackground.getY();
        original_width  = MapBackground.getWidth();
        original_height = MapBackground.getHeight();


        RoomImages = new Array<RoomButton>();
        RoomImages.add(BrigRoomImage         );
        RoomImages.add(CargoRoomImage        );
        RoomImages.add(CommandRoomImage      );
        RoomImages.add(ElectricalRoomImage   );
        RoomImages.add(EngineRoomImage       );
        RoomImages.add(HangerRoomImage       );
        RoomImages.add(InfirmaryRoomImage    );
        RoomImages.add(OxygenRoomImage       );
        this.addActor(MapBackground       );
        this.setScale(closedScale,closedScale);
    }

    public boolean isTeleportEnable() {
        return isTeleportEnable;

    public void setTeleportEnable(boolean teleportEnable) {
        isTeleportEnable = teleportEnable;

    public void OpenMap(float duration){
        isOpen = true;
        float target_scale = 1.6f;
        float target_width = original_width * target_scale;
        float target_height = original_width * target_scale;
        float target_x = ((UIStage)getUIParent()).getWidth()/2 + target_width / 2;
        float target_y = ((UIStage)getUIParent()).getHeight()/2 + target_height / 2;



        Interpolation interpolation = Interpolation.pow3;
        ScaleToAction scaleToAction = Actions.scaleTo(
                target_scale,
                target_scale,
                duration,
                interpolation);
        MoveToAction moveToAction = Actions.moveTo(
                target_x,
                target_y,
                duration,
                interpolation);
        this.setColor(0,0,0,1);

        ParallelAction parallelAction = Actions.parallel(scaleToAction, moveToAction);
        RunnableAction runnableAction = Actions.run(new Runnable() {
            @Override
            public void run() {
                setAnimationOrigin(getX(), getY());

            }
        });
        SequenceAction sequenceAction = Actions.sequence(parallelAction, runnableAction);
        this.addAction(sequenceAction);

        this.addActor(theSpaceStationLabel);

        VisibleAction mapButtonVisibleAction = Actions.visible(false);
        mapButton.addAction(mapButtonVisibleAction);

    }

    public void CloseMap(float duration){
        isOpen = false;
        Interpolation interpolation = Interpolation.pow3;
        ScaleToAction scaleToAction = Actions.scaleTo(
                closedScale,
                closedScale,
                duration,
                interpolation);
        MoveToAction moveToAction = Actions.moveTo(
                closed_x,
                closed_y,
                duration,
                interpolation);
        this.addAction(scaleToAction);
        this.addAction(moveToAction);
        this.removeActor(theSpaceStationLabel);

        AlphaAction alphaAction = Actions.alpha(0f);
        mapButton.setVisible(true);
        mapButton.addAction(alphaAction);
        setAnimationOrigin(getX(), getY());
    }

    public void setSelectedRoomImage(RoomButton roomButton){
        for (RoomButton roomImage : RoomImages ) {
            if(roomImage == roomButton){
                roomImage.isCurrentRoom(true);
                roomImage.setButtonUIState(ClickableUIElement.ButtonUIState.pressed);
            }
            else{
                roomImage.isCurrentRoom(false);
                roomImage.setButtonUIState(ClickableUIElement.ButtonUIState.normal);
            }
        }
    }

    public void setSelectedRoomImage(RoomScreen screen){
        for (RoomButton roomImage : RoomImages ) {
            if(roomImage.getRoomScreen() == screen){
                roomImage.isCurrentRoom(true);
                roomImage.setButtonUIState(ClickableUIElement.ButtonUIState.pressed);
            }
            else{
                roomImage.isCurrentRoom(false);
                roomImage.setButtonUIState(ClickableUIElement.ButtonUIState.normal);
            }
        }
    }

    public RoomButton getSelectedRoomImage(){
        for (RoomButton roomImage : RoomImages ) {
            if(roomImage.isCurrentRoom()){
                return roomImage;
            }
        }
        return InfirmaryRoomImage;
    }
}
