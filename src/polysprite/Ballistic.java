package polysprite;

import java.util.ArrayList;
import java.util.List;

import collision.Vector;

/**
 *
 * @author Michael Topsom
 */
public class Ballistic extends Sprite {
	private List<List<Vector>> ballisticImages;
	private List<List<Vector>> ballisticColliders;
	
	private int alive = 200;
	
	public Ballistic(Vector position, double orientation) {
		super();
		
		ballisticImages = new ArrayList<>();
		
		List<Vector> ballisticImage = new ArrayList<>();
		
		ballisticImage.add(new Vector(2.0, 2.0));
		ballisticImage.add(new Vector(-2.0, 2.0));
		ballisticImage.add(new Vector(-2.0, -2.0));
		ballisticImage.add(new Vector(2.0, -2.0));
		
		ballisticImages.add(ballisticImage);
		
		ballisticImage = new ArrayList<>();
		
		ballisticImage.add(new Vector(2.5, 0.0));
		ballisticImage.add(new Vector(-2.5, 0.0));
		
		ballisticImages.add(ballisticImage);
		
		ballisticImage.add(new Vector(0.0, 2.5));
		ballisticImage.add(new Vector(0.0, -2.5));
		
		ballisticImages.add(ballisticImage);
		
		ballisticColliders = new ArrayList<>();
		
		List<Vector> ballisticCollider = new ArrayList<>();
		
		ballisticCollider.add(new Vector(2.0, 2.0));
		ballisticCollider.add(new Vector(-2.0, 2.0));
		ballisticCollider.add(new Vector(-2.0, -2.0));
		ballisticCollider.add(new Vector(2.0, -2.0));
		
		ballisticColliders.add(ballisticCollider);
		
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
