package com.engoneassessment.game.actors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Invisible extends Hostile {
    private boolean iMode = true; // when mode = true means hostile is currently invisible

    public Invisible(TextureRegion region) {
        super(region);
    }

    public void iModeChange() {
        if (iMode = true) {
            iMode = false;
        }else{
            iMode = true;
        }

    }
}
