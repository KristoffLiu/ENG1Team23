package com.engoneassessment.game.screens.rooms;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.GameEntry;
import com.engoneassessment.game.actors.buildings.ShipSystem;
import com.engoneassessment.game.screens.RoomScreen;


public class ElectricalScreen extends RoomScreen {
    private ShipSystem electricalSystem;
    public ElectricalScreen(GameEntry gameEntry, String name, int numNPCs) {
        super(gameEntry, name, numNPCs);
        setWallsTexture(new TextureRegion(new Texture("Rooms/General Square/Electrical.png")));
        electricalSystem = new ShipSystem(new TextureRegion(new Texture("Systems/Electrical.png")));
        stage.addActor(electricalSystem);
        electricalSystem.setPosition(getWalls().getWidth()/2-electricalSystem.getWidth()/2,getWalls().getHeight()-electricalSystem.getHeight()*2);
    }
}
