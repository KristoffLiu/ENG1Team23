package com.engoneassessment.game.actors.charactors.npcs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.engoneassessment.game.actors.buildings.Building;
import com.engoneassessment.game.actors.Character;
import com.engoneassessment.game.actors.rooms.Room;

public class NPC extends Character {

    private System targetSystem;
    private Room targetRoom;
    private Array<Float> path;

    public NPC(TextureRegion textureRegion) {
        super(textureRegion);
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
