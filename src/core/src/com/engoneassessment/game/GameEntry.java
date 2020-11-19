package com.engoneassessment.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;

import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.engoneassessment.game.actors.characters.Player;
import com.engoneassessment.game.actors.characters.npcs.Hostile;
import com.engoneassessment.game.screens.RoomScreen;
import com.engoneassessment.game.screens.end.EndScreen;
import com.engoneassessment.game.screens.rooms.*;
import com.engoneassessment.game.screens.setting.SettingScreen;
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

    public static final float VIEW_WIDTH = 1920;
    public static final float VIEW_HEIGHT = 1080;

    private Player auber;
    private Screen CurrentScreen;
    public HUDStage hudStage;

    private InputListener inputHandler;

    //Screens Objects
    private StartScreen startScreen;
    private EndScreen endScreen;
    private SettingScreen settingScreen;

    //All the room screens
    private CargoScreen cargoScreen;
    private CommandScreen commandScreen;
    private ElectricalScreen electricalScreen;
    private EngineScreen engineScreen;
    private HangerScreen hangerScreen;
    private InfirmaryScreen infirmaryScreen;
    private OxygenScreen oxygenScreen;
    private QuartersScreen quartersScreen;
    private WeaponsScreen weaponsScreen;
    private BrigScreen brigScreen;

    private long spawnTime;

    public Stage stage;

    private Label.LabelStyle style;

    private static Random random;

    private Array<RoomScreen> spawnableScreens;

    private Array<String> abilities;

    private int numHostiles;

    private int caughtHostiles;

    private int sabotagedSystems;

    private boolean gameWon;

    /**
     * Called when the game is first created.
     */
    @Override
    public void create() {
        numHostiles = 0;
        sabotagedSystems = 0;
        caughtHostiles = 0;
        //Used for generating random numbers
        random = new Random();
        //Creates the input handler for keyboard based events
        inputHandler = new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.NUM_1) {
                    setScreen(cargoScreen);
                    auber.setCurrentScreen(cargoScreen);
                }

                if (keycode == Input.Keys.NUM_2) {
                    setScreen(commandScreen);
                    auber.setCurrentScreen(commandScreen);
                }

                if (keycode == Input.Keys.NUM_3) {
                    setScreen(electricalScreen);
                    auber.setCurrentScreen(electricalScreen);
                }

                if (keycode == Input.Keys.NUM_4) {
                    setScreen(engineScreen);
                    auber.setCurrentScreen(engineScreen);
                }

                if (keycode == Input.Keys.NUM_5) {
                    setScreen(hangerScreen);
                    auber.setCurrentScreen(hangerScreen);
                }

                if (keycode == Input.Keys.NUM_6) {
                    setScreen(infirmaryScreen);
                    auber.setCurrentScreen(infirmaryScreen);
                }

                if (keycode == Input.Keys.NUM_7) {
                    setScreen(oxygenScreen);
                    auber.setCurrentScreen(oxygenScreen);
                }

                if (keycode == Input.Keys.NUM_8) {
                    setScreen(quartersScreen);
                    auber.setCurrentScreen(quartersScreen);
                }

                if (keycode == Input.Keys.NUM_9) {
                    setScreen(weaponsScreen);
                    auber.setCurrentScreen(weaponsScreen);
                }

                if (keycode == Input.Keys.NUM_0) {
                    setScreen(brigScreen);
                    auber.setCurrentScreen(brigScreen);
                }

                if (keycode == Input.Keys.SPACE) {
                    for (Hostile hostile : auber.getCurrentScreen().hostiles) {
                        if (auber.getBounds().overlaps(hostile.getBounds())) {
                            hostile.remove();
                            //Creates a new hostile to spawn
                            Hostile new_hostile = new Hostile(new TextureRegion(new Texture("Characters/other/idle/idle.gif")), brigScreen, abilities.random());
                            //Adds the hostile to the room and moves it to the location of a non hostile in the room
                            brigScreen.hostiles.add(new_hostile);
                            new_hostile.setPosition(random.nextInt(brigScreen.getMaxX() - brigScreen.getMinX()) + brigScreen.getMinX(), random.nextInt(brigScreen.getMaxY() - brigScreen.getMinY()) + brigScreen.getMinY());
                            brigScreen.stage.addActor(hostile);

                            caughtHostiles += 1;
                        }
                    }
                    if(caughtHostiles == 8){
                        endScreen.updateGameWon();
                        endGame();
                    }
                }

                if (keycode == Input.Keys.M){
                    setScreen(endScreen);
                }

                return super.keyDown(event, keycode);
            }
        };

        //Creates the initial auber
        auber = new Player(new TextureRegion(new Texture("Characters/auber/idle/idle.gif")), null);
        hudStage = new HUDStage(new StretchViewport(this.VIEW_WIDTH, this.VIEW_HEIGHT), auber, this);

        // Create StartScreen
        startScreen = new StartScreen(this);

        //Creates EndScreen
        endScreen = new EndScreen(this);

        //Create Cargo Bay Screen
        cargoScreen = new CargoScreen(this, "Cargo", 10);

        //Create Command Screen
        commandScreen = new CommandScreen(this, "Command", 10);

        //Create Electrical Screen
        electricalScreen = new ElectricalScreen(this, "Electrical", 10);

        //Create Engine Screen
        engineScreen = new EngineScreen(this, "Engine", 10);

        //Create Hanger Screen
        hangerScreen = new HangerScreen(this, "Hanger", 10);

        //Create Infirmary Screen
        infirmaryScreen = new InfirmaryScreen(this, "Infirmary", 0);

        //Create Oxygen Screen
        oxygenScreen = new OxygenScreen(this, "Oxygen", 10);

        //Create Quarters Screen
        quartersScreen = new QuartersScreen(this, "Quarters", 10);

        //Create Weapons Screen
        weaponsScreen = new WeaponsScreen(this, "Weapons", 10);

        //Create Brig Screen
        brigScreen = new BrigScreen(this, "Brig", 0);

        //spawnable screens keeps track of the screens enemies can spawn on
        spawnableScreens = new Array<>();
        spawnableScreens.addAll(weaponsScreen, quartersScreen, oxygenScreen, hangerScreen, electricalScreen, engineScreen, cargoScreen, commandScreen);

        //Creates the abilities and the abilities array for the hostile to randomly select
        abilities = new Array<>();
        abilities.addAll("Invisibility", "Sprint", "Teleport");

        setScreen(startScreen);
    }

    //Changes the current screen to the one passed in
    @Override
    public void setScreen(Screen nextScreen) {
        super.setScreen(nextScreen);
        if (CurrentScreen != nextScreen && RoomScreen.class.isInstance(nextScreen)) {
            hudStage.updateRoomName((RoomScreen) nextScreen);
        }
    }

    public void endGame(){
        if(caughtHostiles == 7){
            gameWon = true;
        }
        setScreen(endScreen);
    }

    public Screen getCurrentScreen() {
        return CurrentScreen;
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
        return hangerScreen;
    }

    public InfirmaryScreen getInfirmaryScreen() {
        return infirmaryScreen;
    }

    public OxygenScreen getOxygenScreen() {
        return oxygenScreen;
    }

    public QuartersScreen getQuartersScreen() {
        return quartersScreen;
    }

    public WeaponsScreen getWeaponsScreen() {
        return weaponsScreen;
    }

    public StartScreen getStartScreen() {
        return startScreen;
    }

    public InputListener getKeyboardInputHandler() {
        return inputHandler;
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
            endGame();
        }
        //Picks a random screen to sabotage
        RoomScreen sabotagedRoom = spawnableScreens.random();
        //Tells the player which room has been sabotaged
        hudStage.updateLastSabotaged(sabotagedRoom);
        sabotagedRoom.setSabotaged(true);
        if(numHostiles < 8) {
            //Creates a new hostile to spawn
            Hostile hostile = new Hostile(new TextureRegion(new Texture("Characters/other/idle/idle.gif")), sabotagedRoom, abilities.random());
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
                    //Creates a new hostile to spawn
                    Hostile new_hostile = new Hostile(new TextureRegion(new Texture("Characters/other/idle/idle.gif")), brigScreen, abilities.random());
                    //Adds the hostile to the room and moves it to the location of a non hostile in the room
                    brigScreen.hostiles.add(new_hostile);
                    new_hostile.setPosition(random.nextInt(brigScreen.getMaxX() - brigScreen.getMinX()) + brigScreen.getMinX(), random.nextInt(brigScreen.getMaxY() - brigScreen.getMinY()) + brigScreen.getMinY());
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
}

