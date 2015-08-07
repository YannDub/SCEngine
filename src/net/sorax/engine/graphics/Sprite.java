package net.sorax.engine.graphics;

public class Sprite extends Image{
	
	protected int spriteId;
	protected float spriteW, spriteH;
	
	public Sprite(Texture texture, float x, float y, float width, float height, float spriteW, float spriteH, int spriteId) {
		super(texture, x, y, width, height);
		this.spriteId = spriteId;
		this.spriteW = spriteW;
		this.spriteH = spriteH;
	}
	
	public Sprite(String texture, float x, float y, float width, float height,  float spriteW, float spriteH, int spriteId) {
		this(Texture.loadTexture(texture), x, y, width, height, spriteW, spriteH, spriteId);
	}
	
	public void render() {
		Renderer.renderSprite(this);
	}
	
	public int getSpriteId() {
		return this.spriteId;
	}
	
	public float getSpriteWidth() {
		return this.spriteW;
	}
	
	public float getSpriteHeight() {
		return this.spriteH;
	}
}
