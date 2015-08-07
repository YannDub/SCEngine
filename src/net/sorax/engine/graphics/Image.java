package net.sorax.engine.graphics;

public class Image {
	
	protected Texture texture;
	protected float width, height;
	protected float x, y;
	
	public Image(Texture texture, float x, float y) {
		this(texture, x, y, texture.getWidth(), texture.getHeight());
	}
	
	public Image(String texture, float x, float y, float width, float height) {
		this(Texture.loadTexture(texture), x, y, width, height);
	}

	public Image(String texture, float x, float y) {
		this.texture = Texture.loadTexture(texture);
		this.width = this.texture.getWidth();
		this.height = this.texture.getHeight();
		this.x = x;
		this.y = y;
	}
	
	public Image(Texture texture, float x, float y, float width, float height) {
		this.texture = texture;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	
	public void render() {
		Renderer.renderImage(this);
	}
	
	public Texture getTexture() {
		return this.texture;
	}
	
	public float getWidth() {
		return this.width;
	}
	
	public float getHeight() {
		return this.height;
	}
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
}
