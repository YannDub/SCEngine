package net.sorax.engine.maths;

/**
 * 2D Vector using float number
 * @author Yann (Sorax) Dubois
 *
 */
public class Vector2f {
	
	public float x, y;
	
	/**
	 * The constructor of 2d vector
	 * @param x The x position
	 * @param y the y position
	 */
	public Vector2f(float x, float y) {
		this.set(x, y);
	}
	
	/**
	 * Set the x and y coord of this vector
	 * @param x the x coord
	 * @param y the y coord
	 */
	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Get the result of the dot product
	 * @param v The second vector to calculate the dot product
	 * @return The result of the dot product
	 */
	public float dot(Vector2f v) {
		return this.x * v.x + this.y * v.y;
	}
	
	/**
	 * Add two vectors
	 * @param vec The second vector
	 * @return The result vector
	 */
	public Vector2f add(Vector2f vec) {
		return new Vector2f(this.x + vec.x, this.y + vec.y);
	}
	
	/**
	 * Add two vectors
	 * @param vec The second vector
	 */
	public void addSelf(Vector2f vec) {
		this.x += vec.x;
		this.y += vec.y;
	}
	
	/**
	 * Multiply two vectors
	 * @param vec The second vector
	 * @return The result vector
	 */
	public Vector2f mul(float scalar) {
		return new Vector2f(this.x * scalar, this.y * scalar);
	}
	
	/**
	 * Multiply two vectors
	 * @param vec The second vector
	 */
	public void mulSelf(float scalar) {
		this.x *= scalar;
		this.y *= scalar;
	}
	
	public float length() {
		return (float) Math.sqrt(this.x * this.x + this.y * this.y);
	}
	
	public void normalizeSelf() {
		this.x /= length();
		this.y /= length();
	}
	
	public Vector2f normalize() {
		return new Vector2f(this.x / length(), this.y / length());
	}
	
	public float distance(Vector2f v) {
		float x_c = (v.x - this.x) * (v.x - this.x);
		float y_c = (v.y - this.y) * (v.y - this.y);
		return (float)Math.sqrt(x_c + y_c);
	}
}
