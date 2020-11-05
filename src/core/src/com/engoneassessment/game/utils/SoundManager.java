//package com.engoneassessment.game.utils;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.audio.Sound;
//import com.badlogic.gdx.utils.Array;
//import com.engoneassessment.game.models.Setting;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//
///**
// * WARNING !!!!!!!!!!!!!!!!!
// * So far this is not usable;
// */
//
///**
// * Encapsulated from Sound class.
// *
// * - Zhikang Liu
// */
//public class SoundManager {
//    private Sound sound;
//    private Array<Long> soundsId;
//
//    public SoundManager(){
//        soundmap = new HashMap();
//    }
//
//    public long addSound(String internalPath){
//        sound = Gdx.audio.newSound(Gdx.files.internal("internalPath"));
//        long soundId = sound.play(0);
//        sound.stop(soundId);
//        soundsId.add(soundId);
//        return soundId;
//    }
//
//    public void dispose(){
//        sound.dispose();
//    }
//
//    public void setSource(String internalPath){
//
//    }
//
//    public void play(long soundId){
//        sound.
//        sound.play();
//    }
//
//    public void pause(long soundId){
//        Sound sound = (Sound) soundmap.get(soundKey);
//        sound.pause();
//    }
//
//    public void stop(long soundId){
//        Sound sound = (Sound) soundmap.get(soundKey);
//        sound.stop();
//    }
//
//    public void loop(long soundId){
//        Sound sound = (Sound) soundmap.get(soundKey);
//        sound.loop();
//    }
//
//    public void resume(long soundId){
//        Sound sound = (Sound) soundmap.get(soundKey);
//        sound.resume();
//    }
//
//    public void setPitch(long soundId){
//        Sound sound = (Sound) soundmap.get(soundKey);
//        sound.setPitch();
//    }
//
//    public void setVolume(long soundId){
//        Sound sound = (Sound) soundmap.get(soundKey);
//        sound.setVolume();
//    }
//
//    public void setLooping(long soundId){
//        Sound sound = (Sound) soundmap.get(soundKey);
//        sound.setLooping();
//    }
//
//    public void setPan(long soundId, float pan){
//        Sound sound = (Sound) soundmap.get(soundKey);
//        sound.setPan(pan);
//    }
//}
