package net.sorax.engine.graphics;

public class Sprite extends Image{
	
	protected int frame;
	protected float spriteW, spriteH;
	
	protected Animation anim;
	
	public Sprite(Texture texture, float x, float y, float width, float height, float spriteW, float spriteH, int frame) {
		super(texture, x, y, width, height);
		this.frame = frame;
		this.spriteW = spriteW;
		this.spriteH = spriteH;
	}
	
	public void playAnimation(Animation anim) {
		this.anim = anim;
		this.anim.play();
	}
	
	public void stopAnimation() {
		if(this.anim != null) this.anim.stop();
	}
	
	public Sprite(String texture, float x, float y, float width, float height,  float spriteW, float spriteH, int frame) {
		this(Texture.loadTexture(texture), x, y, width, height, spriteW, spriteH, frame);
	}
	
	public void render() {
		Renderer.renderSprite(this);
	}
	
	public void setFrame(int frame) {
		this.frame = frame;
	}
	
	public int getFrame() {
		return this.frame;
	}
	
	public float getSpriteWidth() {
		return this.spriteW;
	}
	
	public float getSpriteHeight() {
		return this.spriteH;
	}
	
	public Animation getAnimation() {
		return this.anim;
	}
}
