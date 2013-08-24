package engine.JWolf2D.resource;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Textures extends Assets<Texture> {
	private static Textures instance;
	
	public static Textures get() {
		if(instance == null)
			instance = new Textures();
		
		return instance;
	}
	
	private Textures() {}
	
	public Texture extract(String path) {
		path = path.replace('\\', '/');
				
		if(path.startsWith("/"))
			path = path.substring(1);
		
		try{
			return TextureLoader.getTexture(path.substring(path.lastIndexOf('.')+1).toUpperCase(),Textures.class.getClassLoader().getResourceAsStream(path));
		}
		catch(Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}
}
