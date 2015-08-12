package net.sorax.engine.utils;

import net.sorax.engine.maths.Vector2f;

import static org.lwjgl.opengl.GL11.*;

public class Camera {
	
	private Vector2f position;
	
	public Camera(Vector2f position) {
		this.position = position;
	}
	
	public void translate(float x, float y) {
		glTranslatef(-x, -y, 0);
		this.position.x += x;
		this.position.y += y;
	}
	
	public Vector2f getPosition() {
		return this.position;
	}
}
