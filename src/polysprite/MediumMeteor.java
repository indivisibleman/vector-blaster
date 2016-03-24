package polysprite;

import java.util.ArrayList;

import collision.Vector2D;
import core.Constants.MeteorType;

/**
 *
 * @author Michael Topsom
 */
public class MediumMeteor extends PolySprite {
	private ArrayList<Vector2D[]> mediumMeteorImages;
	
	private ArrayList<Vector2D[]> mediumMeteorColliders;
	
	public MediumMeteor(Vector2D position) {
		super();
		
		mediumMeteorImages = new ArrayList<>();
		
		Vector2D[] pointsIn = { new Vector2D(2.0, 10.0), new Vector2D(7.0, 10.0), new Vector2D(13.0, 7.0), new Vector2D(14.0, -1.0), new Vector2D(10.0, -5.0),
				new Vector2D(11.0, -12.0), new Vector2D(4.0, -14.0), new Vector2D(-2.0, -12.0), new Vector2D(-7.0, -14.0), new Vector2D(-13.0, -7.0), new Vector2D(-12.0, -2.0),
				new Vector2D(-14.0, 0.0), new Vector2D(-13.0, 9.0), new Vector2D(-6.0, 15.0), new Vector2D(-1.0, 13.0) };
		
		mediumMeteorImages.add(pointsIn);
		
		mediumMeteorColliders = new ArrayList<>();
		
		Vector2D[] pointsInCollidersOne = { new Vector2D(2.0, 10.0), new Vector2D(7.0, 10.0), new Vector2D(13.0, 7.0), new Vector2D(14.0, -1.0), new Vector2D(10.0, -5.0) };
		
		mediumMeteorColliders.add(pointsInCollidersOne);
		
		Vector2D[] pointsInCollidersTwo = { new Vector2D(10.0, -5.0), new Vector2D(11.0, -12.0), new Vector2D(4.0, -14.0), new Vector2D(-2.0, -12.0) };
		
		mediumMeteorColliders.add(pointsInCollidersTwo);
		
		Vector2D[] pointsInCollidersThree = { new Vector2D(-2.0, -12.0), new Vector2D(-7.0, -14.0), new Vector2D(-13.0, -7.0), new Vector2D(-12.0, -2.0) };
		
		mediumMeteorColliders.add(pointsInCollidersThree);
		
		Vector2D[] pointsInCollidersFour = { new Vector2D(2.0, 10.0), new Vector2D(10.0, -5.0), new Vector2D(-2.0, -12.0), new Vector2D(-12.0, -2.0), new Vector2D(-14.0, 0.0),
				new Vector2D(-13.0, 9.0), new Vector2D(-6.0, 15.0), new Vector2D(-1.0, 13.0) };
		
		mediumMeteorColliders.add(pointsInCollidersFour);
		
		initialise(mediumMeteorImages, position, (Math.PI * 2.0 * constants.getRandomDouble()) - Math.PI, (constants.getRandomDouble() * 0.2) - 0.1,
				MeteorType.MEDIUM);
	}
}
