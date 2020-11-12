package com.engoneassessment.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.engoneassessment.game.actors.characters.Player;

public class HUD extends Stage {
    private Player player;

    private Button buttonMap;
    private Button.ButtonStyle buttonMapStyle;
    private Texture buttonMap_upTexture;
    private Texture buttonMap_downTexture;

    private Image hitbar;
    private Texture hitbarTexture;
    private int hitbar_frameRows = 10;
    private int hitbar_frameCols = 1;

    public HUD(Viewport viewport, Player auber){
        super(viewport);

        initButtonMap();
        initHitBar();

        this.addActor(hitbar);
        this.addActor(buttonMap);
    }

    public void initButtonMap(){
        buttonMapStyle = new Button.ButtonStyle();
        buttonMap_upTexture = new Texture("UI/Icons/Map.png");
        buttonMap_downTexture = new Texture("UI/Icons/Map.png");
        buttonMapStyle.up = new TextureRegionDrawable(buttonMap_upTexture);
        buttonMapStyle.down = new TextureRegionDrawable(buttonMap_downTexture);
        buttonMap = new Button(buttonMapStyle);
        buttonMap.setX(this.getWidth() - buttonMap.getWidth() - 20);
        buttonMap.setY(20);
    }


    public void initHitBar(){
        hitbarTexture = new Texture(Gdx.files.internal("UI/Icons/Hitbar.png"));
        int perCellWidth = hitbarTexture.getWidth() / hitbar_frameCols;
        int perCellHeight = hitbarTexture.getHeight() / hitbar_frameRows;
        TextureRegion[][] cellRegions = TextureRegion.split(hitbarTexture, perCellWidth, perCellHeight);

        hitbar = new Image(new TextureRegion(cellRegions[6][0]));
        hitbar.setX(20);
        hitbar.setY(20);
    }
}
