package com.engoneassessment.game.actors.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.actors.characters.Player;
import com.engoneassessment.game.ui.hud.HUDStage;

public class Teleporter extends Building {
    public Teleporter() {
        super(new TextureRegion(new Texture("Systems/Teleporter.png")));
    }

    /**
     * Checks if the player is on top of the teleporter
     * @param auber player character
     * @param hud hud overlay
     */
    public void checkOverlap(Player auber, HUDStage hud){
        if(getBounds().overlaps(auber.getBounds())){
            hud.getMinimap().setTeleportEnable(true);
        }
        else if(hud.getMinimap().isTeleportEnable()){
            hud.getMinimap().setTeleportEnable(false);
        }
    }
}

