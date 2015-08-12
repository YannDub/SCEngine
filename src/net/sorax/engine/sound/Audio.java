package net.sorax.engine.sound;

import static org.lwjgl.openal.AL10.*;

import org.lwjgl.openal.AL11;

public class Audio implements IAudio{
	
	private SoundStore store;
	private int buffer;
	private int index = -1;
	private float length;
	
	public Audio(SoundStore store, int buffer) {
		this.store = store;
		this.buffer = buffer;
		
		int bytes = alGetBufferi(buffer, AL_SIZE);
		int bits = alGetBufferi(buffer, AL_BITS);
		int channels = alGetBufferi(buffer, AL_CHANNELS);
		int freq = alGetBufferi(buffer, AL_FREQUENCY);
		
		int samples = bytes / (bits / 8);
		this.length = (samples / (float) freq) / channels;
	}
	
	@Override
	public int getBufferId() {
		return this.buffer;
	}

	@Override
	public int playAsSoundFX(float pitch, float gain, boolean loop) {
		index = store.playAsSound(buffer, pitch, gain, loop);
		return store.getSource(index);
	}

	@Override
	public int playAsSoundFX(float pitch, float gain, boolean loop, float x, float y, float z) {
		index = store.playAsSoundAt(buffer, pitch, gain, loop, x, y, z);
		return store.getSource(index);
	}

	@Override
	public int playAsMusic(float pitch, float gain, boolean loop) {
		index = store.playAsSound(buffer, pitch, gain, loop);
		return store.getSource(index);
	}
	
	public static void pauseMusic() {
		SoundStore.get().pauseLoop();
	}
	
	public static void restartMusic() {
		SoundStore.get().restartLoop();
	}
	
	@Override
	public boolean setPosition(float position) {
		position = position % length;
		
		alSourcef(store.getSource(index), AL11.AL_SEC_OFFSET, position);
		return alGetError() != 0 ? false : true;
	}

	@Override
	public float getPosition() {
		return alGetSourcef(store.getSource(index), AL11.AL_SEC_OFFSET);
	}

	@Override
	public void stop() {
		if(index != -1) {
			store.stopSource(index);
			index = -1;
		}
	}

	@Override
	public boolean isPlaying() {
		return index != -1 ? store.isPlaying(index) : false;
	}

}
