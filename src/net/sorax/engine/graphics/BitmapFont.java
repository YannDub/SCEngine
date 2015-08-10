package net.sorax.engine.graphics;

public class BitmapFont {
	
	private Texture texture;
	
	public BitmapFont(Texture texture) {
		this.texture = texture;
	}
	
	public void render(String s, int gridSize, int x, int y, int charWidth, int charHeight) {
		
	}
	
	public Texture getTexture() {
		return this.texture;
	}
}
