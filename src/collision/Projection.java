package collision;

/**
 *
 * @author Michael Topsom
 */
class Projection {
	private double min;
	private double max;
	
	Projection(double min, double max) {
		this.min = min;
		this.max = max;
	}
	
	public double getMin() {
		return min;
	}
	
	public double getMax() {
		return max;
	}
	
	boolean intersects(Projection projection) {
		return max > projection.getMin() && projection.getMax() > min;
	}
}
