package net.sorax.engine.graphics;

public class Animation {
	
	private int length, speed, frame;
	private boolean loop;
	private boolean play;
	
	private int time = 0;
	
	public Animation(int length, int speed, boolean loop) {
		this.length = length;
		this.speed = speed;
		this.loop = loop;
		this.frame = 0;
		this.play = false;
	}
	
	public void play() {
		this.play = true;
	}
	
	public void pause() {
		this.play = false;
	}
	
	public void stop() {
		this.play = false;
		this.frame = 0;
	}
	
	public void update() {
		if(play) {
			time++;
			if(time > speed) {
				frame++;
				if(frame >= length) {
					if(loop) frame = 0;
					else frame = length - 1;
				}
				time = 0;
			}
		}
	}
	
	public int length() {
		return this.length;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public int getFrame() {
		return this.frame;
	}
	
	public boolean isPlayed() {
		return this.play;
	}
	
	public boolean isLooped() {
		return this.loop;
	}
}
