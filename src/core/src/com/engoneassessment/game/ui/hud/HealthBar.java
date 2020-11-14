package com.engoneassessment.game.ui.hud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.engoneassessment.game.actors.characters.Player;
import com.engoneassessment.game.ui.controls.NinePatchImage;

public class HealthBar extends VerticalGroup {
    Player auber;
    Label text;
    NinePatchImage bar;
    int bar_frameRows = 10;
    int bar_frameCols = 1;

    public HealthBar(Player auber){
        this.auber = auber;

        Texture hitbarTexture = new Texture(Gdx.files.internal("UI/Icons/Hitbar.png"));
        int perCellWidth = hitbarTexture.getWidth() / bar_frameCols;
        int perCellHeight = hitbarTexture.getHeight() / bar_frameRows;
        TextureRegion[][] cellRegions = TextureRegion.split(hitbarTexture, perCellWidth, perCellHeight);

        bar = new NinePatchImage(new TextureRegion(cellRegions[6][0]));
        bar.setNinePatch(0,0,0,0,new TextureRegion(cellRegions[6][0]));
        bar.setMiddleWidth(300);
        bar.setX(30);
        bar.setY(30);
        this.addActor(bar);
    }
}
