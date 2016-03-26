package polysprite;

import java.util.ArrayList;
import java.util.List;

import collision.Vector;
import core.Constants.SpriteType;

/**
 *
 * @author Michael Topsom
 */
public class LargeMeteor extends Sprite {
	private List<List<Vector>> largeMeteorImages;
	private List<List<Vector>> largeMeteorColliders;
	
	public LargeMeteor(Vector position) {
		super();
		
		largeMeteorImages = new ArrayList<>();
		
		List<Vector> largeMeteorImage = new ArrayList<>();
		
		largeMeteorImage.add(new Vector(0.0, 22.0));
		largeMeteorImage.add(new Vector(12.0, 18.0));
		largeMeteorImage.add(new Vector(18.0, 20.0));
		largeMeteorImage.add(new Vector(23.0, 15.0));
		largeMeteorImage.add(new Vector(22.0, 10.0));
		largeMeteorImage.add(new Vector(18.0, 5.0));
		largeMeteorImage.add(new Vector(23.0, -3.0));
		largeMeteorImage.add(new Vector(21.0, -10.0));
		largeMeteorImage.add(new Vector(17.0, -14.0));
		largeMeteorImage.add(new Vector(16.0, -19.0));
		largeMeteorImage.add(new Vector(3.0, -22.0));
		largeMeteorImage.add(new Vector(-14.0, -20.0));
		largeMeteorImage.add(new Vector(-17.0, -15.0));
		largeMeteorImage.add(new Vector(-23.0, -12.0));
		largeMeteorImage.add(new Vector(-23.0, -2.0));
		largeMeteorImage.add(new Vector(-18.0, 3.0));
		largeMeteorImage.add(new Vector(-22.0, 11.0));
		largeMeteorImage.add(new Vector(-17.0, 20.0));
		
		largeMeteorImages.add(largeMeteorImage);
		
		largeMeteorColliders = new ArrayList<>();
		
		List<Vector> largeMeteorCollider = new ArrayList<>();
		
		largeMeteorCollider.add(new Vector(12.0, 18.0));
		largeMeteorCollider.add(new Vector(18.0, 20.0));
		largeMeteorCollider.add(new Vector(23.0, 15.0));
		largeMeteorCollider.add(new Vector(22.0, 10.0));
		largeMeteorCollider.add(new Vector(18.0, 5.0));
		largeMeteorCollider.add(new Vector(-18.0, 3.0));
		
		largeMeteorColliders.add(largeMeteorCollider);
		
		largeMeteorCollider.add(new Vector(18.0, 5.0));
		largeMeteorCollider.add(new Vector(23.0, -3.0));
		largeMeteorCollider.add(new Vector(21.0, -10.0));
		largeMeteorCollider.add(new Vector(17.0, -14.0));
		largeMeteorCollider.add(new Vector(-18.0, 3.0));
		
		largeMeteorColliders.add(largeMeteorCollider);
		
		largeMeteorCollider.add(new Vector(17.0, -14.0));
		largeMeteorCollider.add(new Vector(16.0, -19.0));
		largeMeteorCollider.add(new Vector(3.0, -22.0));
		largeMeteorCollider.add(new Vector(-14.0, -20.0));
		largeMeteorCollider.add(new Vector(-17.0, -15.0));
		largeMeteorCollider.add(new Vector(-18.0, 3.0));
		
		largeMeteorColliders.add(largeMeteorCollider);
		
		largeMeteorCollider.add(new Vector(-17.0, -15.0));
		largeMeteorCollider.add(new Vector(-23.0, -12.0));
		largeMeteorCollider.add(new Vector(-23.0, -2.0));
		largeMeteorCollider.add(new Vector(-18.0, 3.0));
		
		largeMeteorColliders.add(largeMeteorCollider);
		
		largeMeteorCollider.add(new Vector(-18.0, 3.0));
		largeMeteorCollider.add(new Vector(-22.0, 11.0));
		largeMeteorCollider.add(new Vector(-17.0, 20.0));
		largeMeteorCollider.add(new Vector(0.0, 22.0));
		largeMeteorCollider.add(new Vector(12.0, 18.0));
		
		largeMeteorColliders.add(largeMeteorCollider);
		
		initialise(largeMeteorImages, position, (Math.PI * 2.0 * constants.getRandomDouble()) - Math.PI, (constants.getRandomDouble() * 0.2) - 0.1, SpriteType.LARGE);
	}
}
