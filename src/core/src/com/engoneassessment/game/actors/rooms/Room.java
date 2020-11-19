package com.engoneassessment.game.actors.rooms;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.engoneassessment.game.actors.CustomActor;

/**
 * Created by Zhikang Liu;
 */
public class Room extends CustomActor {
    public String name;
    public Array<System> systemsInRoom;
    public Texture texture;

    public Room(TextureRegion textureRegion) {
        super(textureRegion);
    }

    public void setTextureRegion(Texture texture) {
        this.texture = texture;
    }
}
