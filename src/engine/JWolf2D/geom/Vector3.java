package engine.JWolf2D.geom;


public class Vector3
{
	public float x;
	public float y;
	public float z;

	Vector3()
	{
		x = 0;
		y = 0;
		z = 0;
	}
	
	public Vector3(float vx, float vy, float vz)
	{
		x = vx;
		y = vy;
		z = vz;
	}
	
	Vector3(int vx, int vy, int vz)
	{
		x = (float) vx;
		y = (float) vy;
		z = (float) vz;
	}
	
	public Vector3(double vx, double vy, double vz)
	{
		x = (float) vx;
		y = (float) vy;
		z = (float) vz;
	}
	
	public Vector3(Vector2 v)
	{
		x = v.x;
		y = v.y;
		z = 0;
	}
	
	public Vector3(Vector3 v)
	{
		x = v.x;
		y = v.y;
		z = v.z;
	}
	
	public float magnitude()
	{
		return (float) Math.sqrt(x*x + y*y + z*z);
	}

	public void normalize()
	{
		float m = magnitude();

		x /= m;
		y /= m;
		z /= m;
	}

	public void scale(float s)
	{
		x *= s;
		y *= s;
		z *= s;
	}
	
	public float distance(Vector2 other) {
		float a = x - other.x;
		float b = y - other.y;
		float csqrd = a*a + b*b;
		float c = (float) Math.sqrt(csqrd);
		return c;
	}

	public String toString()
	{
		return "vector " + x + ", " + y + ", " + z;
	}
}
