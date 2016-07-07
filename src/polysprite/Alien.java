package polysprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import collision.Vector;
import core.Constants.SpriteType;

/**
 *
 * @author Michael Topsom
 */
public class Alien extends Sprite {
	private List<List<Vector>> alienImages;
	private List<List<Vector>> alienColliders;

	private int alive = 400;

	public Alien(Vector position) {
		super();

		alienImages = new ArrayList<>();

		List<Vector> alienImage = new ArrayList<>();

		alienImage.add(new Vector(6.0, 12.0));
		alienImage.add(new Vector(12.0, 6.0));
		alienImage.add(new Vector(12.0, -6.0));
		alienImage.add(new Vector(6.0, -12.0));
		alienImage.add(new Vector(-6.0, -12.0));
		alienImage.add(new Vector(-12.0, -6.0));
		alienImage.add(new Vector(-12.0, 6.0));
		alienImage.add(new Vector(-6.0, 12.0));

		alienImages.add(alienImage);

		alienImage = new ArrayList<>();

		alienImage.add(new Vector(2.5, 0.0));
		alienImage.add(new Vector(-2.5, 0.0));

		alienImages.add(alienImage);

		alienImage = new ArrayList<>();

		alienImage.add(new Vector(0.0, 2.5));
		alienImage.add(new Vector(0.0, -2.5));

		alienImages.add(alienImage);

		alienImage = new ArrayList<>();

		alienImage.add(new Vector(3.0, 6.0));
		alienImage.add(new Vector(6.0, 3.0));
		alienImage.add(new Vector(6.0, -3.0));
		alienImage.add(new Vector(3.0, -6.0));
		alienImage.add(new Vector(-3.0, -6.0));
		alienImage.add(new Vector(-6.0, -3.0));
		alienImage.add(new Vector(-6.0, 3.0));
		alienImage.add(new Vector(-3.0, 6.0));

		alienImages.add(alienImage);

		alienColliders = new ArrayList<>();

		List<Vector> alienCollider = new ArrayList<>();

		alienCollider.add(new Vector(6.0, 12.0));
		alienCollider.add(new Vector(12.0, 6.0));
		alienCollider.add(new Vector(12.0, -6.0));
		alienCollider.add(new Vector(6.0, -12.0));
		alienCollider.add(new Vector(-6.0, -12.0));
		alienCollider.add(new Vector(-12.0, -6.0));
		alienCollider.add(new Vector(-12.0, 6.0));
		alienCollider.add(new Vector(-6.0, 12.0));

		alienColliders.add(alienCollider);

		initialise(alienImages, position,
				(Math.PI * 2.0 * constants.getRandomDouble()) - Math.PI,
				(constants.getRandomDouble() * 0.2) - 0.1, SpriteType.ALIEN);

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
