package engine.JWolf2D.core;

public class ScreenManager {
	private Game game;
	private Screen currentScreen;
	
	public ScreenManager(Screen s) {
		currentScreen = s;
	}
	
	public void init(Game game) {
		this.game = game;
		currentScreen.init(this);
	}
	
	public Game getGame() {
		return game;
	}
	
	public void setScreen(Screen newScreen) {
		if(newScreen == null)
			throw new NullPointerException("newScreen cannot be null.");
		currentScreen = newScreen;
		if(!currentScreen.getStarted())
			currentScreen.init(this);
	}
	
	public Screen getScreen() {
		return currentScreen;
	}
	
	public void update(long deltaTime) {
		try {
			currentScreen.update(deltaTime);
		}
		catch(Exception exc) {
			
		}
	}
	
	public void render() {
		try {
			currentScreen.render();
		}
		catch(Exception exc) {
			
		}
	}
}
