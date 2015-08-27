package net.sorax.engine;

import static org.lwjgl.opengl.GL11.*;
import net.sorax.engine.graphics.Texture;
import net.sorax.engine.gui.Scene;
import net.sorax.engine.utils.LWJGLNativesLoader;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * The game instance
 * @author Yann (Sorax) Dubois
 *
 */

public class SCEGame {
	
	protected int width, height;
	protected int scale = 1;
	protected String title;
	protected boolean showFPS = false;
	
	protected Scene scene;
	
	/**
	 * Constructor to create a new instance of a game
	 */
	public SCEGame() {
		this.width = 800;
		this.height = 400;
		this.title = "Test";
		LWJGLNativesLoader.addLWJGLNative("natives/");
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
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(title);
			Display.sync(60);
			Display.setResizable(true);
			Display.create();
			this.run();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		return;
	}
	
	/**
	 * Stop the game
	 */
	protected void stop() {
		Texture.clearAllTexture();
		if(scene != null) this.scene.dispose();
		Display.destroy();
		AL.destroy();
	}
	
	/**
	 * Initialize opengl for a 2d game
	 */
	private void initGL() {
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, width / scale, height / scale, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		glEnable(GL_TEXTURE_2D);
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	/**
	 * The game loop
	 */
	private void run() {
		long before = System.nanoTime();
		double ns = 1000000000.0 / 60.0;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int tick = 0;
		int fps = 0;

		this.init();
		this.initGL();
		
		while(!Display.isCloseRequested()) {
			long now = System.nanoTime();
			delta += (now - before) / ns;
			before = now;
			if(delta >= 1) {
				delta--;
				update();
				tick++;
			}
			
			render();
			fps++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				if(this.showFPS) System.out.println("tick : " + tick + " fps : " + fps);
				fps = 0;
				tick = 0;
			}
			
			Display.update();
		}
		stop();
	}
	
	/**
	 * The main rendering method
	 */
	protected void render() {
		this.initGL();
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
	 * Get the scale
	 * @return the scale
	 */
	public int getScale() {
		return this.scale;
	}
	
	/**
	 * Get the width of this game
	 * @return the width
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * Get the height of this game
	 * @return the height
	 */
	public int getHeight() {
		return this.height;
	}
}
