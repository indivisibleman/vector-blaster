package polysprite;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import collision.Vector;

/**
 *
 * @author Michael Topsom
 */
class ExplosionPiece extends Sprite {
	private List<List<Vector>> explosionPieceImages;

	private int alive = 50;

	ExplosionPiece(Vector position) {
		super();

		explosionPieceImages = new ArrayList<>();

		List<Vector> explosionPieceImage = new ArrayList<>();

		double size = 1 + constants.getRandomDouble() * 5;

		explosionPieceImage.add(new Vector(size, size));
		explosionPieceImage.add(new Vector(-size, -size));

		explosionPieceImages.add(explosionPieceImage);

		initialise(explosionPieceImages, position,
				(Math.PI * 2.0 * constants.getRandomDouble()) - Math.PI,
				(constants.getRandomDouble() * 0.2) - 0.1);
	}

	void update() {
		this.update(0.0, 2.0);

		alive--;
	}

	@Override
	public void render(Graphics2D g2d) {
		render(g2d, alive / 50.0f);
	}

	boolean kill() {
		return alive < 0;
	}
}
