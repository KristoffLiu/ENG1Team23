package com.engoneassessment.game.ui.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.engoneassessment.game.actors.characters.Player;
import com.engoneassessment.game.screens.RoomScreen;
import com.engoneassessment.game.screens.rooms.*;
import com.engoneassessment.game.ui.UIElement;
import com.engoneassessment.game.ui.UIStage;
import com.engoneassessment.game.ui.controls.Image;
import com.engoneassessment.game.ui.controls.labels.LabelStyles;
import com.engoneassessment.game.ui.layouts.UIGroup;

public class Minimap extends UIGroup {
    Player auber;
    Array<Image> RoomImages;

    Image MapBackground;

    Image BrigRoomImage         ;
    Image CargoRoomImage        ;
    Image CommandRoomImage      ;
    Image ElectricalRoomImage   ;
    Image EngineRoomImage       ;
    Image HangerRoomImage       ;
    Image InfirmaryRoomImage    ;
    Image OxygenRoomImage       ;
    Image QuartersRoomImage     ;
    Image WeaponsRoomImage      ;
    Color originalRoomColor     ;

    public MapButton mapButton;

    Label theSpaceStationLabel;


    float closed_x;
    float closed_y;
    float closedScale;
    float opened_x;
    float opened_y;
    float openedScale;

    public Minimap(UIStage uiStage, Player auber){
        super(uiStage);
        this.auber = auber;

        closedScale = 0.5f;
        closed_x = uiStage.getWidth() - 20;
        closed_y = uiStage.getHeight() - 10;

        openedScale = 1.6f;
        opened_x = ((UIStage)getUIParent()).getWidth()/2 + 400;
        opened_y = ((UIStage)getUIParent()).getHeight()/2 + 350;

        MapBackground         = new Image(this, new TextureRegion(new Texture("Ship/ShipPlain_ThickBorder.png")));

        BrigRoomImage         = new Image(this, new TextureRegion(new Texture("Ship/Room1.png")));
        CargoRoomImage        = new Image(this, new TextureRegion(new Texture("Ship/Room2.png")));
        CommandRoomImage      = new Image(this, new TextureRegion(new Texture("Ship/Room3.png")));
        ElectricalRoomImage   = new Image(this, new TextureRegion(new Texture("Ship/Room4.png")));
        EngineRoomImage       = new Image(this, new TextureRegion(new Texture("Ship/Room5.png")));
        HangerRoomImage       = new Image(this, new TextureRegion(new Texture("Ship/Room6.png")));
        InfirmaryRoomImage    = new Image(this, new TextureRegion(new Texture("Ship/Room7.png")));
        OxygenRoomImage       = new Image(this, new TextureRegion(new Texture("Ship/Room8.png")));
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
        BrigRoomImage         .setRelativePosition(0,0, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.topAlignment);
        CargoRoomImage        .setRelativePosition(0,0, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.topAlignment);
        CommandRoomImage      .setRelativePosition(0,0, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.topAlignment);
        ElectricalRoomImage   .setRelativePosition(0,0, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.topAlignment);
        EngineRoomImage       .setRelativePosition(0,0, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.topAlignment);
        HangerRoomImage       .setRelativePosition(0,0, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.topAlignment);
        InfirmaryRoomImage    .setRelativePosition(0,0, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.topAlignment);
        OxygenRoomImage       .setRelativePosition(0,0, UIElement.HorizontalAlignment.rightAlignment, UIElement.VerticalAlignment.topAlignment);
        theSpaceStationLabel.setPosition(-420,-420);

        RoomImages = new Array<Image>();
        RoomImages.add(BrigRoomImage         );
        RoomImages.add(CargoRoomImage        );
        RoomImages.add(CommandRoomImage      );
        RoomImages.add(ElectricalRoomImage   );
        RoomImages.add(EngineRoomImage       );
        RoomImages.add(HangerRoomImage       );
        RoomImages.add(InfirmaryRoomImage    );
        RoomImages.add(OxygenRoomImage       );

        this.addActor(MapBackground       );
        this.addActor(BrigRoomImage       );
        this.addActor(CargoRoomImage      );
        this.addActor(CommandRoomImage    );
        this.addActor(ElectricalRoomImage );
        this.addActor(EngineRoomImage     );
        this.addActor(HangerRoomImage     );
        this.addActor(InfirmaryRoomImage  );
        this.addActor(OxygenRoomImage     );
        this.setScale(closedScale,closedScale);
    }

    public void OpenMap(float duration){
        ScaleToAction scaleToAction = Actions.scaleTo(
                openedScale,
                openedScale,
                duration);
        MoveToAction moveToAction = Actions.moveTo(
                opened_x,
                opened_y,
                duration);
        this.setColor(0,0,0,1);

        this.addAction(scaleToAction);
        this.addAction(moveToAction);
        this.addActor(theSpaceStationLabel);
    }

    public void CloseMap(float duration){
        ScaleToAction scaleToAction = Actions.scaleTo(
                closedScale,
                closedScale,
                duration);
        MoveToAction moveToAction = Actions.moveTo(
                closed_x,
                closed_y,
                duration);
        this.addAction(scaleToAction);
        this.addAction(moveToAction);
        this.removeActor(theSpaceStationLabel);
    }



    public void Teleport(){
        RoomScreen roomScreen = auber.getCurrentScreen();
        Actor teleportedLocation;
        if (roomScreen instanceof BrigScreen){
            teleportedLocation = BrigRoomImage;
        }
        else if (roomScreen instanceof CargoScreen){
            teleportedLocation = CargoRoomImage;
        }
        else if (roomScreen instanceof CommandScreen){
            teleportedLocation = CommandRoomImage;
        }
        else if (roomScreen instanceof ElectricalScreen){
            teleportedLocation = ElectricalRoomImage;
        }
        else if (roomScreen instanceof EngineScreen){
            teleportedLocation = EngineRoomImage;
        }
        else if (roomScreen instanceof HangerScreen){
            teleportedLocation = HangerRoomImage;
        }
        else if (roomScreen instanceof InfirmaryScreen){
            teleportedLocation = InfirmaryRoomImage;
        }
        else{
            teleportedLocation = BrigRoomImage;
        }
//        else if (roomScreen instanceof QuartersScreen){
//
//        }
//        else if (roomScreen instanceof WeaponsScreen){
//
//        }
        for (Image roomImage : RoomImages ) {
            if(roomImage == teleportedLocation){
                roomImage.setColor(1,1,1,1);
            }
            else{
                roomImage.getColor().set(new Color(originalRoomColor));
                roomImage.getColor().a = 0.3f;
            }
        }
    }

}
