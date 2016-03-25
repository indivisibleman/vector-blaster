package polysprite;

import java.util.ArrayList;

import collision.Vector;
import core.Constants.MeteorType;

/**
 *
 * @author Michael Topsom
 */
public class SmallMeteor extends PolySprite {
	private ArrayList<Vector[]> smallMeteorImages;
	
	private ArrayList<Vector[]> smallMeteorColliders;
	
	public SmallMeteor(Vector position) {
		super();
		
		smallMeteorImages = new ArrayList<>();
		
		Vector[] pointsIn = { new Vector(3.0, 8.0), new Vector(4.0, 5.0), new Vector(10.0, 3.0), new Vector(6.0, -7.0), new Vector(2.0, -6.0), new Vector(-1.0, -9.0),
				new Vector(-8.0, -7.0), new Vector(-9.0, 2.0), new Vector(-4.0, 9.0) };
		
		smallMeteorImages.add(pointsIn);
		
		smallMeteorColliders = new ArrayList<>();
		
		Vector[] pointsInCollidersOne = { new Vector(4.0, 5.0), new Vector(10.0, 3.0), new Vector(6.0, -7.0), new Vector(2.0, -6.0) };
		
		smallMeteorColliders.add(pointsInCollidersOne);
		
		Vector[] pointsInCollidersTwo = { new Vector(3.0, 8.0), new Vector(4.0, 5.0), new Vector(2.0, -6.0), new Vector(-1.0, -9.0), new Vector(-8.0, -7.0),
				new Vector(-9.0, 2.0), new Vector(-4.0, 9.0) };
		
		smallMeteorColliders.add(pointsInCollidersTwo);
		
		initialise(smallMeteorImages, position, (Math.PI * 2.0 * constants.getRandomDouble()) - Math.PI, (constants.getRandomDouble() * 0.2) - 0.1,
				MeteorType.SMALL);
	}
	
	@Override
	public void setPosition(Vector position) {
		this.position = position;
	}
}
