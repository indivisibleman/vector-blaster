package collision;

/**
 *
 * @author Michael Topsom
 */
public class Vector2D {
	private double x;
	private double y;
	
	public Vector2D() {
		x = 0.0;
		y = 1.0;
	}
	
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double dotProduct(Vector2D vector) {
		return (x * vector.getX()) + (y * vector.getY());
	}
	
	public void add(Vector2D vector) {
		x += vector.x;
		y += vector.y;
	}
	
	public void subtract(Vector2D vector) {
		x -= vector.x;
		y -= vector.y;
	}
	
	public void multiply(double scalar) {
		x *= scalar;
		y *= scalar;
	}
	
	public void divide(double scalar) {
		this.multiply(1.0 / scalar);
	}
	
	public double magnitude() {
		return Math.sqrt((x * x) + (y * y));
	}
	
	public Vector2D normalise() {
		double inverseMagnitude = 1.0 / this.magnitude();
		
		return new Vector2D(x * inverseMagnitude, y * inverseMagnitude);
	}
	
	public Vector2D perpendicular() {
		return new Vector2D(y * -1.0, x);
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
}
