package com.engoneassessment.game.ui.hud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.engoneassessment.game.actors.characters.Player;
import com.engoneassessment.game.ui.UIElement;
import com.engoneassessment.game.ui.controls.NinePatchImage;
import com.engoneassessment.game.ui.controls.labels.LabelStyles;

import javax.print.DocFlavor;

public class HealthBar {
    Player auber;
    Label PlayerName;
    Label hpIndicator;
    NinePatchImage hpBar;
    TextureRegion[][] hpBar_cellRegions;
    int bar_frameRows = 10;
    int bar_frameCols = 1;

    public HealthBar(Player auber) {
        this.auber = auber;

        //Split Images into cells
        Texture hitbarTexture = new Texture(Gdx.files.internal("UI/Icons/Hitbar.png"));
        int perCellWidth = hitbarTexture.getWidth() / bar_frameCols;
        int perCellHeight = hitbarTexture.getHeight() / bar_frameRows;
        hpBar_cellRegions = TextureRegion.split(hitbarTexture, perCellWidth, perCellHeight);

        //Init HP bar
        hpBar = new NinePatchImage(new TextureRegion(hpBar_cellRegions[0][0]),
                30, 30, 0, 0);
        hpBar.setSize(400, 40);
        hpBar.setRelativePosition(20, 20, UIElement.HorizontalAlignment.leftAlignment, UIElement.VerticalAlignment.bottomAlignment);

        //Init Name of the Player
            PlayerName = new Label("Auber",
                LabelStyles.usingImpactFontStyle(
                        true, 0.5f,
                        1,1,1,1
                ));
        PlayerName.setPosition(40, 70);

        //Init HP Indicator(which is basically just a text label)
        hpIndicator = new Label("100/100",
                LabelStyles.usingImpactFontStyle(
                        true, 0.5f,
                        1,1,1,1
                ));
        hpIndicator.setPosition(300, 70);
    }

    private void updateHpBarImage(int healthLevel){
        hpBar.setImage(new TextureRegion(hpBar_cellRegions[9][0]),
                30, 30, 0, 0);
    }

    private void updateHpIndicatorText(String currentHPText){
        hpIndicator = new Label(currentHPText + "/100",
                LabelStyles.usingImpactFontStyle(
                        true, 0.5f,
                        1,1,1,1
                ));
    }

    public void updateHpBar(){
        Double currentHP = auber.getHealth();
        if(currentHP == 100){
            updateHpBarImage(0);
        }
        else if(currentHP >= 80){
            updateHpBarImage(1);
        }
        else if(currentHP >= 70){
            updateHpBarImage(2);
        }
        else if(currentHP >= 60){
            updateHpBarImage(3);
        }
        else if(currentHP >= 50){
            updateHpBarImage(4);
        }
        else if(currentHP >= 40){
            updateHpBarImage(5);
        }
        else if(currentHP >= 30){
            updateHpBarImage(6);
        }
        else if(currentHP >= 20){
            updateHpBarImage(7);
        }
        else if(currentHP >= 10){
            updateHpBarImage(8);;
        }
        else{
            updateHpBarImage(9);
        }
        updateHpIndicatorText(String.valueOf(currentHP.intValue()));
    }

//    private int getHealthLevel(){
//        Double currentHP = auber.getHealth();
//        if(currentHP == 100){
//            return 10;
//        }
//        else if(currentHP >= 80){
//            return 8;
//        }
//        else if(currentHP >= 70){
//            return 7;
//        }
//        else if(currentHP >= 60){
//            return 6;
//        }
//        else if(currentHP >= 50){
//            return 5;
//        }
//        else if(currentHP >= 40){
//            return 4;
//        }
//        else if(currentHP >= 30){
//            return 3;
//        }
//        else if(currentHP >= 20){
//            return 2;
//        }
//        else if(currentHP >= 10){
//            return 1;
//        }
//        else{
//            return 0;
//        }
//    }
}
