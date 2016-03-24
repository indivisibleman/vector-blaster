package polysprite;

import java.util.ArrayList;

import collision.Vector2D;
import core.Constants.MeteorType;

/**
 *
 * @author Michael Topsom
 */
public class SmallMeteor extends PolySprite {
	private ArrayList<Vector2D[]> smallMeteorImages;
	
	private ArrayList<Vector2D[]> smallMeteorColliders;
	
	public SmallMeteor(Vector2D position) {
		super();
		
		smallMeteorImages = new ArrayList<>();
		
		Vector2D[] pointsIn = { new Vector2D(3.0, 8.0), new Vector2D(4.0, 5.0), new Vector2D(10.0, 3.0), new Vector2D(6.0, -7.0), new Vector2D(2.0, -6.0), new Vector2D(-1.0, -9.0),
				new Vector2D(-8.0, -7.0), new Vector2D(-9.0, 2.0), new Vector2D(-4.0, 9.0) };
		
		smallMeteorImages.add(pointsIn);
		
		smallMeteorColliders = new ArrayList<>();
		
		Vector2D[] pointsInCollidersOne = { new Vector2D(4.0, 5.0), new Vector2D(10.0, 3.0), new Vector2D(6.0, -7.0), new Vector2D(2.0, -6.0) };
		
		smallMeteorColliders.add(pointsInCollidersOne);
		
		Vector2D[] pointsInCollidersTwo = { new Vector2D(3.0, 8.0), new Vector2D(4.0, 5.0), new Vector2D(2.0, -6.0), new Vector2D(-1.0, -9.0), new Vector2D(-8.0, -7.0),
				new Vector2D(-9.0, 2.0), new Vector2D(-4.0, 9.0) };
		
		smallMeteorColliders.add(pointsInCollidersTwo);
		
		initialise(smallMeteorImages, position, (Math.PI * 2.0 * constants.getRandomDouble()) - Math.PI, (constants.getRandomDouble() * 0.2) - 0.1,
				MeteorType.SMALL);
	}
	
	@Override
	public void setPosition(Vector2D position) {
		this.position = position;
	}
}
