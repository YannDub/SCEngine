package net.sorax.engine.sound;

import org.lwjgl.util.WaveData;

import static org.lwjgl.openal.AL10.*;

public class Audio {
	
	int source;
	
	public Audio(String path) {
		WaveData data = WaveData.create(Audio.class.getResourceAsStream(path));
		int buffer = alGenBuffers();
		alBufferData(buffer, data.format, data.data, data.samplerate);
		source = alGenSources();
		alSourcei(source, AL_BUFFER, buffer);
		alDeleteBuffers(buffer);
	}
	
	public void play() {
		alSourcePlay(source);
	}
	
	public void stop() {
		alSourceStop(source);
	}
	
	public void dispose() {
		alDeleteBuffers(source);
	}
}
