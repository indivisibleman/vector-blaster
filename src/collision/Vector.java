package collision;

/**
 *
 * @author Michael Topsom
 */
public class Vector {
	private double x;
	private double y;
	
	public Vector() {
		x = 0.0;
		y = 1.0;
	}
	
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	double dotProduct(Vector vector) {
		return (x * vector.getX()) + (y * vector.getY());
	}
	
	public void add(Vector vector) {
		x += vector.x;
		y += vector.y;
	}
	
	public void multiply(double scalar) {
		x *= scalar;
		y *= scalar;
	}
	
	private double magnitude() {
		return Math.sqrt((x * x) + (y * y));
	}
	
	Vector normalise() {
		double inverseMagnitude = 1.0 / this.magnitude();
		
		return new Vector(x * inverseMagnitude, y * inverseMagnitude);
	}
	
	Vector perpendicular() {
		return new Vector(y * -1.0, x);
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
}
