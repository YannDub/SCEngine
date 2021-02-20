package com.yanndub.scengine.graphics;

public class Animation {
	
	private int length, speed, frame, beginFrame;
	private boolean loop;
	private boolean play;
	
	private int time = 0;
	
	public Animation(int beginFrame, int length, int speed, boolean loop) {
		this.length = length + beginFrame;
		this.speed = speed;
		this.loop = loop;
		this.beginFrame = beginFrame;
		this.frame = beginFrame;
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
		this.frame = this.beginFrame;
	}
	
	public void update() {
		if(play) {
			time++;
			if(time > speed) {
				frame++;
				if(frame >= length) {
					if(loop) frame = this.beginFrame;
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
