package net.sorax.engine.sound;

import static org.lwjgl.openal.AL10.*;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.util.WaveData;

/**
 * The audio loader
 * @author Yann (Sorax) Dubois
 *
 */

public class SoundStore {
	
	private static SoundStore store = new SoundStore();
	
	private IntBuffer sources;
	private FloatBuffer sourcePos = BufferUtils.createFloatBuffer(3);
	private FloatBuffer sourceVel = BufferUtils.createFloatBuffer(3).put(new float[] {0, 0, 0});
	
	private int maxSources = 64;
	private int sourceCount;
	private int currentMusic = -1;
	
	private boolean inited;
	private boolean soundWorks;
	private boolean music;
	private boolean sounds;
	
	private float musicVolume = 1.0f;
	private float soundVolume = 1.0f;
	private float lastCurrentMusicVolume = 1.0f;
	
	private boolean paused = false;
	
	public Map<String, Integer> cache = new HashMap<String, Integer>();
	
	public void init() {
		if(inited) return;
		inited = true;
		
		sourceCount = 0;
		sources = BufferUtils.createIntBuffer(maxSources);
		
		this.startAL();
		
		if(soundWorks) {			
			while(alGetError() == AL_NO_ERROR) {
				IntBuffer temp = BufferUtils.createIntBuffer(1);
				
				alGenSources(temp);
				if(alGetError() == AL_NO_ERROR) {
					sourceCount++;
					sources.put(temp.get(0));
					if(sourceCount > maxSources - 1)
						break;
				}
			}
		}
		
		if(alGetError() != AL_NO_ERROR) {
			sounds = false;
			music = false;
			soundWorks = false;
		} else {
			FloatBuffer listenerOri = BufferUtils.createFloatBuffer(6).put(new float[] {0, 0, -1, 0, 0, 1});
			FloatBuffer listenerPos = BufferUtils.createFloatBuffer(3).put(new float[] {0, 0, 0});
			FloatBuffer listenerVel = BufferUtils.createFloatBuffer(3).put(new float[] {0, 0, 0});
			listenerVel.flip();
			listenerPos.flip();
			listenerOri.flip();
			alListener(AL_POSITION, listenerPos);
			alListener(AL_VELOCITY, listenerVel);
			alListener(AL_ORIENTATION, listenerOri);
		}
		
	}
	
	public void stopSource(int index) {
		alSourceStop(sources.get(index));
	}
	
	private void startAL() {
		try {
			AL.create();
			this.sounds = true;
			this.music = true;
			this.soundWorks = true;
		} catch (LWJGLException e) {
			this.sounds = false;
			this.music = false;
			this.soundWorks = false;
		}
		
	}
	
	public void setMaxSources(int maxSources) {
		this.maxSources = maxSources;
	}
	
	public int playAsSound(int buffer, float pitch, float gain, boolean loop) {
		return playAsSoundAt(buffer, pitch, gain, loop, 0, 0, 0);
	}
	
	public int playAsSoundAt(int buffer, float pitch, float gain, boolean loop, float x, float y, float z) {
		gain *= this.soundVolume;
		if(gain == 0) gain = 0.001f;
		
		if(this.soundWorks && this.sounds) {
			int nextSource = this.findFreeSource();
			if(nextSource == -1) return -1;
			
			alSourceStop(nextSource);
			
			alSourcei(sources.get(nextSource), AL_BUFFER, buffer);
			alSourcef(sources.get(nextSource), AL_PITCH, pitch);
			alSourcef(sources.get(nextSource), AL_GAIN, gain);
			alSourcei(sources.get(nextSource), AL_LOOPING, loop ? AL_TRUE : AL_FALSE);
			
			sourcePos.clear();
			sourceVel.clear();
			sourceVel.put(new float[] {0, 0, 0});
			sourcePos.put(new float[] {x, y, z});
			sourcePos.flip();
			sourceVel.flip();
			
			alSource(sources.get(nextSource), AL_POSITION, sourcePos);
			alSource(sources.get(nextSource), AL_VELOCITY, sourceVel);
			
			alSourcePlay(sources.get(nextSource));
		}
		
		return -1;
	}
	
