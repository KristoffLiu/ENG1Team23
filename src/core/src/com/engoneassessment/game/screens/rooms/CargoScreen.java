package com.engoneassessment.game.screens.rooms;

import com.engoneassessment.game.GameEntry;
import com.engoneassessment.game.screens.RoomScreen;


public class CargoScreen extends RoomScreen {
    String name;

    public CargoScreen(GameEntry gameEntry, String name) {
        super(gameEntry);
        this.name = name;
    }
}
