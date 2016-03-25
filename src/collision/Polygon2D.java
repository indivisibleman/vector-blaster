package collision;

/**
 *
 * @author Michael Topsom
 */
public class Polygon2D {
	private Vector[] points;
	private Vector[] edges;
	
	private int length;
	
	public Polygon2D(Vector[] points) {
		this.points = points;
		
		edges = new Vector[points.length];
		
		for(int i = 0; i < points.length; i++) {
			edges[i] = new Vector(points[(i + 1) % points.length].getX() - points[i].getX(), points[(i + 1) % points.length].getY() - points[i].getY());
		}
		
		length = points.length;
	}
	
	private Projection projectToAxis(Vector vector) {
		double min = Double.POSITIVE_INFINITY;
		double max = Double.NEGATIVE_INFINITY;
		
		for(int i = 0; i < points.length; i++) {
			if(points[i].dotProduct(vector) < min) {
				min = points[i].dotProduct(vector);
			}
			
			if(points[i].dotProduct(vector) > max) {
				max = points[i].dotProduct(vector);
			}
		}
		
		return new Projection(min, max);
	}
	
	public boolean intersects(Polygon2D polygon) {
		Vector[] combinedEdges = new Vector[polygon.length + length];
		
		System.arraycopy(edges, 0, combinedEdges, 0, edges.length);
		System.arraycopy(polygon.edges, 0, combinedEdges, length, polygon.length);
		
		Vector axis;
		Projection a;
		Projection b;
		
		for(int i = 0; i < combinedEdges.length; i++) {
			axis = combinedEdges[i].normalise().perpendicular();
			
			a = this.projectToAxis(axis);
			b = polygon.projectToAxis(axis);
			
			if(!a.intersects(b)) {
				return false;
			}
		}
		
		return true;
	}
}
