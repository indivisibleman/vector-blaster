package polysprite;

import java.util.ArrayList;

import collision.Vector;
import core.Constants.MeteorType;

/**
 *
 * @author Michael Topsom
 */
public class LargeMeteor extends PolySprite {
	private ArrayList<Vector[]> largeMeteorImages;
	
	private ArrayList<Vector[]> largeMeteorColliders;
	
	public LargeMeteor(Vector position) {
		super();
		
		largeMeteorImages = new ArrayList<>();
		
		Vector[] pointsIn = { new Vector(0.0, 22.0), new Vector(12.0, 18.0), new Vector(18.0, 20.0), new Vector(23.0, 15.0), new Vector(22.0, 10.0),
				new Vector(18.0, 5.0), new Vector(23.0, -3.0), new Vector(21.0, -10.0), new Vector(17.0, -14.0), new Vector(16.0, -19.0), new Vector(3.0, -22.0),
				new Vector(-14.0, -20.0), new Vector(-17.0, -15.0), new Vector(-23.0, -12.0), new Vector(-23.0, -2.0), new Vector(-18.0, 3.0), new Vector(-22.0, 11.0),
				new Vector(-17.0, 20.0) };
		
		largeMeteorImages.add(pointsIn);
		
		largeMeteorColliders = new ArrayList<>();
		
		Vector[] pointsInCollidersOne = { new Vector(12.0, 18.0), new Vector(18.0, 20.0), new Vector(23.0, 15.0), new Vector(22.0, 10.0), new Vector(18.0, 5.0),
				new Vector(-18.0, 3.0) };
		
		largeMeteorColliders.add(pointsInCollidersOne);
		
		Vector[] pointsInCollidersTwo = { new Vector(18.0, 5.0), new Vector(23.0, -3.0), new Vector(21.0, -10.0), new Vector(17.0, -14.0), new Vector(-18.0, 3.0) };
		
		largeMeteorColliders.add(pointsInCollidersTwo);
		
		Vector[] pointsInCollidersThree = { new Vector(17.0, -14.0), new Vector(16.0, -19.0), new Vector(3.0, -22.0), new Vector(-14.0, -20.0),
				new Vector(-17.0, -15.0), new Vector(-18.0, 3.0) };
		
		largeMeteorColliders.add(pointsInCollidersThree);
		
		Vector[] pointsInCollidersFour = { new Vector(-17.0, -15.0), new Vector(-23.0, -12.0), new Vector(-23.0, -2.0), new Vector(-18.0, 3.0) };
		
		largeMeteorColliders.add(pointsInCollidersFour);
		
		Vector[] pointsInCollidersFive = { new Vector(-18.0, 3.0), new Vector(-22.0, 11.0), new Vector(-17.0, 20.0), new Vector(0.0, 22.0), new Vector(12.0, 18.0) };
		
		largeMeteorColliders.add(pointsInCollidersFive);
		
		initialise(largeMeteorImages, position, (Math.PI * 2.0 * constants.getRandomDouble()) - Math.PI, (constants.getRandomDouble() * 0.2) - 0.1,
				MeteorType.LARGE);
	}
}
