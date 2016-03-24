package polysprite;

import java.util.ArrayList;

import collision.Vector2D;

/**
 *
 * @author Michael Topsom
 */
public class Ballistic extends PolySprite {
	private ArrayList<Vector2D[]> ballisticImages;
	
	private ArrayList<Vector2D[]> ballisticColliders;
	
	private int alive = 200;
	
	public Ballistic(Vector2D position, double orientation) {
		super();
		
		ballisticImages = new ArrayList<>();
		
		Vector2D[] pointsIn = { new Vector2D(2.0, 2.0), new Vector2D(-2.0, 2.0), new Vector2D(-2.0, -2.0), new Vector2D(2.0, -2.0) };
		
		ballisticImages.add(pointsIn);
		
		Vector2D[] pointsInTwo = { new Vector2D(2.5, 0.0), new Vector2D(-2.5, 0.0) };
		
		ballisticImages.add(pointsInTwo);
		
		Vector2D[] pointsInThree = { new Vector2D(0.0, 2.5), new Vector2D(0.0, -2.5) };
		
		ballisticImages.add(pointsInThree);
		
		ballisticColliders = new ArrayList<>();
		
		ballisticColliders.add(pointsIn);
		
		initialise(ballisticImages, position, orientation, 0.0, ballisticColliders);
	}
	
	public void update() {
		this.update(0.0, 4.0);
		
		alive--;
	}
	
	public boolean kill() {
		return alive < 0;
	}
}
