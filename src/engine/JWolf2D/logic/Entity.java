package engine.JWolf2D.logic;

import engine.JWolf2D.geom.Rectangle2;
import engine.JWolf2D.geom.Vector2;

public interface Entity {
	public Vector2 getPos();
	public void setPos(Vector2 pos);
	public void update(long deltaTime);
	public void checkCollision();
	public boolean collides(Rectangle2 rect);
	public void addVelocity(float x, float y);
	public void render(int xoff, int yoff);
	public void damage(int amount);
	public void die();
	public Vector2 getSize();
	public String getName();
	public String getID();
}
