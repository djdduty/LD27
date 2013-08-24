package engine.JWolf2D.logic;

import engine.JWolf2D.geom.Rectangle2;
import engine.JWolf2D.geom.Vector2;
import engine.JWolf2D.graphic.Sprite;
import engine.JWolf2D.level.Layer;

public class EnemyExample implements Entity{
	private Vector2 size, pos;
	private String type, id;
	private Sprite sprite;
	private int health = 100;
	private boolean isCollide,jumping;
	private Vector2 velocity;
	private Layer layer;
	private Player player;
	private float gravity = 0.5f;
	
	public EnemyExample(Vector2 pos, Layer l) {
		this.pos = pos;
		layer = l;
		sprite = new Sprite(pos, "entity1");
		size = new Vector2(sprite.getWidth(), sprite.getHeight());
		velocity = new Vector2(0,0);
		//id =
		type = "testEntity";
		player = layer.getLevel().getPlayer();
		health = 100;
	}
	
	public void damage(int amount) {
		health -= amount;
	}
	
	public void update(long deltaTime) {
		gravity = layer.getGravity();
		velocity.x = 0;
		if(health > 0) {
			if(player != null) {
				if(player.getPos().x < pos.x)
					addVelocity(-100.0f*(deltaTime/1000.0f), 0);
				if(player.getPos().x > pos.x)
					addVelocity(100.0f*(deltaTime/1000.0f), 0);
				if(player.getPos().y < pos.y && jumping == false)
					addVelocity(0, -8);
			}
		}else {
			die();
		}
		checkCollision();
		sprite.setPos(pos);
	}
	
	public void setPos(Vector2 pos) {
		this.pos = pos;
	}
	
	public void die() {
		
	}
	
	public Vector2 getPos() {
		return pos;
	}
	
	public void checkCollision() {
		float oldX = pos.x;
		float oldY = pos.y;
		
		pos.x += velocity.x;
		if(layer.collidesWithObjects(new Rectangle2(pos, size))) {
			pos.x = oldX;
			if(jumping == false)
				addVelocity(0, -3);
		}
		velocity.x = 0;
		pos.y += velocity.y;
		if(layer.collidesWithObjects(new Rectangle2(pos, size))) {
			if(layer.collidesWithObjects(new Rectangle2(new Vector2(pos.x+6, pos.y+12), new Vector2(size.x-12, size.y-10)))) {
				jumping = false;
				oldY = layer.getCollidesWithObject(new Rectangle2(pos, size)).getPos().y-(size.y);
			}
			pos.y = oldY;
			velocity.y = 0;
		}else {
			jumping = true;
			if(velocity.y < 10) {
				velocity.y += gravity;
			}
		}
	}
	
	public void addVelocity(float x, float y) {
		this.velocity.x += x;
		this.velocity.y += y;
	}
	
	public boolean collides(Rectangle2 rect) {
		isCollide = rect.intersects(new Rectangle2(pos, size));
		return isCollide;
	}

	public void render(int xoff, int yoff) {
		sprite.render(xoff, yoff);
	}

	public Vector2 getSize() {
		return size;
	}

	public String getName() {
		return type;
	}

	public String getID() {
		return id;
	}
}
