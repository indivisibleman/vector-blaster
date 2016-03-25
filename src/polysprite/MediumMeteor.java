package polysprite;

import java.util.ArrayList;

import collision.Vector;
import core.Constants.MeteorType;

/**
 *
 * @author Michael Topsom
 */
public class MediumMeteor extends PolySprite {
	private ArrayList<Vector[]> mediumMeteorImages;
	
	private ArrayList<Vector[]> mediumMeteorColliders;
	
	public MediumMeteor(Vector position) {
		super();
		
		mediumMeteorImages = new ArrayList<>();
		
		Vector[] pointsIn = { new Vector(2.0, 10.0), new Vector(7.0, 10.0), new Vector(13.0, 7.0), new Vector(14.0, -1.0), new Vector(10.0, -5.0),
				new Vector(11.0, -12.0), new Vector(4.0, -14.0), new Vector(-2.0, -12.0), new Vector(-7.0, -14.0), new Vector(-13.0, -7.0), new Vector(-12.0, -2.0),
				new Vector(-14.0, 0.0), new Vector(-13.0, 9.0), new Vector(-6.0, 15.0), new Vector(-1.0, 13.0) };
		
		mediumMeteorImages.add(pointsIn);
		
		mediumMeteorColliders = new ArrayList<>();
		
		Vector[] pointsInCollidersOne = { new Vector(2.0, 10.0), new Vector(7.0, 10.0), new Vector(13.0, 7.0), new Vector(14.0, -1.0), new Vector(10.0, -5.0) };
		
		mediumMeteorColliders.add(pointsInCollidersOne);
		
		Vector[] pointsInCollidersTwo = { new Vector(10.0, -5.0), new Vector(11.0, -12.0), new Vector(4.0, -14.0), new Vector(-2.0, -12.0) };
		
		mediumMeteorColliders.add(pointsInCollidersTwo);
		
		Vector[] pointsInCollidersThree = { new Vector(-2.0, -12.0), new Vector(-7.0, -14.0), new Vector(-13.0, -7.0), new Vector(-12.0, -2.0) };
		
		mediumMeteorColliders.add(pointsInCollidersThree);
		
		Vector[] pointsInCollidersFour = { new Vector(2.0, 10.0), new Vector(10.0, -5.0), new Vector(-2.0, -12.0), new Vector(-12.0, -2.0), new Vector(-14.0, 0.0),
				new Vector(-13.0, 9.0), new Vector(-6.0, 15.0), new Vector(-1.0, 13.0) };
		
		mediumMeteorColliders.add(pointsInCollidersFour);
		
		initialise(mediumMeteorImages, position, (Math.PI * 2.0 * constants.getRandomDouble()) - Math.PI, (constants.getRandomDouble() * 0.2) - 0.1,
				MeteorType.MEDIUM);
	}
}
