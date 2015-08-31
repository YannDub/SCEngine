package net.sorax.engine.graphics;

/**
 * Sprite is a part of a Texture
 * @author Yann (Sorax) Dubois
 *
 */
public class Sprite extends Image{
	
	protected int frame;
	protected float spriteW, spriteH;
	protected float angle;
	
	protected Animation anim;
	
	/**
	 * The constructor of a new sprite
	 * @param texture the texture of this sprite
	 * @param x the position x of this sprite
	 * @param y the position y of this sprite
	 * @param width the horizontal size of this sprite
	 * @param height the vertical size of this sprite
	 * @param spriteW the width on the texture
	 * @param spriteH the height on the texture
	 * @param frame The position on the texture
	 */
	public Sprite(Texture texture, float width, float height, float spriteW, float spriteH, int frame) {
		super(texture, width, height);
		this.frame = frame;
		this.spriteW = spriteW;
		this.spriteH = spriteH;
		this.angle = 0;
	}
	
	/**
	 * The constructor of a new sprite
	 * @param texture the texture path of this sprite
	 * @param x the position x of this sprite
	 * @param y the position y of this sprite
	 * @param width the horizontal size of this sprite
	 * @param height the vertical size of this sprite
	 * @param spriteW the width on the texture
	 * @param spriteH the height on the texture
	 * @param frame The position on the texture
	 */
	public Sprite(String texture, float width, float height,  float spriteW, float spriteH, int frame) {
		this(Texture.loadTexture(texture), width, height, spriteW, spriteH, frame);
	}
	
	/**
	 * play an animation
	 * @param anim the animation to played
	 */
	public void playAnimation(Animation anim) {
		this.anim = anim;
		this.anim.play();
	}
	
	/**
	 * Stop the current animation
	 */
	public void stopAnimation() {
		if(this.anim != null) this.anim.stop();
	}
	
	/**
	 * Rotate this sprite
	 * @param angle the angle of rotation
	 */
	public void rotate(float angle) {
		this.angle = angle;
	}
	
	/**
	 * Draw this sprite
	 */
	public void render(float x, float y) {
		Renderer.renderSprite(this, x, y);
	}
	
	/**
	 * Change the current frame
	 * @param frame the new frame
	 */
	public void setFrame(int frame) {
		this.frame = frame;
	}
	
	/**
	 * Get the current frame
	 * @return the current frame
	 */
	public int getFrame() {
		return this.frame;
	}
	
	/**
	 * Get the width on the texture
	 * @return the width on the texture
	 */
	public float getSpriteWidth() {
		return this.spriteW;
	}
	
	/**
	 * Get the height on the texture
	 * @return the height on the texture
	 */
	public float getSpriteHeight() {
		return this.spriteH;
	}
	
	/**
	 * Get the current animation
	 * @return the current animation
	 */
	public Animation getAnimation() {
		return this.anim;
	}
	
	/**
	 * Get the current angle
	 * @return the current angle
	 */
	public float getAngle() {
		return this.angle;
	}
}
