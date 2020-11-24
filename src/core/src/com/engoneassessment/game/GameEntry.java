package com.engoneassessment.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.Array;

import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.engoneassessment.game.actors.characters.Player;
import com.engoneassessment.game.actors.characters.npcs.Hostile;
import com.engoneassessment.game.screens.RoomScreen;
import com.engoneassessment.game.screens.end.EndScreen;
import com.engoneassessment.game.screens.rooms.*;
import com.engoneassessment.game.screens.start.StartScreen;
import com.engoneassessment.game.ui.hud.HUDStage;

import java.util.Random;

/**
 * Since it may not only be a single screen/stage game,
 * then we should extend the whole game based on Game class,
 * instead of extending from ApplicationAdaptor interface.
 * This allows it to switch the screens/stages,
 * and control their rendering process from this top class.
 *
 * - Zhikang Liu 2020/11/01
 */

public class GameEntry extends Game {

    public static GameEntry current;

    public static final float VIEW_WIDTH = 1920;
    public static final float VIEW_HEIGHT = 1080;

    private Screen currentScreen;
    private RoomScreen currentRoomScreen;

    private Player auber;
    public HUDStage hudStage;

    //Screens Objects
    private StartScreen startScreen;
    private EndScreen endScreen;

    //All the room screens
    private CargoScreen cargoScreen;
    private CommandScreen commandScreen;
    private ElectricalScreen electricalScreen;
    private EngineScreen engineScreen;
    private HangerScreen hangerScreen;
    private InfirmaryScreen infirmaryScreen;
    private OxygenScreen oxygenScreen;
    private BrigScreen brigScreen;

    private long spawnTime;

    public Stage stage;

    private static Random random;

    private Array<RoomScreen> spawnableScreens;

    private Array<String> abilities;

    private int numHostiles;

    private int caughtHostiles;

    private int sabotagedSystems;

    private boolean gameWon;

    private boolean demo;

    /**
     * Called when the game is first created.
     */
    @Override
    public void create() {
        numHostiles = 0;
        sabotagedSystems = 0;
        caughtHostiles = 0;
        gameWon = false;
        current = this;
        //Stores the time the last hostile was spawned
        spawnTime = System.currentTimeMillis();
        //Used for generating random numbers
        random = new Random();

        //Creates the initial auber
        auber = new Player(new TextureRegion(new Texture("Characters/Auber/idle/idle.gif")), null);
        hudStage = new HUDStage(new StretchViewport(VIEW_WIDTH, VIEW_HEIGHT), auber, this);

        // Create StartScreen
        startScreen = new StartScreen(this);

        //Creates EndScreen
        endScreen = new EndScreen(this);

        //Create Cargo Bay Screen, gameEntry, screen name, number of NPCS
        cargoScreen = new CargoScreen(this, "Cargo", 15);

        //Create Command Screen, gameEntry, screen name, number of NPCS
        commandScreen = new CommandScreen(this, "Command", 15);

        //Create Electrical Screen, gameEntry, screen name, number of NPCS
        electricalScreen = new ElectricalScreen(this, "Electrical", 15);

        //Create Engine Screen, gameEntry, screen name, number of NPCS
        engineScreen = new EngineScreen(this, "Engine", 15);

        //Create Hanger Screen, gameEntry, screen name, number of NPCS
        hangerScreen = new HangerScreen(this, "Hanger", 15);

        //Create Infirmary Screen, gameEntry, screen name, number of NPCS
        infirmaryScreen = new InfirmaryScreen(this, "Infirmary", 0);

        //Create Oxygen Screen, gameEntry, screen name, number of NPCS
        oxygenScreen = new OxygenScreen(this, "Oxygen", 15);


        //Create Brig Screen, gameEntry, screen name, number of NPCS
        brigScreen = new BrigScreen(this, "Brig", 0);

        //spawnable screens keeps track of the screens enemies can spawn on
        spawnableScreens = new Array<>();
        spawnableScreens.addAll(oxygenScreen, hangerScreen, electricalScreen, engineScreen, cargoScreen, commandScreen);

        //Creates the abilities and the abilities array for the hostile to randomly select
        abilities = new Array<>();
        abilities.addAll("Invisibility", "Sprint", "Teleport");

        setScreen(startScreen);
    }


    //Changes the current screen to the one passed in
    @Override
    public void setScreen(Screen nextScreen) {
        if(getCurrentScreen() != nextScreen){
            if(getCurrentScreen() instanceof StartScreen){
                hudStage.updateRoomName((RoomScreen) nextScreen);
            }
            super.setScreen(nextScreen);
            setCurrentScreen(nextScreen);
            if(nextScreen instanceof RoomScreen){
                setCurrentRoomScreen((RoomScreen) nextScreen);
                auber.setCurrentScreen((RoomScreen)nextScreen);
            }
        }
    }

    public void endGame(){
        setScreen(endScreen);
    }

    public void setScreenFromStartScreen(Screen nextScreen){
        setScreen(nextScreen);

    }

