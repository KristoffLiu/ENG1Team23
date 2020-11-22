package com.engoneassessment.game.actors.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.engoneassessment.game.GameEntry;
import com.engoneassessment.game.actors.characters.npcs.Hostile;
import com.engoneassessment.game.actors.characters.npcs.NPC;
import com.engoneassessment.game.screens.RoomScreen;

import java.util.Random;

public class Player extends Character{
    float speed;
    float autoSpeed;
    private NPC chosenNPC;
    boolean NPCChosen;
    private Array<RoomScreen> sabotageList;
    boolean movingNextRoom;
    public Player(TextureRegion region, RoomScreen screen) {
        super(region,screen, new Texture("Characters/Auber/run/run.png"),
                new Texture("Characters/Auber/idle/idle.png"));
        speed = 800;
        autoSpeed = 8f;
        NPCChosen = false;
        movingNextRoom = true;
        sabotageList = new Array<>();
    }

    public float getSpeed(){
        return speed;
    }

    public void autoMove(Random random, GameEntry gameEntry){
        //Moves the player to the teleporter and teleports them to the next sabotaged room
        if (movingNextRoom && sabotageList.size > 0) {
            //The angle the player needs to move at to go straight to the Teleporter
            double movementAngle = Math.atan2(getY() - getCurrentScreen().getTeleporter().getY(), getX() - getCurrentScreen().getTeleporter().getX());
            moveBy(-(float) Math.cos(movementAngle) * autoSpeed, -(float) Math.sin(movementAngle) * autoSpeed);
            if(getBounds().overlaps(getCurrentScreen().getTeleporter().getBounds())){
                getCurrentScreen().hudStage.teleport(sabotageList.get(0));
                movingNextRoom = false;
            }
        }

        else if(sabotageList.size>0){
            //Chooses a new NPC in the room to move towards if there is no NPC currently selected or the NPC is on a different screen
            if (!NPCChosen || chosenNPC.getCurrentScreen() != getCurrentScreen()) {
                int randomChance = random.nextInt(10);
                if (getCurrentScreen().nonHostiles.size > 0 && (randomChance < 9|| getCurrentScreen().hostiles.size == 0)) {
                    chosenNPC = getCurrentScreen().nonHostiles.random();
                    NPCChosen = true;
                } else if (randomChance > 7 && getCurrentScreen().hostiles.size > 0) {
                    chosenNPC = getCurrentScreen().hostiles.random();
                    NPCChosen = true;
                }
            } else {
                //The angle the player needs to move at to go straight to the NPC
                double movementAngle = Math.atan2(getY() - chosenNPC.getY(), getX() - chosenNPC.getX());
                moveBy(-(float) Math.cos(movementAngle) * autoSpeed, -(float) Math.sin(movementAngle) * autoSpeed);
                if (getBounds().overlaps(chosenNPC.getBounds())) {
                    if (chosenNPC instanceof Hostile) {
                        gameEntry.beamHostiles();
                        movingNextRoom = true;
                        sabotageList.removeIndex(0);
                    }
                    NPCChosen = false;
                }
            }
        }

    }

    public void addSabotaged(RoomScreen screen){
        sabotageList.add(screen);
    }
}
