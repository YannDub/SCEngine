package net.sorax.engine.gui;

import net.sorax.engine.Game;

public interface Scene {
	
	public void init();
	
	public void render();
	
	public void update();
	
	public void dispose();
}
