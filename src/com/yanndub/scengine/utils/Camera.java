package com.yanndub.scengine.utils;

import static org.lwjgl.opengl.GL11.*;
import com.yanndub.scengine.SCEGame;
import com.yanndub.scengine.graphics.Window;
import com.yanndub.scengine.maths.Vector2f;

public class Camera {
	
	private Vector2f position;
	private Vector2f size;
	private float zoom = 1, targetZoom = 1;
	private float zoomSpeed = 10;
	private float moveSpeed = 1;
	
	public Camera(final Window window) {
		this(new Vector2f(0, 0), new Vector2f(window.getWidth(), window.getHeight()));
	}
	
	public Camera(final Window window, Vector2f position) {
		this(position, new Vector2f(window.getWidth(), window.getHeight()));
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
	
	public float getX() {
		return this.position.x;
	}
	
	public float getY() {
		return this.position.y;
	}
	
	public Vector2f getSize() {
		return this.size;
	}
	
	public float getWidth() {
		return this.size.x;
	}
	
	public float getHeight() {
		return this.size.y;
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
