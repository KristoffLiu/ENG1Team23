package com.engoneassessment.game.screens.rooms;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.GameEntry;
import com.engoneassessment.game.actors.buildings.ShipSystem;
import com.engoneassessment.game.screens.RoomScreen;


public class OxygenScreen extends RoomScreen {
    private ShipSystem oxygenSystem;

    public OxygenScreen(GameEntry gameEntry, String name, int numNPCs) {
        super(gameEntry, name, numNPCs);
        setWallsTexture(new TextureRegion(new Texture("Rooms/General Square/Oxygen.png")));
        oxygenSystem = new ShipSystem(new TextureRegion(new Texture("Systems/LifeSupport.png")));
        stage.addActor(oxygenSystem);
        oxygenSystem.setPosition(getWalls().getWidth()/2-oxygenSystem.getWidth()/2,getWalls().getHeight()-oxygenSystem.getHeight()*2);

    }

}