    public void setRoomScreen(Screen nextScreen) {
        if(this.getCurrentScreen() != nextScreen){
            hudStage.teleport(nextScreen);
        }
    }

    public void setRoomScreen_Finished(Screen nextScreen){
        setScreen(nextScreen);
    }

    @Override
    public void dispose() {
        // super.dispose() I think you can't remove this line,
        // because there's some essential action in superclass's method.
        super.dispose();


        // When the game finished, dispose everything
        if (startScreen != null) {
            startScreen.dispose();
            startScreen = null;
        }
    }

    public BrigScreen getBrigScreen() {
        return brigScreen;
    }

    public CargoScreen getCargoScreen() {
        return cargoScreen;
    }

    public CommandScreen getCommandScreen() {
        return commandScreen;
    }

    public ElectricalScreen getElectricalScreen() {
        return electricalScreen;
    }

    public EngineScreen getEngineScreen() {
        return engineScreen;
    }

    public HangerScreen getHangerScreen() {
        return hangerScreen;    }

    public InfirmaryScreen getInfirmaryScreen() {
        return infirmaryScreen;
    }

    public OxygenScreen getOxygenScreen() {
        return oxygenScreen;
    }


    public StartScreen getStartScreen() {
        return startScreen;
    }

    public Player getAuber() {
        return auber;
    }

    public void setAuber(Player auber) {
        this.auber = auber;
    }

    public static Random getRandom() {
        return random;
    }

    public long getSpawnTime() {
        return spawnTime;
    }

    public void setSpawnTime(long spawnTime) {
        this.spawnTime = spawnTime;
    }

    public void sabotage(){
        sabotagedSystems += 1;
        //If 15 systems have been sabotaged end the game
        if(sabotagedSystems == 15){
            gameWon = false;
            System.out.println("Lost");
            endGame();
        }
        //Picks a random screen to sabotage
        RoomScreen sabotagedRoom = spawnableScreens.random();
        //Tells the player which room has been sabotaged
        hudStage.updateLastSabotaged(sabotagedRoom);
        sabotagedRoom.setSabotaged(true);
        auber.addSabotaged(sabotagedRoom);

        if(numHostiles < 8) {
            //Creates a new hostile to spawn
            Hostile hostile = new Hostile(new TextureRegion(new Texture("Characters/Other/idle/idle.gif")), sabotagedRoom, abilities.random());
            //Adds the hostile to the room and moves it to the location of a non hostile in the room
            sabotagedRoom.hostiles.add(hostile);
            hostile.setPosition(sabotagedRoom.nonHostiles.get(sabotagedRoom.nonHostiles.size - 1).getX(), sabotagedRoom.nonHostiles.get(sabotagedRoom.nonHostiles.size - 1).getY());
            //Removes the non hostile that got replaced from the room
            sabotagedRoom.nonHostiles.pop().remove();
            sabotagedRoom.stage.addActor(hostile);
            numHostiles += 1;
        }
    }

    public int getNumHostiles() {
        return numHostiles;
    }

    public int getSabotagedSystems() {
        return sabotagedSystems;
    }

    public int getCaughtHostiles() {
        return caughtHostiles;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void beamHostiles() {
        if(getCurrentScreen()!=brigScreen && getCurrentScreen()!=infirmaryScreen){
            for (Hostile hostile : auber.getCurrentScreen().hostiles) {
                if (auber.getBounds().overlaps(hostile.getBounds())) {
                    hostile.remove();
                    auber.getCurrentScreen().hostiles.removeValue(hostile,true);
                    //Creates a new hostile to spawn
                    Hostile new_hostile = new Hostile(new TextureRegion(new Texture("Characters/Other/idle/idle.gif")), brigScreen, abilities.random());
                    //Adds the hostile to the room and moves it to the location of a non hostile in the room
                    brigScreen.hostiles.add(new_hostile);
                    //Random X, random Y above room
                    new_hostile.setPosition(random.nextInt(brigScreen.getMaxX() - brigScreen.getMinX()) + brigScreen.getMinX(), random.nextInt(brigScreen.getMaxY() - brigScreen.getMinY()) + brigScreen.getMaxY()+(brigScreen.getMaxY() - brigScreen.getMinY()));
                    brigScreen.stage.addActor(hostile);

                    caughtHostiles += 1;
                }
            }
            if (caughtHostiles == 8) {
                endScreen.updateGameWon();
                endGame();
            }
        }
    }

    public Screen getCurrentScreen() {
        return currentScreen;
    }

    private void setCurrentScreen(Screen nextScreen) {
        currentScreen = nextScreen;
    }


    public RoomScreen getCurrentRoomScreen(){
        return currentRoomScreen;
    }

    public void setCurrentRoomScreen(RoomScreen roomScreen){
        currentRoomScreen = roomScreen;
    }

    public boolean isDemo() {
        return demo;
    }

    public void setDemo(boolean demo) {
        this.demo = demo;
    }

}

