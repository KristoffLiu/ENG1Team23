package com.engoneassessment.game.screens.rooms;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.GameEntry;
import com.engoneassessment.game.screens.RoomScreen;

public class InfirmaryScreen extends RoomScreen {

    public InfirmaryScreen(GameEntry gameEntry, String name, int numNPCs) {
        super(gameEntry, name, numNPCs);
        setFloorTexture(new TextureRegion(new Texture("Rooms/General Square/TutorialFloor.png")));
    }

    @Override
    public void passiveEffects(float delta) {
        if (auber.getHealth() < 100) {
            auber.setHealth(auber.getHealth()+delta/1);
        }
        super.passiveEffects(delta);
    }
}
