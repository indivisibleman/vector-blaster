package polysprite;

import java.util.ArrayList;

import collision.Vector2D;

/**
 *
 * @author Michael Topsom
 */
public class Ship extends PolySprite {
	private ArrayList<Vector2D[]> shipImages;
	
	private ArrayList<Vector2D[]> shipColliders;
	
	public Ship(Vector2D position) {
		super();
		
		shipImages = new ArrayList<>();
		
		Vector2D[] pointsIn = { new Vector2D(20.0, 0.0), new Vector2D(-12.0, 10.0), new Vector2D(-8.0, 0.0), new Vector2D(-12.0, -10.0) };
		
		shipImages.add(pointsIn);
		
		shipColliders = new ArrayList<>();
		
		shipColliders.add(pointsIn);
		
		initialise(shipImages, position, 0.0, 0.0, shipColliders);
	}
	
	public double getOrientation() {
		return orientation;
	}
	
	public Vector2D getFirePosition() {
		return new Vector2D(position.getX() + 20.0 * Math.cos(orientation), position.getY() + 20.0 * Math.sin(orientation));
	}
}
