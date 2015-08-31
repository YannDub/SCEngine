package net.sorax.engine.graphics;

public class SpriteSheet {
	
	private Texture texture;
	private float textureWidth, textureHeight, spriteWidth, spriteHeight, renderWidth, renderHeight;
	private Sprite[] sprites;
	
	public SpriteSheet(Texture texture, float spriteWidth, float spriteHeight, float renderWidth, float renderHeight) {
		this.texture = texture;
		this.textureWidth = texture.getWidth();
		this.textureHeight = texture.getHeight();
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		this.renderWidth = renderWidth;
		this.renderHeight = renderHeight;
		
		int nbSpriteWidth = (int) (textureWidth / spriteWidth);
		int nbSpriteHeight = (int) (textureHeight / spriteHeight);
		
		this.sprites = new Sprite[nbSpriteWidth * nbSpriteHeight];
		
		for(int j = 0; j < nbSpriteHeight; j++) {
			for(int i = 0; i < nbSpriteWidth; i++) {
				int frame = i + j * nbSpriteWidth;
				this.sprites[frame] = new Sprite(texture, renderWidth, renderHeight, spriteWidth, spriteHeight, frame);
			}
		}
		
	}
	
	public SpriteSheet(String texture, float spriteWidth, float spriteHeight, float renderWidth, float renderHeight) {
		this(Texture.loadTexture(texture), spriteWidth, spriteHeight, renderWidth, renderHeight);
	}
	
	public Texture getTexture() {
		return this.texture;
	}
	
	public float getWidth() {
		return this.textureWidth;
	}
	
	public float getHeight() {
		return this.getHeight();
	}
	
	public float getSpriteWidth() {
		return this.spriteWidth;
	}
	
	public float getSpriteHeight() {
		return this.spriteHeight;
	}
	
	public float getRenderWidth() {
		return this.renderWidth;
	}
	
	public float getRenderHeight() {
		return this.renderHeight;
	}
	
	public Sprite getSprite(int frame) {
		return this.sprites[frame];
	}
}
