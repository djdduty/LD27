package engine.JWolf2D.geom;

public class Rectangle2
{
	public Vector2 top = new Vector2(0,0);
	public Vector2 bot = new Vector2(0,0);
	
	public Rectangle2()
	{
	}
	
	public Rectangle2(Vector2 pos, Vector2 size)
	{
		this.top = new Vector2(pos);
		this.bot = new Vector2(pos.x + size.x, pos.y + size.y);
	}
	
	public void move(Vector2 trans)
	{
		top.x += trans.x;
		top.y += trans.y;
		bot.x += trans.x;
		bot.y += trans.y;
	}
	
	public boolean intersects(Rectangle2 other)
	{
		return intersects(other, true);
	}
	
	public boolean intersects(Rectangle2 other, boolean recurse)
	{
		boolean res = !(bot.x <= other.top.x ||
				 top.x >= other.bot.x ||
				 bot.y <= other.top.y ||
				 top.y >= other.bot.y);
		
		if (!res && recurse)
			res = other.intersects(this, false);
			
		return res;
	}
	
	public boolean containsPoint(Vector2 p)
	{
		return (p.x > top.x) && (p.x < bot.x) && (p.y > top.y) && (p.y < bot.y);
	}
	
	public String toString()
	{
		return "Rectangle2 " + top + ", " + bot;
	}
}
