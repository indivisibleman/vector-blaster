package polysprite;

import java.util.ArrayList;

import collision.Vector2D;
import core.Constants.MeteorType;

/**
 *
 * @author Michael Topsom
 */
public class LargeMeteor extends PolySprite {
	private ArrayList<Vector2D[]> largeMeteorImages;
	
	private ArrayList<Vector2D[]> largeMeteorColliders;
	
	public LargeMeteor(Vector2D position) {
		super();
		
		largeMeteorImages = new ArrayList<>();
		
		Vector2D[] pointsIn = { new Vector2D(0.0, 22.0), new Vector2D(12.0, 18.0), new Vector2D(18.0, 20.0), new Vector2D(23.0, 15.0), new Vector2D(22.0, 10.0),
				new Vector2D(18.0, 5.0), new Vector2D(23.0, -3.0), new Vector2D(21.0, -10.0), new Vector2D(17.0, -14.0), new Vector2D(16.0, -19.0), new Vector2D(3.0, -22.0),
				new Vector2D(-14.0, -20.0), new Vector2D(-17.0, -15.0), new Vector2D(-23.0, -12.0), new Vector2D(-23.0, -2.0), new Vector2D(-18.0, 3.0), new Vector2D(-22.0, 11.0),
				new Vector2D(-17.0, 20.0) };
		
		largeMeteorImages.add(pointsIn);
		
		largeMeteorColliders = new ArrayList<>();
		
		Vector2D[] pointsInCollidersOne = { new Vector2D(12.0, 18.0), new Vector2D(18.0, 20.0), new Vector2D(23.0, 15.0), new Vector2D(22.0, 10.0), new Vector2D(18.0, 5.0),
				new Vector2D(-18.0, 3.0) };
		
		largeMeteorColliders.add(pointsInCollidersOne);
		
		Vector2D[] pointsInCollidersTwo = { new Vector2D(18.0, 5.0), new Vector2D(23.0, -3.0), new Vector2D(21.0, -10.0), new Vector2D(17.0, -14.0), new Vector2D(-18.0, 3.0) };
		
		largeMeteorColliders.add(pointsInCollidersTwo);
		
		Vector2D[] pointsInCollidersThree = { new Vector2D(17.0, -14.0), new Vector2D(16.0, -19.0), new Vector2D(3.0, -22.0), new Vector2D(-14.0, -20.0),
				new Vector2D(-17.0, -15.0), new Vector2D(-18.0, 3.0) };
		
		largeMeteorColliders.add(pointsInCollidersThree);
		
		Vector2D[] pointsInCollidersFour = { new Vector2D(-17.0, -15.0), new Vector2D(-23.0, -12.0), new Vector2D(-23.0, -2.0), new Vector2D(-18.0, 3.0) };
		
		largeMeteorColliders.add(pointsInCollidersFour);
		
		Vector2D[] pointsInCollidersFive = { new Vector2D(-18.0, 3.0), new Vector2D(-22.0, 11.0), new Vector2D(-17.0, 20.0), new Vector2D(0.0, 22.0), new Vector2D(12.0, 18.0) };
		
		largeMeteorColliders.add(pointsInCollidersFive);
		
		initialise(largeMeteorImages, position, (Math.PI * 2.0 * constants.getRandomDouble()) - Math.PI, (constants.getRandomDouble() * 0.2) - 0.1,
				MeteorType.LARGE);
	}
}
