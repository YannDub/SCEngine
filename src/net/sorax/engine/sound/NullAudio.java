package net.sorax.engine.sound;

public class NullAudio implements IAudio {

	@Override
	public int getBufferId() {
		return 0;
	}

	@Override
	public int playAsSoundFX(float pitch, float gain, boolean loop) {
		return 0;
	}

	@Override
	public int playAsSoundFX(float pitch, float gain, boolean loop, float x,
			float y, float z) {
		return 0;
	}

	@Override
	public int playAsMusic(float pitch, float gain, boolean loop) {
		return 0;
	}

	@Override
	public boolean setPosition(float position) {
		return false;
	}

	@Override
	public float getPosition() {
		return 0;
	}

	@Override
	public void stop() {
		
	}

	@Override
	public boolean isPlaying() {
		return false;
	}

}
