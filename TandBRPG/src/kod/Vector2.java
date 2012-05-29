package kod;

public class Vector2 {
	public float x, y;

	public Vector2() {
	}

	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}


	public static Vector2 divide(Vector2 a, float b) {
		Vector2 c = new Vector2();
		c.x = a.x / b;
		c.y = a.y / b;
		return c;
	}

	public static Vector2 add(Vector2 a, Vector2 b) {
		Vector2 c = new Vector2();
		c.x = a.x + b.x;
		c.y = a.y + b.y;
		return c;
	}

	public static Vector2 multiply(Vector2 a, float b) {
		Vector2 c = new Vector2();
		c.x = a.x * b;
		c.y = a.y * b;
		return c;
	}
}
