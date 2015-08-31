package net.sorax.engine.graphics;

public class Image {
	
	protected Texture texture;
	protected float width, height;
	
	public Image(Texture texture) {
		this(texture, texture.getWidth(), texture.getHeight());
	}
	
	public Image(String texture, float width, float height) {
		this(Texture.loadTexture(texture), width, height);
	}

	public Image(String texture) {
		this.texture = Texture.loadTexture(texture);
		this.width = this.texture.getWidth();
		this.height = this.texture.getHeight();
	}
	
	public Image(Texture texture, float width, float height) {
		this.texture = texture;
		this.width = width;
		this.height = height;
	}
	
	public void render(float x, float y) {
		Renderer.renderImage(this, x, y);
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
}
