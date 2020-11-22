package com.engoneassessment.game.actors.characters.npcs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.engoneassessment.game.actors.characters.Player;
import com.engoneassessment.game.screens.RoomScreen;

import java.util.Random;

public class Hostile extends NPC {

    private boolean arrested = false; // hostile's initial status is not arrested

    public int NumOfDestroy = 0; // record the number of systems the hostile destroy


    private String ability;

    private boolean abilityActivated;

    private int abilityCooldown;
    private int abilityTimer;

    public Hostile(TextureRegion region, RoomScreen roomScreen, String ability) {
        super(region, roomScreen, new Texture("Characters/other/run/run.png"), new Texture("Characters/other/idle/idle.png"));

        this.ability = ability;
        //Keeps track of if the ability is activated
        abilityActivated = false;
        //Keeps track of how long until the ability can be used again
        abilityCooldown = 10;
        //keeps track of how long the ability has left
        abilityTimer = 0;
    }

    public void useAbility(Player player, Random random){
        if(!abilityActivated) {
            if (ability.equals("Sprint")) {
                System.out.println("Sprint");
                setSpeed(3);
                abilityActivated = true;
                abilityTimer = 100;
                abilityCooldown = 200;
            }
            else if (ability.equals("Invisibility")){
                setVisible(false);
                abilityTimer = 100;
                abilityCooldown = 200;
                abilityActivated = true;
            }
            else if (ability.equals("Teleport")){
                if(checkIfPlayerInRange(player,100)){
                    //Moves the hostile to a random point in the room
                    setPosition(random.nextInt(getCurrentScreen().getMaxX()-getCurrentScreen().getMinX())+getCurrentScreen().getMinX(),random.nextInt(getCurrentScreen().getMaxY()-getCurrentScreen().getMinY())+getCurrentScreen().getMinY());
                    abilityTimer = 100;
                    abilityCooldown = 200;
                    abilityActivated = true;
                }
            }
            
        }
    }

    public void deactivateAbility(){
        abilityActivated = false;
        if (ability.equals("Sprint")){
            setSpeed(0.8f);
        }
        else if (ability.equals("Invisibility")){
            setVisible(true);
        }
    }

    private Boolean checkIfPlayerInRange(Player player,int range){
        //Works out the straight line distance between the player and hostile
        if (Math.sqrt(Math.pow((player.getX()-getX()),2)+Math.pow((player.getY()-getY()),2)) < range){
            return true;
        }
        else{
            return false;
        }
    }

    public void getCaught() {
        arrested = true;
    }


    public int getNumOfDestroy() {
        return NumOfDestroy;
    }

    public int getAbilityCooldown() {
        return abilityCooldown;
    }

    public int getAbilityTimer() {
        return abilityTimer;
    }

    public void setAbilityCooldown(int abilityCooldown) {
        this.abilityCooldown = abilityCooldown;
    }

    public void setAbilityTimer(int abilityTimer) {
        this.abilityTimer = abilityTimer;
    }

    public boolean getAbilityActivated(){
        return abilityActivated;
    }
}
