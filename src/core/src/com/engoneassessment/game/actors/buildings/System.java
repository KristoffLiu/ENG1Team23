package com.engoneassessment.game.actors.buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class System extends Building {

    public System(TextureRegion textureRegion) {
        super(textureRegion);
    }

    public Boolean isDestroyed(){
        return true;
    }

    public float timeTillDestroyed(){
        return 0f;
    }
}
