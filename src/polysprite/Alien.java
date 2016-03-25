package polysprite;

import java.awt.Color;
import java.util.ArrayList;

import collision.Vector;
import core.Constants.MeteorType;

/**
 *
 * @author Michael Topsom
 */
public class Alien extends PolySprite {
	private ArrayList<Vector[]> alienImages;
	
	private ArrayList<Vector[]> alienColliders;
	
	private int alive = 400;
	
	public Alien(Vector position) {
		super();
		
		alienImages = new ArrayList<>();
		
		Vector[] pointsIn = { new Vector(6.0, 12.0), new Vector(12.0, 6.0), new Vector(12.0, -6.0), new Vector(6.0, -12.0), new Vector(-6.0, -12.0),
				new Vector(-12.0, -6.0), new Vector(-12.0, 6.0), new Vector(-6.0, 12.0) };
		
		alienImages.add(pointsIn);
		
		Vector[] pointsInTwo = { new Vector(2.5, 0.0), new Vector(-2.5, 0.0) };
		
		alienImages.add(pointsInTwo);
		
		Vector[] pointsInThree = { new Vector(0.0, 2.5), new Vector(0.0, -2.5) };
		
		alienImages.add(pointsInThree);
		
		Vector[] pointsInFour = { new Vector(3.0, 6.0), new Vector(6.0, 3.0), new Vector(6.0, -3.0), new Vector(3.0, -6.0), new Vector(-3.0, -6.0),
				new Vector(-6.0, -3.0), new Vector(-6.0, 3.0), new Vector(-3.0, 6.0) };
		
		alienImages.add(pointsInFour);
		
		alienColliders = new ArrayList<>();
		
		alienColliders.add(pointsIn);
		
		initialise(alienImages, position, (Math.PI * 2.0 * constants.getRandomDouble()) - Math.PI, (constants.getRandomDouble() * 0.2) - 0.1, MeteorType.ALIEN);
		
		colour = Color.GREEN;
	}
	
	@Override
	public void update(double rotation, double thrust) {
		super.update(rotation, thrust);
		
		alive--;
	}
	
	public boolean alive() {
		return alive < 0;
	}
}
