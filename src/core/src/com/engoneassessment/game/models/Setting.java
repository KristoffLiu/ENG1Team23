package com.engoneassessment.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * this class encapsulates the global setting,
 * such as the resolution, volume of music and so on.
 * These setting should be stored in the database or file-based,
 * then we will be able to load these setting for the next time.
 *
 * - Zhikang Liu 2020/11/03
 */
public class Setting {
    private float resolutionWidth;
    private float resolutionHeight;
    private float musicVolume;
    private float soundVolume;

    Preferences prefs;

    public static Setting current;

    public Setting(){
        current = this;
        prefs = Gdx.app.getPreferences("game.preferences");
        resolutionWidth = prefs.getFloat("resolutionWidth");
        resolutionHeight = prefs.getFloat("resolutionHeight");
        musicVolume = prefs.getFloat("musicVolume");
        soundVolume = prefs.getFloat("soundVolume");
    }

    public static float getMusicVolume(){
        return current.musicVolume;
    }

    public static float getSoundVolume(){
        return current.soundVolume;
    }

    public static void setMusicVolume(float value){
        current.musicVolume = value;
        current.prefs.putFloat("musicVolume",value);
    }

    public static void setSoundVolume(float value){
        current.soundVolume = value;
        current.prefs.putFloat("soundVolume",value);
    }


}
