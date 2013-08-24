package engine.JWolf2D.gui;

import static org.lwjgl.opengl.GL11.*;

import engine.JWolf2D.geom.Vector2;

public class Label {
	private String content;
	private GuiManager manager;
	private Vector2 pos, size;
	private float[] color;
	
	public Label(GuiManager manager, String content, Vector2 pos,  int width, int height) {
		this.manager = manager;
		this.content = content;
		this.pos = pos;
		size = new Vector2(width,height);
		color = new float[4];
		color[0] = 1;
		color[1] = 1;
		color[2] = 1;
		color[3] = 1;
	}

	public void render() {
		glColor4f(color[0], color[1], color[2], color[3]);
		manager.getFont().drawString(content, pos.x, pos.y, size.x, size.y);
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
	
}
