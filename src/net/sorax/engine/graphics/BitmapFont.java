package net.sorax.engine.graphics;

public class BitmapFont {
	
	private Texture texture;
	private int gridSize;
	
	public BitmapFont(Texture texture, int gridSize) {
		this.texture = texture;
		this.gridSize = gridSize;
	}
	
	public BitmapFont(String path, int gridSize) {
		this(Texture.loadTexture(path), gridSize);
	}
	
	public void render(String s, int x, int y, int charWidth, int charHeight) {
		Renderer.renderString(this, s, gridSize, x, y, charWidth, charHeight);
	}
	
	public Texture getTexture() {
		return this.texture;
	}
}
