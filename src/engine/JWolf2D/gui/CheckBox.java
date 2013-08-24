package engine.JWolf2D.gui;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import engine.JWolf2D.geom.Vector2;
import engine.JWolf2D.resource.Textures;

public class CheckBox extends Clickable {
	private GuiManager manager;
	public boolean checked;
	
	public CheckBox(Vector2 pos, GuiManager manager) {
		this.pos = pos;
		this.manager = manager;
		texName = "unchecked";
		texNameDown = "checked";
		init();
		checked = false;
	}
	
	public void update() {
		if(Mouse.getEventButton() == 0 && Mouse.getEventButtonState()) {
			if(rect.containsPoint(new Vector2(Mouse.getX(), (Mouse.getY()*-1)+Display.getHeight()))) {
				down = true;
				if(checked == false) {
					sprite.setTexture("checked");
					checked = true;
					System.out.println("Checkbox checked!");
					down = false;
				}
				if(checked == true && down == true) {
					sprite.setTexture("unchecked");
					checked = false;
					System.out.println("Checkbox unchecked!");
					down = false;
				}
			}
		}else {
			down = false;
		}
	}
}