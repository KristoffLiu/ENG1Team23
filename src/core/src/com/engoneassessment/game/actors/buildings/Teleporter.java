package com.engoneassessment.game.actors.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Teleporter extends Building {
    public Teleporter() {
        super(new TextureRegion(new Texture("Systems/Teleporter.png")));
    }
}
