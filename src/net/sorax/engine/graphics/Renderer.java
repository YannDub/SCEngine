package net.sorax.engine.graphics;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {
	
	public static void renderBox(float x, float y, float width, float height, float r, float g, float b, float a) {
		glBegin(GL_QUADS);
			glColor4f(r, g, b, a); glVertex2f(x, y);
			glColor4f(r, g, b, a); glVertex2f(x + width, y);
			glColor4f(r, g, b, a); glVertex2f(x + width, y + height);
			glColor4f(r, g, b, a); glVertex2f(x, y + height);
		glEnd();
	}
	
	public static void renderImage(Image image) {
		float x = image.getX();
		float y = image.getY();
		float w = image.getWidth();
		float h = image.getHeight();
		Texture texture = image.getTexture();
		
		texture.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0); glVertex2f(x, y);
			glTexCoord2f(1, 0); glVertex2f(x + w, y);
			glTexCoord2f(1, 1); glVertex2f(x + w, y + h);
			glTexCoord2f(0, 1); glVertex2f(x, y + h);
		glEnd();
		texture.unbind();
	}
	
	public static void renderSprite(Sprite sprite, float angle) {
		float x = sprite.getX();
		float y = sprite.getY();
		float w = sprite.getWidth();
		float h = sprite.getHeight();
		int spriteId = sprite.getFrame();
		Texture texture = sprite.getTexture();
		
		float nbSpriteW = texture.getWidth() / sprite.getSpriteWidth();
		float nbSpriteH = texture.getHeight() / sprite.getSpriteHeight();
		
		if(sprite.getAnimation() != null) {
			spriteId = sprite.getAnimation().getFrame();
		}
		
		int posX = (int) (spriteId % nbSpriteW);
		int posY = (int) (spriteId / nbSpriteH);
		
		texture.bind();
		glPushMatrix();
		
		glTranslatef(x + w / 2, y + h / 2, 0);
		glRotatef(angle, 0, 0, 1);
		glTranslatef(-x - w / 2, -y - h / 2, 0);
		
		glBegin(GL_QUADS);
			glTexCoord2f(posX / sprite.getSpriteWidth(), posY / sprite.getSpriteHeight()); glVertex2f(x, y);
			glTexCoord2f((1 + posX) / sprite.getSpriteWidth(), posY / sprite.getSpriteHeight()); glVertex2f(x + w, y);
			glTexCoord2f((1 + posX) / sprite.getSpriteWidth(), (1 + posY) / sprite.getSpriteHeight()); glVertex2f(x + w, y + h);
			glTexCoord2f(posX / sprite.getSpriteWidth(), (1 + posY) / sprite.getSpriteHeight()); glVertex2f(x, y + h);
		glEnd();
		glPopMatrix();
		texture.unbind();
	}
}
