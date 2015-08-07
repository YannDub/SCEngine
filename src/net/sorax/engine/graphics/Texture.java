package net.sorax.engine.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

public class Texture {
	
	private int width, height, id;
	private float resize_factor_w, resize_factor_h;
	
	private static final Map<String, Texture> cache = new HashMap<String, Texture>();
	
	public Texture(int width, int height, int id, float resize_factor_w, float resize_factor_h) {
		this.width = width;
		this.height = height;
		this.id = id;
		this.resize_factor_w = resize_factor_w;
		this.resize_factor_h = resize_factor_h;
	}
	
	public static Texture loadTexture(String path) {
		if(cache.containsKey(path)) {
			return cache.get(path);
		}
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(Texture.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int w = image.getWidth();
		int h = image.getHeight();
		float resize_factor_w = 1;
		float resize_factor_h = 1;
		
		int pixels[] = new int[w * h];
		image.getRGB(0, 0, w, h, pixels, 0, w);
		
		float previousW = w;
		if(!Texture.isPowerOfTwo(w)) {
			int nbBit = Integer.toBinaryString(w).length();
			w = 1 << nbBit;
			resize_factor_w = (previousW / (float) w);
		}
		
		float previousH = h;
		if(!Texture.isPowerOfTwo(h)) {
			int nbBit = Integer.toBinaryString(h).length();
			h = 1 << nbBit;
			resize_factor_h = (previousH / (float) h);
		}
		
		ByteBuffer buffer = BufferUtils.createByteBuffer(w * h * 4);
		
		for(int j = 0; j < h; j++) {
			for(int i = 0; i < w; i++) {
				int p = 0;
				if(i + j * w < pixels.length) {					
					p = pixels[i + j * w];
				}
				buffer.put((byte) ((p >> 16) & 0xFF));
				buffer.put((byte) ((p >> 8) & 0xFF));
				buffer.put((byte) ((p) & 0xFF));
				buffer.put((byte) ((p >> 24) & 0xFF));
			}
		}
		
		buffer.flip();
		
		int id = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, id);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, w, h, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
		
		Texture tex = new Texture(w, h, id, resize_factor_w, resize_factor_h);
		cache.put(path, tex);
		
		return tex;
	}
	
//	private static void fill(int[] h_array, int[] s_array) {
//		for(int i = 0; i < s_array.length; i++) {
//			h_array[i] = s_array[i];
//		}
//	}
	
	private static boolean isPowerOfTwo(int n) {
		while((n & 1) == 0) {
			n = n >> 1;
		}
		if(n != 1) return false;
		return true;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public float getResizeFactorW() {
		return this.resize_factor_w;
	}
	
	public float getResizeFactorH() {
		return this.resize_factor_h;
	}
	
	public void bind() {
		glBindTexture(GL_TEXTURE_2D, id);
	}
	
	public void unbind() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	public static void clearAllTexture() {
		for(Texture tex : cache.values()) {
			IntBuffer buffer = BufferUtils.createIntBuffer(1);
			buffer.put(tex.id);
			buffer.flip();
			
			glDeleteTextures(buffer);
		}
		
		cache.clear();
	}
}
