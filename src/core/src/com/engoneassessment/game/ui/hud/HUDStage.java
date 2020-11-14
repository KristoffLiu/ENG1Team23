package com.engoneassessment.game.ui.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.engoneassessment.game.actors.characters.Player;
import com.engoneassessment.game.ui.UIStage;

public class HUDStage extends UIStage {
    private Player player;

    private Button mapButton;
    private Button.ButtonStyle mapButtonStyle;
    private TextureRegion mapButton_upTextureRegion = new TextureRegion(new Texture("UI/Icons/Map.png"));
    private TextureRegion mapButton_downTextureRegion = new TextureRegion(new Texture("UI/Icons/Map.png"));

    private Button miniMapButton;
    private Button.ButtonStyle miniMapButtonStyle;
    private TextureRegion miniMapButton_upTextureRegion = new TextureRegion(new Texture("UI/Icons/Map.png"));
    private TextureRegion miniMapButton_downTextureRegion = new TextureRegion(new Texture("UI/Icons/Map.png"));

    private Button beamButton;
    private Button.ButtonStyle beamButtonButtonStyle;
    private TextureRegion beamButtonButton_upTextureRegion = new TextureRegion(new Texture("UI/Icons/Map.png"));
    private TextureRegion beamButtonButton_downTextureRegion = new TextureRegion(new Texture("UI/Icons/Map.png"));


    private Image hitbar;
    private Texture hitbarTexture;

    private int hitbar_frameRows = 10;
    private int hitbar_frameCols = 1;

    public HUDStage(Viewport viewport, Player auber){
        super(viewport);

        initMapButton();
        initHitBar();
        initButtonBeam();

        this.addActor(hitbar);
        this.addActor(mapButton);
    }

    public void initMapButton(){
        mapButton = new Button(HUDButton.generateButtonStyle(mapButton_upTextureRegion,mapButton_downTextureRegion));
        mapButton.setWidth(mapButton.getWidth() * 1.5f);
        mapButton.setHeight(mapButton.getHeight() * 1.5f);
        mapButton.setX(this.getWidth() - mapButton.getWidth() - 20);
        mapButton.setY(20);
    }

    public void initBeamButton(){
        mapButtonStyle = new Button.ButtonStyle();
        mapButtonStyle.up = new TextureRegionDrawable(mapButton_upTextureRegion);
        mapButtonStyle.down = new TextureRegionDrawable(mapButton_downTextureRegion);
        mapButton = new Button(mapButtonStyle);
        mapButton.setWidth(mapButton.getWidth() * 1.5f);
        mapButton.setHeight(mapButton.getHeight() * 1.5f);
        mapButton.setX(this.getWidth() - mapButton.getWidth() - 20);
        mapButton.setY(20);
    }


    public void initBeamButton(){
        mapButtonStyle = new Button.ButtonStyle();
        mapButtonStyle.up = new TextureRegionDrawable(mapButton_upTextureRegion);
        mapButtonStyle.down = new TextureRegionDrawable(mapButton_downTextureRegion);
        mapButton = new Button(mapButtonStyle);
        mapButton.setWidth(mapButton.getWidth() * 1.5f);
        mapButton.setHeight(mapButton.getHeight() * 1.5f);
        mapButton.setX(this.getWidth() - mapButton.getWidth() - 20);
        mapButton.setY(20);
    }




    public void initHitBar(){
        hitbarTexture = new Texture(Gdx.files.internal("UI/Icons/Hitbar.png"));
        int perCellWidth = hitbarTexture.getWidth() / hitbar_frameCols;
        int perCellHeight = hitbarTexture.getHeight() / hitbar_frameRows;
        TextureRegion[][] cellRegions = TextureRegion.split(hitbarTexture, perCellWidth, perCellHeight);

        hitbar = new Image(new TextureRegion(cellRegions[6][0]));
        hitbar.setWidth(hitbar.getWidth()*1.5f);
        hitbar.setX(20);
        hitbar.setY(20);
    }



    public void initButtonBeam(){


    }

    public void fadeIn(){

    }

    public void fadeOut(){

    }
}
