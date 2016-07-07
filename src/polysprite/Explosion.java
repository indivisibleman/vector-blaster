package polysprite;

import java.awt.Graphics2D;
import java.util.ArrayList;

import collision.Vector;
import core.Constants;

/**
 *
 * @author Michael Topsom
 */
public class Explosion {
	private ArrayList<ExplosionPiece> pieces;

	public Explosion(Vector position) {
		pieces = new ArrayList<>();

		Constants constants = Constants.getInstance();

		int numPieces = 3 + constants.getRandomInt(8);

		for (int i = 0; i < numPieces; i++) {
			pieces.add(new ExplosionPiece(
					new Vector(position.getX(), position.getY())));
		}
	}

	public void render(Graphics2D g2d) {
		for (int i = 0; i < pieces.size(); i++) {
			pieces.get(i).render(g2d);
		}
	}

	public void update() {
		for (int i = 0; i < pieces.size(); i++) {
			pieces.get(i).update();

			if (pieces.get(i).kill()) {
				pieces.remove(i);
			}
		}
	}

	public boolean kill() {
		return pieces.isEmpty();
	}
}
