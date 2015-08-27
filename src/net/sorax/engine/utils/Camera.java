package net.sorax.engine.utils;

import static org.lwjgl.opengl.GL11.*;
import net.sorax.engine.SCEGame;
import net.sorax.engine.maths.Vector2f;

public class Camera {
	
	private Vector2f position;
	private Vector2f size;
	private float zoom = 1, targetZoom = 1;
	private float zoomSpeed = 10;
	private float moveSpeed = 1;
	
	public Camera() {
		this(new Vector2f(0, 0), new Vector2f(SCEGame.getWidth(), SCEGame.getHeight()));
	}
	
	public Camera(Vector2f position) {
		this(position, new Vector2f(SCEGame.getWidth(), SCEGame.getHeight()));
	}
	
	public Camera(Vector2f position, Vector2f size) {
		this.position = position;
		this.size = size;
	}
	
	public void translate(float x, float y) {
		glTranslatef(-x, -y, 0);
		this.position.x += x * moveSpeed;
		this.position.y += y * moveSpeed;
	}
	
	public void render() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(-this.size.x / 2 * zoom, this.size.x / 2 * zoom, this.size.y / 2 * zoom, -this.size.y / 2 * zoom, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		glEnable(GL_TEXTURE_2D);
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public void update() {
		this.zoom += (targetZoom - zoom) / this.zoomSpeed;
	}
	
	public void zoom(float zoom) {
		this.targetZoom = this.zoom + zoom;
	}
	
	public void setPosition(float x, float y) {
		this.setPosition(new Vector2f(x, y));
	}
	
	public void setPosition(Vector2f pos) {
		this.position = pos;
	}
	
	public Vector2f getPosition() {
		return this.position;
	}
	
	public Vector2f getSize() {
		return this.size;
	}
	
	public void setZoomSpeed(float zoomSpeed) {
		this.zoomSpeed = zoomSpeed;
	}
	
	public void setMoveSpeed(float moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
	
	public float getZoom() {
		return this.zoom;
	}
}
