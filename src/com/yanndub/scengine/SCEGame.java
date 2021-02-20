package com.yanndub.scengine;

import static org.lwjgl.opengl.GL11.*;

import com.yanndub.scengine.graphics.Window;
import com.yanndub.scengine.gui.Scene;
import com.yanndub.scengine.utils.LWJGLNativesLoader;
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
	protected boolean showFPS = false;
	
	protected Scene scene;
	
	/**
	 * Constructor to create a new instance of a game
	 */
	public SCEGame() {
		width = 800;
		height = 400;
		this.title = "Test";
		this.window = new Window(this.title, this.width, this.height);
//		LWJGLNativesLoader.addLWJGLNative("natives/");
	}
	
	/**
	 * Initialize the game
	 */
	protected void init() {
		if(scene != null) this.scene.init();
	}
	
	/**
	 * Set a scene to the game, for exemple change a menu scene to a game scene
	 * @param scene The new scene of the game
	 */
	public void setScene(Scene scene) {
		if(this.scene != null) scene.dispose();
		this.scene = scene;
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
		this.window.close();
//		AL.destroy();
	}
	
	/**
	 * The game loop
	 */
	private void run() {
		this.init();
		this.window.loop();

		stop();
	}
	
	/**
	 * The main rendering method
	 */
	protected void render() {
		glClear(GL_COLOR_BUFFER_BIT);
		if(scene != null) scene.render();
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
}
