package engine.JWolf2D.graphic;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.newdawn.slick.opengl.Texture;

import engine.JWolf2D.geom.Vector2;
import engine.JWolf2D.resource.Textures;

//u = x/w
//v = y/h
//glTexCoord2f(u, v)

public class AnimatedSprite {
	private Texture texture;
	private float width, height;
	private Vector2 pos;
	
	public AnimatedSprite(String texName, int width, int height, Vector2 pos) {
		this.width = width;
		this.height = height;
		this.pos = pos;
		setTexture(texName);
	}
	
	public Vector2 getPos() {
		return pos;
	}
	
	public void setPos(Vector2 pos) {
		this.pos = pos;
	}
	
	public float getWidth() {
		return texture.getTextureWidth();
	}
	
	public float getHeight() {
		return texture.getTextureHeight();
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public void setTexture(String name) {
		texture = Textures.get().get(name);
		
		if(texture == null)
			throw new NullPointerException("Texture not found.");
	}
	
	public String getTextureName() {
		return Textures.get().getName(texture);
	}
	
	public void render(int frameX, int frameY, String dir,int xoff, int yoff) {
		float tx = (frameX * width) / getWidth();
		float ty = (frameY * height) / getHeight();
		float tw = width/getWidth();
		float th = height/getHeight();
		float tx2 = tx+tw;
		float ty2 = ty+th;
		if(dir == "right") {
		glEnable(GL_TEXTURE_2D);
		texture.bind();
			glBegin(GL_QUADS);
				glTexCoord2f(tx, ty);
				glVertex2f(pos.x+xoff,pos.y+yoff);
				glTexCoord2f(tx2, ty);
				glVertex2f(pos.x+width+xoff,pos.y+yoff);
				glTexCoord2f(tx2,ty2);
				glVertex2f(pos.x+width+xoff,pos.y+height+yoff);
				glTexCoord2f(tx,ty2);
				glVertex2f(pos.x+xoff,pos.y+height+yoff);
			glEnd();
		}
		if(dir == "left") {
			glEnable(GL_TEXTURE_2D);
			texture.bind();
			glBegin(GL_QUADS);
				glTexCoord2f(tx2, ty);
				glVertex2f(pos.x,pos.y);
				glTexCoord2f(tx, ty);
				glVertex2f(pos.x+width,pos.y);
				glTexCoord2f(tx,ty2);
				glVertex2f(pos.x+width,pos.y+height);
				glTexCoord2f(tx2,ty2);
				glVertex2f(pos.x,pos.y+height);
			glEnd();
		}
		
		
		glDisable(GL_TEXTURE_2D);
	}
}
