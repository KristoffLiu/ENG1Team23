package com.engoneassessment.game.actors.characters.npcs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.actors.characters.npcs.NPC;

public class NonHostile extends NPC {
    public NonHostile(TextureRegion region) {
        super(region, new Texture("Characters/other/run/run.png"),
                new Texture("Characters/other/idle/idle.png"));
    }

    public void SelectRandomSystem(){

    }
}
