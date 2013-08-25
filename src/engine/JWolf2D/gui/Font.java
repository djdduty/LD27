package engine.JWolf2D.gui;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.opengl.Texture;

import engine.JWolf2D.geom.Vector3;

public class Font {
	private Texture image;
	private float width, height;
	private String[] mappings;
	private Vector3 Color;
	
	public Font(Texture image, int width, int height, String[] mappings) {
		this.image = image;
		this.width = width;
		this.height = height;
		this.mappings = mappings;
		this.Color = new Vector3(1,1,1);
		
		glEnable(GL_TEXTURE_2D);
	}
	
	public Font(Texture image, int width, int height, String[] mappings, Vector3 Color) {
		this.image = image;
		this.width = width;
		this.height = height;
		this.mappings = mappings;
		this.Color = Color;
		
		glEnable(GL_TEXTURE_2D);
	}
	
	public void drawString(String s, float x, float y, float w, float h) {
		s = s.toUpperCase();
		
		image.bind();
		
		for(int a = 0; a < s.length(); a++, x += w) {
			if(s.charAt(a) == ' ')
				continue;
			
			float ix = -1, iy = -1;
			for(int i = 0; i < mappings.length; i++) {
				ix = mappings[i].indexOf(s.charAt(a));
				if(ix >= 0) {
					iy = i;
					break;
				}
			}
			
			if(ix == -1)
				throw new IllegalArgumentException("Character " + s.charAt(a) + " not in mappings.");
			
			float tx = (ix * width) / image.getTextureWidth();
			float ty = (iy * height) / image.getTextureHeight();
			float tw = width/image.getTextureWidth();
			float th = height/image.getTextureHeight();
			
			
			glBegin(GL_QUADS);
				glTexCoord2f(tx,ty);
				//glColor3f(Color.x, Color.y, Color.z);
				glVertex2f(x,y);
				glTexCoord2f(tx+tw,ty);
				//glColor3f(Color.x, Color.y, Color.z);
				glVertex2f(x+w,y);
				glTexCoord2f(tx+tw,ty+th);
				//glColor3f(Color.x, Color.y, Color.z);
				glVertex2f(x+w,y+h);
				glTexCoord2f(tx,ty+th);
				//glColor3f(Color.x, Color.y, Color.z);
				glVertex2f(x,y+h);
			glEnd();
			
		}
	}
}