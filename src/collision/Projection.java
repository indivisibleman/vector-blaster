package collision;

/**
 *
 * @author Michael Topsom
 */
public class Projection {
	private double min;
	private double max;
	
	public Projection(double min, double max) {
		this.min = min;
		this.max = max;
	}
	
	public double getMin() {
		return min;
	}
	
	public double getMax() {
		return max;
	}
	
	public boolean intersects(Projection projection) {
		return max > projection.getMin() && projection.getMax() > min;
	}
}
