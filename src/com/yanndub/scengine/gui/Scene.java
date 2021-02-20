package com.yanndub.scengine.gui;

/**
 * Scene interface, it's like game state
 * @author Yann (Sorax) Dubois
 *
 */
public interface Scene {
	
	public void init();
	
	public void render();
	
	public void update();
	
	public void dispose();
}
