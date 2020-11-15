package com.engoneassessment.game.actors.characters.npcs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.engoneassessment.game.actors.buildings.Building;
import com.engoneassessment.game.actors.characters.Character;
import com.engoneassessment.game.actors.rooms.Room;

public class NPC extends Character {

    private System targetSystem;
    private Room targetRoom;
    private Array<Float> path;

    public NPC(TextureRegion textureRegion, Texture runTexture, Texture idleTexture) {
        super(textureRegion, runTexture, idleTexture);
    }

    public void setTargetSystem(System target){

    }

    public Boolean checkTarget(Building target){
        return true;
    }

    public void setTargetRoom(){

    }

    public Boolean checkTargetRoom(Room target){
        return true;
    }

    private void workOutPathToSystem(){

    }
}
