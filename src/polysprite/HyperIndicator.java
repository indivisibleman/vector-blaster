package polysprite;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

import collision.Vector;

/**
 *
 * @author Michael Topsom
 */
public class HyperIndicator extends PolySprite {
	private ArrayList<Vector[]> hyperIndicatorImages;
	
	public HyperIndicator(Vector position) {
		super();
		
		hyperIndicatorImages = new ArrayList<>();
		
		Vector[] pointsIn = new Vector[20];
		
		for(int i = 0; i < 20; i++) {
			pointsIn[i] = new Vector(i * Math.sin(i), i * Math.cos(i));
		}
		
		hyperIndicatorImages.add(pointsIn);
		
		initialise(hyperIndicatorImages, position, (Math.PI * 2.0 * constants.getRandomDouble()) - Math.PI, 0.05);
	}
	
	@Override
	public void render(Graphics2D g2d, float alpha) {
		for(int i = 0; i < polyImages.size(); i++) {
			modifiedShape = new Vector[polyImages.get(i).length];
			
			for(int j = 0; j < polyImages.get(i).length; j++) {
				modifiedShape[j] = new Vector(polyImages.get(i)[j].getX() * Math.cos(orientation + spin) - polyImages.get(i)[j].getY() * Math.sin(orientation + spin),
						polyImages.get(i)[j].getY() * Math.cos(orientation + spin) + polyImages.get(i)[j].getX() * Math.sin(orientation + spin));
				modifiedShape[j].add(position);
			}
			
			poly = new GeneralPath();
			
			poly.moveTo(modifiedShape[0].getX(), modifiedShape[0].getY());
			
			for(int j = 1; j < modifiedShape.length; j++) {
				poly.lineTo(modifiedShape[j].getX(), modifiedShape[j].getY());
			}
			
			g2d.setColor(colour);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
			g2d.setStroke(new BasicStroke(lineThickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g2d.draw(poly);
		}
	}
}
