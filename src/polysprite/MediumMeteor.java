package polysprite;

import java.util.ArrayList;
import java.util.List;

import collision.Vector;
import core.Constants.SpriteType;

/**
 *
 * @author Michael Topsom
 */
public class MediumMeteor extends Sprite {
	private List<List<Vector>> mediumMeteorImages;
	private List<List<Vector>> mediumMeteorColliders;

	public MediumMeteor(Vector position) {
		super();

		mediumMeteorImages = new ArrayList<>();

		List<Vector> mediumMeteorImage = new ArrayList<>();

		mediumMeteorImage.add(new Vector(2.0, 10.0));
		mediumMeteorImage.add(new Vector(7.0, 10.0));
		mediumMeteorImage.add(new Vector(13.0, 7.0));
		mediumMeteorImage.add(new Vector(14.0, -1.0));
		mediumMeteorImage.add(new Vector(10.0, -5.0));
		mediumMeteorImage.add(new Vector(11.0, -12.0));
		mediumMeteorImage.add(new Vector(4.0, -14.0));
		mediumMeteorImage.add(new Vector(-2.0, -12.0));
		mediumMeteorImage.add(new Vector(-7.0, -14.0));
		mediumMeteorImage.add(new Vector(-13.0, -7.0));
		mediumMeteorImage.add(new Vector(-12.0, -2.0));
		mediumMeteorImage.add(new Vector(-14.0, 0.0));
		mediumMeteorImage.add(new Vector(-13.0, 9.0));
		mediumMeteorImage.add(new Vector(-6.0, 15.0));
		mediumMeteorImage.add(new Vector(-1.0, 13.0));

		mediumMeteorImages.add(mediumMeteorImage);

		mediumMeteorColliders = new ArrayList<>();

		List<Vector> mediumMeteorCollider = new ArrayList<>();

		mediumMeteorCollider.add(new Vector(2.0, 10.0));
		mediumMeteorCollider.add(new Vector(7.0, 10.0));
		mediumMeteorCollider.add(new Vector(13.0, 7.0));
		mediumMeteorCollider.add(new Vector(14.0, -1.0));
		mediumMeteorCollider.add(new Vector(10.0, -5.0));

		mediumMeteorColliders.add(mediumMeteorCollider);

		mediumMeteorCollider = new ArrayList<>();

		mediumMeteorCollider.add(new Vector(10.0, -5.0));
		mediumMeteorCollider.add(new Vector(11.0, -12.0));
		mediumMeteorCollider.add(new Vector(4.0, -14.0));
		mediumMeteorCollider.add(new Vector(-2.0, -12.0));

		mediumMeteorColliders.add(mediumMeteorCollider);

		mediumMeteorCollider = new ArrayList<>();

		mediumMeteorCollider.add(new Vector(-2.0, -12.0));
		mediumMeteorCollider.add(new Vector(-7.0, -14.0));
		mediumMeteorCollider.add(new Vector(-13.0, -7.0));
		mediumMeteorCollider.add(new Vector(-12.0, -2.0));

		mediumMeteorColliders.add(mediumMeteorCollider);

		mediumMeteorCollider = new ArrayList<>();

		mediumMeteorCollider.add(new Vector(2.0, 10.0));
		mediumMeteorCollider.add(new Vector(10.0, -5.0));
		mediumMeteorCollider.add(new Vector(-2.0, -12.0));
		mediumMeteorCollider.add(new Vector(-12.0, -2.0));
		mediumMeteorCollider.add(new Vector(-14.0, 0.0));
		mediumMeteorCollider.add(new Vector(-13.0, 9.0));
		mediumMeteorCollider.add(new Vector(-6.0, 15.0));
		mediumMeteorCollider.add(new Vector(-1.0, 13.0));

		mediumMeteorColliders.add(mediumMeteorCollider);

		initialise(mediumMeteorImages, position, (Math.PI * 2.0 * constants.getRandomDouble()) - Math.PI,
				(constants.getRandomDouble() * 0.2) - 0.1, SpriteType.MEDIUM);
	}
}
