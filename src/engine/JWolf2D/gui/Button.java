package engine.JWolf2D.gui;

import engine.JWolf2D.geom.Vector2;
import engine.JWolf2D.resource.Textures;

public class Button extends Clickable {
	private GuiManager manager;
	private Label label;
	
	public Button(Vector2 pos, GuiManager manager, String title) {
		texName = "defaultButtonUp";
		texNameDown = "defaultButtonDown";
		this.pos = pos;
		init();
		this.manager = manager;
		label = new Label(manager, title, new Vector2(pos.x+((Textures.get().get(texName).getTextureWidth()/2)-9*title.length()), pos.y+9), 18, 18);
		manager.addLabel(label);
	}
	
	public Button(Vector2 pos, GuiManager manager, String title, String texName, String texNameDown) {
		if(Textures.get().get(texName) != null){
			this.texName = texName;
		}else {
			texName = "defaultButtonUp";
		}
		if(Textures.get().get(texNameDown) != null) {
			this.texNameDown = texNameDown;
		}else {
			texNameDown = "defaultButtonDown";
		}
		
		
		this.pos = pos;
		init();
		this.manager = manager;
		label = new Label(manager, title, new Vector2(pos.x+((Textures.get().get(texName).getTextureWidth()/2)-9*title.length()), pos.y+getHeight()/2-9), 18, 18);
		manager.addLabel(label);
	}
	
	public void render() {
		if(down)
			sprite.setTexture(texNameDown);
		else
			sprite.setTexture(texName);
		sprite.render(0, 0);
	}
	
	public void setDownTexture(String texNameDown) {
		this.texNameDown = texName;
	}
	
	public void setUpTexture(String texName) {
		this.texName = texName;
	}
	
	public int getWidth() {
		return Textures.get().get(texName).getTextureWidth();
	}
	
	public int getHeight() {
		return Textures.get().get(texName).getTextureHeight();
	}
}
