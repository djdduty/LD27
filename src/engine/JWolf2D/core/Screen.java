package engine.JWolf2D.core;

public interface Screen {
	public void init(ScreenManager manager);
	public void update(long deltaTime);
	public void render();
	public boolean getStarted();
}
