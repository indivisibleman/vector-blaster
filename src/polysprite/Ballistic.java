package polysprite;

import java.util.ArrayList;

import collision.Vector;

/**
 *
 * @author Michael Topsom
 */
public class Ballistic extends PolySprite {
	private ArrayList<Vector[]> ballisticImages;
	
	private ArrayList<Vector[]> ballisticColliders;
	
	private int alive = 200;
	
	public Ballistic(Vector position, double orientation) {
		super();
		
		ballisticImages = new ArrayList<>();
		
		Vector[] pointsIn = { new Vector(2.0, 2.0), new Vector(-2.0, 2.0), new Vector(-2.0, -2.0), new Vector(2.0, -2.0) };
		
		ballisticImages.add(pointsIn);
		
		Vector[] pointsInTwo = { new Vector(2.5, 0.0), new Vector(-2.5, 0.0) };
		
		ballisticImages.add(pointsInTwo);
		
		Vector[] pointsInThree = { new Vector(0.0, 2.5), new Vector(0.0, -2.5) };
		
		ballisticImages.add(pointsInThree);
		
		ballisticColliders = new ArrayList<>();
		
		ballisticColliders.add(pointsIn);
		
		initialise(ballisticImages, position, orientation, 0.0);
	}
	
	public void update() {
		this.update(0.0, 4.0);
		
		alive--;
	}
	
	public boolean kill() {
		return alive < 0;
	}
}
