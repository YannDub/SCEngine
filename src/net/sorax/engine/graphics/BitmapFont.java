package net.sorax.engine.graphics;

public class BitmapFont {
	
	private Texture texture;
	
	public BitmapFont(Texture texture) {
		this.texture = texture;
	}
	
	public BitmapFont(String path) {
		this(Texture.loadTexture(path));
	}
	
	public void render(String s, int gridSize, int x, int y, int charWidth, int charHeight) {
		Renderer.renderString(this, s, gridSize, x, y, charWidth, charHeight);
	}
	
	public Texture getTexture() {
		return this.texture;
	}
}
