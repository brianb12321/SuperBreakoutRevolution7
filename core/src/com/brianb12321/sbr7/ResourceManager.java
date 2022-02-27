
package com.brianb12321.sbr7;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import java.util.HashMap;

/**
 *
 * @author gamec
 */
public class ResourceManager implements Disposable {
    private HashMap<String, Texture> _textures;
    private Array<String> levels;
    private HashMap<String, Sound> _sfx;
    private HashMap<String, Music> _music;
    private Music _playingMusic;
    public ResourceManager() {
        _textures = new HashMap<String, Texture>();
        _sfx = new HashMap<String, Sound>();
        levels = new Array<String>();
        _music = new HashMap<String, Music>();
    }
    public void addLevelFile(String file) {
        levels.add(file);
    }
    public void addSfx(String name, String file) {
        _sfx.put(name, Gdx.audio.newSound(Gdx.files.internal(file)));
    }
    public void addMusic(String name, String file) {
        _music.put(name, Gdx.audio.newMusic(Gdx.files.internal(file)));
    }
    public Music getMusic(String name) {
        _playingMusic = _music.get(name);
        return _playingMusic;
    }
    public Music getPlayingMusic() {
        return _playingMusic;
    }
    public void switchMusic(String name) {
        getPlayingMusic().stop();
        Music m = getMusic(name);
        m.setVolume(GameDetails.VOLUME);
        m.setLooping(true);
        m.play();
    }
    public void switchMusicMoLoop(String name) {
        getPlayingMusic().stop();
        Music m = getMusic(name);
        m.setVolume(GameDetails.VOLUME);
        m.setLooping(false);
        m.play();
    }
    public Sound getSfx(String name) {
        return _sfx.get(name);
    }
    public void playSfx(String name) {
        getSfx(name).play(GameDetails.VOLUME);
    }
    public String getLevelFile(int number) {
        return levels.get(number - 1);
    }
    public boolean hasLevelNumber(int number) {
        return levels.size >= number;
    }
    public void addTexture(String name, String internalName) {
        _textures.put(name, new Texture(Gdx.files.internal(internalName)));
    }
    public Texture getTexture(String name) {
        return _textures.get(name);
    }
    public void dispose(String name) {
        _textures.get(name).dispose();
    }

    @Override
    public void dispose() {
        for(Texture t : _textures.values()) {
            t.dispose();
        }
        for(Sound sfx : _sfx.values()) {
            sfx.dispose();
        }
        for(Music music : _music.values()) {
            music.dispose();
        }
    }
}