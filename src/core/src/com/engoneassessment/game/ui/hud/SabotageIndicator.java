package com.engoneassessment.game.ui.hud;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.engoneassessment.game.screens.RoomScreen;
import com.engoneassessment.game.ui.UIStage;
import com.engoneassessment.game.ui.controls.labels.LabelStyles;

public class SabotageIndicator {
    Label RoomName;
    public SabotageIndicator(UIStage stage){
        //Init the room name
        RoomName = new Label(
                "",
                LabelStyles.usingImpactFontStyle(
                        true, 0.6f,
                        1,1,1,1
                ));
        RoomName.setPosition(stage.getWidth()/2-200, stage.getHeight() - 70);
        }
        public void update(RoomScreen room){
        RoomName.setText(room.getName()+" Has Been Sabotaged");
    }
}
