package com.yanndub.scengine;

import static org.lwjgl.opengl.GL11.*;

import com.yanndub.scengine.graphics.Window;
import com.yanndub.scengine.gui.Scene;
import com.yanndub.scengine.graphics.Texture;

/**
 * The game instance
 * @author Yann (Sorax) Dubois
 *
 */

public class SCEGame {
	
	protected final int width, height;
	protected final String title;
	private final Window window;
	
	protected Scene scene;
	
	/**
	 * Constructor to create a new instance of a game
	 */
	public SCEGame() {
		width = 800;
		height = 400;
		this.title = "Test";
		this.window = new Window(this.title, this.width, this.height);
	}
	
	/**
	 * Set a scene to the game, for exemple change a menu scene to a game scene
	 * @param scene The new scene of the game
	 */
	public void setScene(Scene scene) {
		if(this.scene != null) scene.dispose();
		this.scene = scene;
		this.scene.init();
	}
	
	/**
	 * Start the game
	 */
	protected void start() {
		this.window.init();
		this.run();
	}
	
	/**
	 * Stop the game
	 */
	protected void stop() {
		Texture.clearAllTexture();
		if(scene != null) this.scene.dispose();
//		AL.destroy();
	}
	
	/**
	 * The game loop
	 */
	private void run() {
		while(!this.window.isCloseRequested()) {
			this.window.render(this.scene);
			this.scene.update();
		}

		stop();
	}
	
	/**
	 * The main rendering method
	 */
	protected void render() {
		if(this.scene != null) this.scene.render();
	}
	
	/**
	 * The main update method
	 */
	protected void update() {
		if(scene != null) scene.update();
	}
	
	/**
	 * Get the width of this game
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Get the height of this game
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	public Window getWindow() {
		return this.window;
	}
}
