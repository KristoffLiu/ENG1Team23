package com.engoneassessment.game.ui;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Button {

    private Stage stage;
    private double x;
    private double y;
    private Texture upTexture;
    private Texture downTexture;
    private Button button;
    //private Skin skin;

    public void create() {
        upTexture = new Texture(Gdx.files.internal("button_1.png"));
        downTexture = new Texture(Gdx.files.internal("button_2.png"));

        Button.ButtonStyle style = new Button.ButtonStyle();

        style.up = new TextureRegionDrawable(new TextureRegion(upTexture));
        style.down = new TextureRegionDrawable(new TextureRegion(downTexture));

        button = new Button(style);

        //button.setPosition();

        public void clicked() {

        }
    }

}
