package net.sorax.engine.graphics;

public class NineSprite extends Image {

	public static final int TOP_LEFT = 0;
	public static final int TOP_CENTER = 1;
	public static final int TOP_RIGHT = 2;
	public static final int CENTER_LEFT = 3;
	public static final int CENTER = 4;
	public static final int CENTER_RIGHT = 5;
	public static final int BOTTOM_LEFT = 6;
	public final static int BOTTOM_CENTER = 7;
	public final static int BOTTOM_RIGHT = 8;
	
	private Sprite[] sprites;
	
	public NineSprite(Texture texture, float x, float y, float nineWidth, float nineHeight) {
		super(texture, x, y);
		this.sprites = new Sprite[9];
//		int spriteWidth = texture.getWidth() / 3;
//		int spriteHeight = texture.getHeight() / 3;
		for(int i = 0; i < 9; i++) {
			this.sprites[i] = new Sprite(texture, x, y, nineWidth, nineHeight, 3, 3, i);
		}
	}
	
	public NineSprite(Sprite sprite, float nineWidth, float nineHeight) {
		super(sprite.getTexture(), sprite.getSpriteWidth(), sprite.getSpriteHeight());
		this.sprites = new Sprite[9];
		int spriteWidth = (int) (sprite.getSpriteWidth());
		int spriteHeight = (int) (sprite.getSpriteHeight());
		for(int i = 0; i < 9; i++) {
			this.sprites[i] = new Sprite(texture, x, y, nineWidth, nineHeight, spriteWidth, spriteHeight, i);
		}
	}
	
	public Sprite getSprite(int i) {
		return this.sprites[i];
	}
}
