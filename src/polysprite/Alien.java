package polysprite;

import java.awt.Color;
import java.util.ArrayList;

import collision.Vector2D;
import core.Constants.MeteorType;

/**
 *
 * @author Michael Topsom
 */
public class Alien extends PolySprite {
	private ArrayList<Vector2D[]> alienImages;
	
	private ArrayList<Vector2D[]> alienColliders;
	
	private int alive = 400;
	
	public Alien(Vector2D position) {
		super();
		
		alienImages = new ArrayList<>();
		
		Vector2D[] pointsIn = { new Vector2D(6.0, 12.0), new Vector2D(12.0, 6.0), new Vector2D(12.0, -6.0), new Vector2D(6.0, -12.0), new Vector2D(-6.0, -12.0),
				new Vector2D(-12.0, -6.0), new Vector2D(-12.0, 6.0), new Vector2D(-6.0, 12.0) };
		
		alienImages.add(pointsIn);
		
		Vector2D[] pointsInTwo = { new Vector2D(2.5, 0.0), new Vector2D(-2.5, 0.0) };
		
		alienImages.add(pointsInTwo);
		
		Vector2D[] pointsInThree = { new Vector2D(0.0, 2.5), new Vector2D(0.0, -2.5) };
		
		alienImages.add(pointsInThree);
		
		Vector2D[] pointsInFour = { new Vector2D(3.0, 6.0), new Vector2D(6.0, 3.0), new Vector2D(6.0, -3.0), new Vector2D(3.0, -6.0), new Vector2D(-3.0, -6.0),
				new Vector2D(-6.0, -3.0), new Vector2D(-6.0, 3.0), new Vector2D(-3.0, 6.0) };
		
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
