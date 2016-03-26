package polysprite;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;

import collision.Vector;

/**
 *
 * @author Michael Topsom
 */
public class HyperIndicator extends Sprite {
	private List<List<Vector>> hyperIndicatorImages;
	
	public HyperIndicator(Vector position) {
		super();
		
		hyperIndicatorImages = new ArrayList<>();
		
		List<Vector> hyperIndicatorImage = new ArrayList<>();
		
		for(int i = 0; i < 20; i++) {
			hyperIndicatorImage.add(new Vector(i * Math.sin(i), i * Math.cos(i)));
		}
		
		hyperIndicatorImages.add(hyperIndicatorImage);
		
		initialise(hyperIndicatorImages, position, (Math.PI * 2.0 * constants.getRandomDouble()) - Math.PI, 0.05);
	}
	
	@Override
	public void render(Graphics2D g2d, float alpha) {
		for(List<Vector> polyImage : polyImages) {
			List<Vector> modifiedShape = new ArrayList<>();
			
			for(Vector point : polyImage) {
				Vector modifiedPoint = new Vector(point.getX() * Math.cos(orientation + spin) - point.getY() * Math.sin(orientation + spin),
						point.getY() * Math.cos(orientation + spin) + point.getX() * Math.sin(orientation + spin));
				
				modifiedShape.add(modifiedPoint);
			}
			
			GeneralPath poly = new GeneralPath();
			
			poly.moveTo(modifiedShape.get(0).getX(), modifiedShape.get(0).getY());
			
			for(int i = 1; i < modifiedShape.size(); i++) {
				poly.lineTo(modifiedShape.get(i).getX(), modifiedShape.get(i).getY());
			}
			
			poly.closePath();
			
			g2d.setColor(colour);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
			g2d.setStroke(new BasicStroke(lineThickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g2d.draw(poly);
		}
	}
}
