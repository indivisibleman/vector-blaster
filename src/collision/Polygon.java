package collision;

import java.util.ArrayList;
import java.util.List;

public class Polygon {
	private List<Vector> points;
	private List<Vector> edges;
	
	public Polygon(List<Vector> points) {
		this.points = points;
		
		edges = new ArrayList<>(points.size());
		
		for(int i = 0; i < points.size(); i++) {
			edges.add(i, new Vector(points.get((i + 1) % points.size()).getX() - points.get(i).getX(), points.get((i + 1) % points.size()).getY() - points.get(i).getY()));
		}
	}
	
	private Projection projectToAxis(Vector vector) {
		double min = Double.POSITIVE_INFINITY;
		double max = Double.NEGATIVE_INFINITY;
		
		for(Vector point : points) {
			if(point.dotProduct(vector) < min) {
				min = point.dotProduct(vector);
			}
			
			if(point.dotProduct(vector) > max) {
				max = point.dotProduct(vector);
			}
		}
		
		return new Projection(min, max);
	}
	
	public boolean intersects(Polygon polygon) {
		List<Vector> combinedEdges = new ArrayList<>();
		
		combinedEdges.addAll(this.edges);
		combinedEdges.addAll(polygon.edges);
		
		Vector axis;
		Projection a;
		Projection b;
		
		for(Vector edge : combinedEdges) {
			axis = edge.normalise().perpendicular();
			
			a = this.projectToAxis(axis);
			b = polygon.projectToAxis(axis);
			
			if(!a.intersects(b)) {
				return false;
			}
		}
		
		return true;
	}
}
