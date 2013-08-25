package engine.JWolf2D.gui;

import static org.lwjgl.opengl.GL11.*;

import engine.JWolf2D.geom.Vector2;
import engine.JWolf2D.geom.Vector3;

public class Label {
	private String content;
	private Vector2 pos, size;
	private float[] color;
	private Font m_Font;
	
	public Label(Font font, String content, Vector2 pos,  int width, int height, Vector3 Color) {
		m_Font = font;
		this.content = content;
		this.pos = pos;
		size = new Vector2(width,height);
		color = new float[4];
		color[0] = Color.x;
		color[1] = Color.y;
		color[2] = Color.z;
		color[3] = 1f;
	}
	
	public Label(GuiManager manager, String content, Vector2 pos,  int width, int height, Vector3 Color, float Translucency) {
		m_Font = manager.getFont();
		this.content = content;
		this.pos = pos;
		size = new Vector2(width,height);
		color = new float[4];
		color[0] = Color.x;
		color[1] = Color.y;
		color[2] = Color.z;
		color[3] = Translucency;
	}
	
	public Label(GuiManager manager, String content, Vector2 pos,  int width, int height, Vector3 Color) {
		m_Font = manager.getFont();
		this.content = content;
		this.pos = pos;
		size = new Vector2(width,height);
		color = new float[4];
		color[0] = Color.x;
		color[1] = Color.y;
		color[2] = Color.z;
		color[3] = 1;
	}
	
	public Label(GuiManager manager, String content, Vector2 pos,  int width, int height) {
		m_Font = manager.getFont();
		this.content = content;
		this.pos = pos;
		size = new Vector2(width,height);
		color = new float[4];
		color[0] = 0;
		color[1] = 0;
		color[2] = 0;
		color[3] = 1;
	}

	public void render() {
		glColor4f(color[0], color[1], color[2], color[3]);
		m_Font.drawString(content, pos.x, pos.y, size.x, size.y);
		glColor4f(1,1,1,1);
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setColor(float r, float b, float g, float a) {
		color[0] = r;
		color[1] = b;
		color[2] = g;
		color[3] = a;
	}
	
	public void setColor(Vector3 col) {
		color[0] = col.x;
		color[1] = col.y;
		color[2] = col.z;
	}
	
}
