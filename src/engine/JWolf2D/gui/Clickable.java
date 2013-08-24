package engine.JWolf2D.gui;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import engine.JWolf2D.geom.Rectangle2;
import engine.JWolf2D.geom.Vector2;
import engine.JWolf2D.graphic.Sprite;

public class Clickable {
	protected Rectangle2 rect;
	protected String texName, texNameDown;
	protected Sprite sprite;
	protected Vector2 pos;
	protected boolean down = false;;
	
	public void init() {
		sprite = new Sprite(pos, texName);
		rect = new Rectangle2(pos, new Vector2(sprite.getWidth(), sprite.getHeight()));
	}
	
	public void update() {
		if(Mouse.getEventButton() == 0 && Mouse.getEventButtonState()) {
			if(rect.containsPoint(new Vector2(Mouse.getX(), (Mouse.getY()*-1)+Display.getHeight()))) {
				down = true;
				System.out.println("Clickable clicked!");
			}
		}else {
			down = false;
		}
	}
	
	public boolean clicked() {
		boolean temp = down;
		down = false;
		return temp;
	}
	
	public void setDown(boolean down) {
		this.down = down;
	}
	
	public void render() {
		sprite.render(0, 0);
	}
}
