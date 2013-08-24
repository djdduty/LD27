package engine.JWolf2D.gui;

import engine.JWolf2D.resource.Textures;
import engine.JWolf2D.util.Bag;

public class GuiManager {
	private Bag<Label> labels;
	private Bag<Button> buttons;
	private Bag<CheckBox> checkboxes;
	private Font font;

	public GuiManager(String fontPath) {
		font = new Font(Textures.get().add("font", fontPath),32,32,new String[] {"ABCDEFGHIJKLMNOPQRSTUVWXYZ","0123456789:;,.-'()!\"@$?_~*/\\+"});
		labels = new Bag<Label>();
		buttons = new Bag<Button>();
		checkboxes = new Bag<CheckBox>();
	
		//Textures.get().add("checked", "res/images/gui/checked.png");
		//Textures.get().add("unchecked", "res/images/gui/unchecked.png");
		//Textures.get().add("defaultButtonUp", "res/images/gui/buttonUp.png");
		//Textures.get().add("defaultButtonDown", "res/images/gui/buttonDown.png");
	}
	
	public void update() {
		for(CheckBox c : checkboxes) {
			c.update();
		}
		for(Button b : buttons) {
			b.update();
		}
	}
	
	public void render() {
		for(Button b : buttons) {
			b.render();
		}
		for(CheckBox c : checkboxes) {
			c.render();
		}
		for(Label l : labels) {
			l.render();
		}
	}
	
	public Font getFont() {
		return font;
	}
	
	public void addLabel(Label label) {
		labels.add(label);
	}
	
	public void addButton(Button button) {
		buttons.add(button);
	}
	public void addCheckBox(CheckBox checkbox) {
		checkboxes.add(checkbox);
	}
}
