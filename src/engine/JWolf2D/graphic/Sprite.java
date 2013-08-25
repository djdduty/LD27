package engine.JWolf2D.graphic;

import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;
import engine.JWolf2D.geom.Vector2;
import engine.JWolf2D.geom.Vector3;
import engine.JWolf2D.resource.Textures;

public class Sprite {
	private Vector2 pos;
	private Texture texture;
	private int wScale, hScale;
	private float alpha=1.f;
	private float rotation=0, scale=1;
	private Vector3 color;
	private boolean rotationEnabled = false;
	
	public Sprite(Vector2 pos, String texName) {
		this.pos = pos;
		wScale = 1;
		hScale = 1;
		this.setTexture(texName);
		color = new Vector3(1,1,1);
	}
	
	public Sprite(Vector2 pos, String texName, int scaleWidthPercent, int scaleHeightPercent) {
		this.pos = pos;
		wScale = scaleWidthPercent/100;
		hScale = scaleHeightPercent/100;
		this.setTexture(texName);
	}
	
	public void render(int xoff, int yoff) {
		glEnable(GL_TEXTURE_2D);
		glColor4f(color.x, color.y, color.z, alpha);
		texture.bind();
		
		glPushMatrix();
		
		glTranslatef(pos.x+xoff, pos.y+yoff, 0);
		glRotatef(rotation,0,0,1);	
		glScalef(scale,scale,1);
		
		//this next bit is SO dirty, but I am drunkenly hacking in rotation...
		if(rotationEnabled) {
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2f(-getWidth()/2,-getHeight()/2);
			glTexCoord2f(1,0);
			glVertex2f(getWidth()/2,-getHeight()/2);
			glTexCoord2f(1,1);
			glVertex2f(getWidth()/2,0+getHeight()/2);
			glTexCoord2f(0,1);
			glVertex2f(-getWidth()/2,0+getHeight()/2);
		glEnd();
		}else {
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2f(0,0);
			glTexCoord2f(1,0);
			glVertex2f(getWidth(),0);
			glTexCoord2f(1,1);
			glVertex2f(getWidth(),getHeight());
			glTexCoord2f(0,1);
			glVertex2f(0,getHeight());
		glEnd();
		}
		
		glPopMatrix();
		
		glDisable(GL_TEXTURE_2D);
	}
	
	public Vector2 getPos() {
		return pos;
	}
	
	public void setPos(Vector2 pos) {
		this.pos = pos;
	}
	
	public void addPos(Vector2 pos) {
		this.pos.x += pos.x;
		this.pos.y += pos.y;
	}
	
	public float getWidth() {
		return texture.getTextureWidth() * wScale;
	}
	
	public float getHeight() {
		return texture.getTextureHeight() * hScale;
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public void setTexture(String name) {
		texture = Textures.get().get(name);
		
		if(texture == null)
			texture = Textures.get().get("nulltex");
		
		if(texture == null)//somehow still null
			throw new NullPointerException(name + " sprite and nulltex sprite are somehow missing");
	}
	
	public String getTextureName() {
		return Textures.get().getName(texture);
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
		
	}
	
	public void setColor(Vector3 color) {
		this.color = color;
	}
	
	public void addRotation(float rot) {
		rotationEnabled = true;
		rotation += rot;
	}
	
	public void addScale(float scale) {
		this.scale += scale;
	}
	
	public void setRotation(float rot) {
		rotationEnabled = true;
		rotation = rot;
	}
	
	public void setScale(float scale) {
		this.scale = scale;
	}
	
	public float getRotation() {
		return rotation;
	}
	
	public float getScale() {
		return scale;
	}
	
	public void enableRotation(boolean on) {
		rotationEnabled = on;
	}
}
