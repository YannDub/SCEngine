package net.sorax.engine.sound;

import java.io.IOException;

public class Sound {
	
	private IAudio sound;
	
	public Sound(String ref) {
		SoundStore.get().init();
		try {
			sound = SoundStore.get().getWav(ref);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		play(1.0f, 1.0f);
	}
	
	public void play(float pitch, float volume) {
		sound.playAsSoundFX(pitch, volume * SoundStore.get().getSoundVolume(), false);
	}
	
	public void playAt(float x, float y, float z) {
		playAt(1.0f, 1.0f, x, y, z);
	}
	
	public void playAt(float pitch, float volume, float x, float y, float z) {
		sound.playAsSoundFX(pitch, volume * SoundStore.get().getSoundVolume(), false, x, y, z);
	}
	
	public void loop() {
		loop(1.0f, 1.0f);
	}
	
	public void loop(float pitch, float volume) {
		sound.playAsSoundFX(pitch, volume * SoundStore.get().getSoundVolume(), true);
	}
	
	public boolean isPlaying() {
		return sound.isPlaying();
	}
	
	public void stop() {
		sound.stop();
	}
}
