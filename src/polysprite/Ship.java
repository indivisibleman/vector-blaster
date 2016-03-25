package polysprite;

import java.util.ArrayList;

import collision.Vector;

/**
 *
 * @author Michael Topsom
 */
public class Ship extends PolySprite {
	private ArrayList<Vector[]> shipImages;
	
	private ArrayList<Vector[]> shipColliders;
	
	public Ship(Vector position) {
		super();
		
		shipImages = new ArrayList<>();
		
		Vector[] pointsIn = { new Vector(20.0, 0.0), new Vector(-12.0, 10.0), new Vector(-8.0, 0.0), new Vector(-12.0, -10.0) };
		
		shipImages.add(pointsIn);
		
		shipColliders = new ArrayList<>();
		
		shipColliders.add(pointsIn);
		
		initialise(shipImages, position, 0.0, 0.0);
	}
	
	public double getOrientation() {
		return orientation;
	}
	
	public Vector getFirePosition() {
		return new Vector(position.getX() + 20.0 * Math.cos(orientation), position.getY() + 20.0 * Math.sin(orientation));
	}
}
