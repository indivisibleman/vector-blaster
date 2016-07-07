package polysprite;

import java.util.ArrayList;
import java.util.List;

import collision.Vector;
import core.Constants.SpriteType;

/**
 *
 * @author Michael Topsom
 */
public class SmallMeteor extends Sprite {
	private List<List<Vector>> smallMeteorImages;
	private List<List<Vector>> smallMeteorColliders;

	public SmallMeteor(Vector position) {
		super();

		smallMeteorImages = new ArrayList<>();

		List<Vector> smallMeteorImage = new ArrayList<>();

		smallMeteorImage.add(new Vector(3.0, 8.0));
		smallMeteorImage.add(new Vector(4.0, 5.0));
		smallMeteorImage.add(new Vector(10.0, 3.0));
		smallMeteorImage.add(new Vector(6.0, -7.0));
		smallMeteorImage.add(new Vector(2.0, -6.0));
		smallMeteorImage.add(new Vector(-1.0, -9.0));
		smallMeteorImage.add(new Vector(-8.0, -7.0));
		smallMeteorImage.add(new Vector(-9.0, 2.0));
		smallMeteorImage.add(new Vector(-4.0, 9.0));

		smallMeteorImages.add(smallMeteorImage);

		smallMeteorColliders = new ArrayList<>();

		List<Vector> smallMeteorCollider = new ArrayList<>();

		smallMeteorCollider.add(new Vector(4.0, 5.0));
		smallMeteorCollider.add(new Vector(10.0, 3.0));
		smallMeteorCollider.add(new Vector(6.0, -7.0));
		smallMeteorCollider.add(new Vector(2.0, -6.0));

		smallMeteorColliders.add(smallMeteorCollider);

		smallMeteorCollider = new ArrayList<>();

		smallMeteorCollider.add(new Vector(3.0, 8.0));
		smallMeteorCollider.add(new Vector(4.0, 5.0));
		smallMeteorCollider.add(new Vector(2.0, -6.0));
		smallMeteorCollider.add(new Vector(-1.0, -9.0));
		smallMeteorCollider.add(new Vector(-8.0, -7.0));
		smallMeteorCollider.add(new Vector(-9.0, 2.0));
		smallMeteorCollider.add(new Vector(-4.0, 9.0));

		smallMeteorColliders.add(smallMeteorCollider);

		initialise(smallMeteorImages, position,
				(Math.PI * 2.0 * constants.getRandomDouble()) - Math.PI,
				(constants.getRandomDouble() * 0.2) - 0.1, SpriteType.SMALL);
	}
}
