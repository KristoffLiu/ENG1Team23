package com.engoneassessment.game.screens.rooms;

import com.engoneassessment.game.GameEntry;
import com.engoneassessment.game.screens.RoomScreen;
import com.engoneassessment.game.actors.characters.Player;
import com.engoneassessment.game.actors.rooms.Room;
import com.engoneassessment.game.ui.HUD;



public class InfirmaryScreen extends RoomScreen {

    public InfirmaryScreen(GameEntry gameEntry, String name) {
        super(gameEntry);
        this.name = name;
    }
}
