package com.engoneassessment.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.engoneassessment.game.models.Setting;

/**
 * Encapsulated from Music class.
 *
 * - Zhikang Liu
 */
public class MusicManager {
    //Background Music
    private Music music;

    public MusicManager(String internalPath){
        setVolume(Setting.getMusicVolume());
        setSource(internalPath);
    }

    public void setSource(String internalPath){
        music = Gdx.audio.newMusic(Gdx.files.internal("internalPath"));
        isLoop(true);
    }

    public void isLoop(boolean isloop){
        music.setLooping(isloop);
    }

    public void setVolume(float volume){
        music.setVolume(volume);
        Setting.setMusicVolume(volume);
    }

    public void play(){
        music.play();
    }

    public void pause(){
        music.pause();
    }

    public void stop(){
        music.stop();
    }
}
