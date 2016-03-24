package polysprite;

import java.awt.Graphics2D;
import java.util.ArrayList;

import collision.Vector2D;

/**
 *
 * @author Michael Topsom
 */
public class ExplosionPiece extends PolySprite {
	private ArrayList<Vector2D[]> explosionPieceImages;
	
	private int alive = 50;
	
	public ExplosionPiece(Vector2D position) {
		super();
		
		explosionPieceImages = new ArrayList<>();
		
		double size = 1 + constants.getRandomDouble() * 5;
		
		Vector2D[] pointsIn = { new Vector2D(size, size), new Vector2D(-size, -size) };
		
		explosionPieceImages.add(pointsIn);
		;
		
		initialise(explosionPieceImages, position, (Math.PI * 2.0 * constants.getRandomDouble()) - Math.PI, (constants.getRandomDouble() * 0.2) - 0.1, explosionPieceImages);
	}
	
	public void update() {
		this.update(0.0, 2.0);
		
		alive--;
	}
	
	@Override
	public void render(Graphics2D g2d) {
		render(g2d, alive / 50.0f);
	}
	
	public boolean kill() {
		return alive < 0;
	}
}