	public void playAsMusic(int buffer, float pitch, float gain, boolean loop) {
		setPaused(false);
		
		if(soundWorks) {
			if(currentMusic != -1) alSourceStop(0);
			alSourcei(sources.get(0), AL_BUFFER, buffer);
			alSourcef(sources.get(0), AL_PITCH, pitch);
			alSourcei(sources.get(0), AL_LOOPING, loop ? AL_TRUE : AL_FALSE);
			
			currentMusic = sources.get(0);
			
			if(!music) pauseLoop();
			else alSourcePlay(currentMusic);
		}
	}
	
	public void pauseLoop() {
		if(soundWorks && currentMusic != -1) {
			paused = true;
			alSourcePause(currentMusic);
		}
	}
	
	public void restartLoop() {
		if(soundWorks && music && currentMusic != -1) {
			paused = false;
			alSourcePlay(currentMusic);
		}
	}
	
	public void setMusicOn(boolean music) {
		if(soundWorks) {
			this.music = music;
			if(music) {
				restartLoop();
				this.setMusicVolume(musicVolume);
			} else {
				pauseLoop();
			}
		}
	}
	
	public boolean isMusicOn() {
		return this.music;
	}
	
	public void setMusicVolume(float volume) {
		if(volume < 0) volume = 0;
		if(volume > 1) volume = 1;
		if(soundWorks) {
			this.musicVolume = volume;
			alSourcef(sources.get(0), AL_GAIN, lastCurrentMusicVolume * musicVolume);
		}
	}
	
	public float getMusicVolume() {
		return this.musicVolume;
	}
	
	public void setCurrentMusicVolume(float volume) {
		if(volume < 0) volume = 0;
		if(volume > 1) volume = 1;
		if(soundWorks) {
			this.lastCurrentMusicVolume = volume;
			alSourcef(sources.get(0), AL_GAIN, lastCurrentMusicVolume * musicVolume);
		}
	}
	
	public float getLastCurrentMusicVolume() {
		return this.lastCurrentMusicVolume;
	}

	public boolean isPaused() {
		return paused;
	}
	
	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	
	public boolean soundWorks() {
		return this.soundWorks;
	}
	
	private int findFreeSource() {
		for(int i = 1; i < this.sourceCount - 1; i++) {
			int state = alGetSourcei(sources.get(i), AL_SOURCE_STATE);
			
			if(state != AL_PLAYING && state != AL_PAUSED) return i;
		}
		return -1;
	}
	
	public boolean isPlaying(int index) {
		int state = alGetSourcei(sources.get(index), AL_SOURCE_STATE);
		
		return state == AL_PLAYING;
	}
	
	public int getSource(int index) {
		if(!soundWorks) return -1;
		if(index < 0) return -1;
		return this.sources.get(index);
	}
	
	public int getMusicSource() {
		return this.sources.get(0);
	}
	
	public static SoundStore get() {
		return store;
	}
	
	public IAudio getWav(String ref) throws IOException {
		if(!this.soundWorks) return new NullAudio();
		
		int buffer = -1;
		
		if(cache.get(ref) != null) {
			buffer = ((Integer) cache.get(ref)).intValue();
		} else {
			IntBuffer buf = BufferUtils.createIntBuffer(1);
			WaveData data = WaveData.create(SoundStore.class.getResource(ref));
			alGenBuffers(buf);
			alBufferData(buf.get(0), data.format, data.data, data.samplerate);
			cache.put(ref, buf.get(0));
			buffer = buf.get(0);
		}
		
		if(buffer == -1) {
			throw new IOException("Unable to load " + ref);
		}
		
		return new Audio(this, buffer);
	}
	
	public float getSoundVolume() {
		return this.soundVolume;
	}
}
