package net.sorax.engine.sound;

import org.lwjgl.util.WaveData;

import static org.lwjgl.openal.AL10.*;

/**
 * The audio loader
 * @author Yann (Sorax) Dubois
 *
 */

public class Audio {
	
	int source;
	
	/**
	 * Constructor of an audio asset
	 * @param path the path of the asset on the computer
	 */
	public Audio(String path) {
		WaveData data = WaveData.create(Audio.class.getResourceAsStream(path));
		int buffer = alGenBuffers();
		alBufferData(buffer, data.format, data.data, data.samplerate);
		source = alGenSources();
		alSourcei(source, AL_BUFFER, buffer);
		alDeleteBuffers(buffer);
	}
	
	/**
	 * play the source audio
	 */
	public void play() {
		alSourcePlay(source);
	}
	
	/**
	 * stop the source audio
	 */
	public void stop() {
		alSourceStop(source);
	}
	
	/**
	 * Delete the source audio
	 */
	public void dispose() {
		alDeleteBuffers(source);
	}
}
