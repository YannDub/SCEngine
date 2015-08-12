package net.sorax.engine.sound;

public interface IAudio {
	
	public int getBufferId();
	
	public int playAsSoundFX(float pitch, float gain, boolean loop);
	
	public int playAsSoundFX(float pitch, float gain, boolean loop, float x, float y, float z);
	
	public int playAsMusic(float pitch, float gain, boolean loop);
	
	public boolean setPosition(float position);
	
	public float getPosition();
	
	public void stop();
	
	public boolean isPlaying();
}
