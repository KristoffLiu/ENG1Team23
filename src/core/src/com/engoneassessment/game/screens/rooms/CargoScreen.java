package com.engoneassessment.game.screens.rooms;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.GameEntry;
import com.engoneassessment.game.screens.RoomScreen;


public class CargoScreen extends RoomScreen {

    public CargoScreen(GameEntry gameEntry, String name, int numNPCs) {
        super(gameEntry, name, numNPCs);
        setWallsTexture(new TextureRegion(new Texture("Rooms/General Square/Cargo.png")));

    }
}
