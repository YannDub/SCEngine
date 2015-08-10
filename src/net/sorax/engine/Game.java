package net.sorax.engine;

import static org.lwjgl.opengl.GL11.*;
import net.sorax.engine.graphics.Texture;
import net.sorax.engine.gui.Scene;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Game {
	
	protected int width, height;
	protected int scale;
	protected String title;
	protected boolean showFPS = false;
	
	protected Scene scene;
	
	public Game() {
		this.width = 800;
		this.height = 400;
		this.title = "Test";
	}
	
	protected void init() {
		if(scene != null) this.scene.init();
	}
	
	public void setScene(Scene scene) {
		if(this.scene != null) scene.dispose();
		this.scene = scene;
	}
	
	protected void start() {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(title);
			Display.sync(60);
			Display.setResizable(true);
			Display.create();
			AL.create();
			this.run();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		return;
	}
	
	protected void stop() {
		Texture.clearAllTexture();
		if(scene != null) this.scene.dispose();
		Display.destroy();
		AL.destroy();
	}
	
	private void initGL() {
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, width, height, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		glEnable(GL_TEXTURE_2D);
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
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
	
	protected void render() {
		this.initGL();
		glClear(GL_COLOR_BUFFER_BIT);
		
		if(scene != null) scene.render();
	}
	
	protected void update() {
		if(scene != null) scene.update();
	}
}
