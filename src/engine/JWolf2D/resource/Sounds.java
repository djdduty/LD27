package engine.JWolf2D.resource;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;

public class Sounds {
	private static Sounds instance;
	
	public static Sounds get() {
		if(instance == null)
			instance = new Sounds();
		
		return instance;
	}
	
	private Sounds() {}
	
	public Audio extract(String path) {
		path = path.trim().replace('\\',  '/');
		
		if(path.startsWith("/"))
			path = path.substring(1);
		
		try {
			return AudioLoader.getAudio(path.substring(path.lastIndexOf('.')+1).toUpperCase().trim(),Sounds.class.getClassLoader().getResourceAsStream(path));
		}
		catch(Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}
}
