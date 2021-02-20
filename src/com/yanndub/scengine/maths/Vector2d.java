package com.yanndub.scengine.maths;

/**
 * 2D Vector using double number
 * @author Yann (Sorax) Dubois
 *
 */
public class Vector2d {
	
	public double x, y;
	
	public static final Vector2d NULL = new Vector2d(0, 0);
	
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
	
	public double length() {
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}
	
	public void normalizeSelf() {
		this.x /= length();
		this.y /= length();
	}
	
	public Vector2d normalize() {
		return new Vector2d(this.x / length(), this.y / length());
	}
	
	public double distance(Vector2d v) {
		double x_c = (v.x - this.x) * (v.x - this.x);
		double y_c = (v.y - this.y) * (v.y - this.y);
		return Math.sqrt(x_c + y_c);
	}
	
	public void lerp(Vector2d v, float time) {
		this.x += (v.x - this.x) * time;
		this.y += (v.y - this.y) * time;
	}
}
