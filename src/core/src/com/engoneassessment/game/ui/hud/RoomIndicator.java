package com.engoneassessment.game.ui.hud;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.engoneassessment.game.screens.RoomScreen;
import com.engoneassessment.game.ui.UIStage;
import com.engoneassessment.game.ui.controls.labels.LabelStyles;

public class RoomIndicator {
    Label Title;
    Label RoomName;

    public RoomIndicator(UIStage stage){
        //Init Name of the Title
        Title = new Label("ROOM",
                LabelStyles.usingImpactFontStyle(
                        true, 0.8f,
                        1,1,1,1
                ));

        Title.setPosition(20, stage.getHeight() - 70);

        //Init the room name
        RoomName = new Label(
                "Name Of The Room",
                LabelStyles.usingImpactFontStyle(
                        true, 0.6f,
                        1,1,1,1
                ));
        RoomName.setPosition(20, stage.getHeight() - 105);
    }

    public void update(RoomScreen room){
        RoomName.setText(room.getName());
    }
}
