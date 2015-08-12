package net.sorax.engine.sound;

import java.io.IOException;

public class Music {
	
	private IAudio music;
	private boolean playing;
	private boolean positioning;
	private float requiredPosition = -1;
	private float volume;
	public static Music currentMusic;
	
	public Music(String ref) {
		SoundStore.get().init();
		
		try {
			music = SoundStore.get().getWav(ref);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loop() {
		this.loop(1.0f, 1.0f);
	}
	
	public void loop(float pitch, float volume) {
		this.startMusic(pitch, volume, true);
	}
	
	public void play() {
		play(1.0f, 1.0f);
	}
	
	public void play(float pitch, float volume) {
		this.startMusic(pitch, volume, false);
	}
	
	private void startMusic(float pitch, float volume, boolean loop) {
		if(currentMusic != null) {
			currentMusic.stop();
			currentMusic.playing = false;
		}
		
		currentMusic = this;
		if(volume < 0) volume = 0;
		if(volume > 1) volume = 1;
		
		music.playAsMusic(pitch, volume, loop);
		playing = true;
		this.setVolume(volume);
		if(requiredPosition != -1) setPosition(requiredPosition);
	}
	
	public void setVolume(float volume) {
		if(volume > 1) volume = 1;
		else if(volume < 0) volume = 0;
		
		this.volume = volume;
		if(currentMusic == this) SoundStore.get().setCurrentMusicVolume(volume);
	}
	
	public boolean setPosition(float position) {
		if(playing) {
			requiredPosition = -1;
			positioning = true;
			playing = false;
			boolean result = music.setPosition(position);
			playing = true;
			positioning= false;
			
			return result;
		} else {
			this.requiredPosition = position;
			return false;
		}
	}
	
	public void pause() {
		playing = false;
		Audio.pauseMusic();
	}
	
	public void stop() {
		music.stop();
	}
	
	public void resume() {
		playing = true;
		Audio.restartMusic();
	}
	
	public boolean playing() {
		return this.playing;
	}
	
	public boolean positioning() {
		return this.positioning;
	}
	
	public float getVolume() {
		return this.volume;
	}
	
	public float getPosition() {
		return music.getPosition();
	}
}
