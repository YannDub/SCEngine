package net.sorax.engine.maths;

/**
 * 2D Vector using double number
 * @author Yann (Sorax) Dubois
 *
 */
public class Vector2d {
	
	public double x, y;
	
	/**
	 * The constructor of 2d vector
	 * @param x The x position
	 * @param y the y position
	 */
	public Vector2d(double x, double y) {
		this.set(x, y);
	}
	
	/**
	 * Set the x and y coord of this vector
	 * @param x the x coord
	 * @param y the y coord
	 */
	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Get the result of the dot product
	 * @param v The second vector to calculate the dot product
	 * @return The result of the dot product
	 */
	public double dot(Vector2d v) {
		return this.x * v.x + this.y * v.y;
	}
	
	/**
	 * Add two vectors
	 * @param vec The second vector
	 * @return The result vector
	 */
	public Vector2d add(Vector2d vec) {
		return new Vector2d(this.x + vec.x, this.y + vec.y);
	}
	
	/**
	 * Add two vectors
	 * @param vec The second vector
	 */
	public void addSelf(Vector2d vec) {
		this.x += vec.x;
		this.y += vec.y;
	}
	
	/**
	 * Multiply two vectors
	 * @param vec The second vector
	 * @return The result vector
	 */
	public Vector2d mul(double scalar) {
		return new Vector2d(this.x * scalar, this.y * scalar);
	}
	
	/**
	 * Multiply two vectors
	 * @param vec The second vector
	 */
	public void mulSelf(double scalar) {
		this.x *= scalar;
		this.y *= scalar;
	}
}
