package polysprite;

import java.util.ArrayList;
import java.util.List;

import collision.Vector;

/**
 *
 * @author Michael Topsom
 */
public class Ship extends Sprite {
	private List<List<Vector>> shipImages;
	private List<List<Vector>> shipColliders;
	
	public Ship(Vector position) {
		super();
		
		shipImages = new ArrayList<>();
		
		List<Vector> shipImage = new ArrayList<>();
		
		shipImage.add(new Vector(20.0, 0.0));
		shipImage.add(new Vector(-12.0, 10.0));
		shipImage.add(new Vector(-8.0, 0.0));
		shipImage.add(new Vector(-12.0, -10.0));
		
		shipImages.add(shipImage);
		
		shipColliders = new ArrayList<>();
		
		List<Vector> shipCollider = new ArrayList<>();
		
		shipCollider.add(new Vector(20.0, 0.0));
		shipCollider.add(new Vector(-12.0, 10.0));
		shipCollider.add(new Vector(-8.0, 0.0));
		shipCollider.add(new Vector(-12.0, -10.0));
		
		shipColliders.add(shipCollider);
		
		initialise(shipImages, position, 0.0, 0.0);
	}
	
	public double getOrientation() {
		return orientation;
	}
	
	public Vector getFirePosition() {
		return new Vector(position.getX() + 20.0 * Math.cos(orientation), position.getY() + 20.0 * Math.sin(orientation));
	}
}
