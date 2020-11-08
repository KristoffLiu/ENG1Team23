package com.engoneassessment.game.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.actors.CustomActor;

public class MenuButton extends CustomActor {
    TextureRegion defaultTexture;
    TextureRegion highlightedTexture;
    boolean highlighted;

    public MenuButton(TextureRegion textureRegion,TextureRegion highlightedTextureRegion) {
        super(textureRegion);
        defaultTexture = textureRegion;
        highlightedTexture = highlightedTextureRegion;
        highlighted = false;
    }

    //Switches the texture of the button to be highlighted or not highlighted
    public void switchHighlighted(){
        if(highlighted){
            this.setTextureRegion(defaultTexture);
            highlighted = false;
        }

        else{
            this.setTextureRegion(highlightedTexture);
            highlighted = true;
        }

    }
}
