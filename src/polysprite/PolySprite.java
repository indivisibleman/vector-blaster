package polysprite;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import collision.Vector;
import core.Constants;
import core.Constants.SpriteType;

/**
 *
 * @author Michael Topsom
 */
public class PolySprite {
	protected List<Vector[]> polyImages;
	
	protected GeneralPath poly;
	
	protected Color colour;
	private Vector pos;
	protected float lineThickness;
	
	private double colliderRadius;
	
	private List<Vector[]> polyCollidersOut;
	
	private List<Vector> positions;
	private Iterator<Vector> positionIterator;
	
	protected Vector position;
	private Vector speed;
	
	protected double orientation;
	
	protected double spin;
	private double spinRate;
	
	protected Vector[] modifiedShape;
	
	protected Constants constants;
	
	private SpriteType type;
	
	public PolySprite() {
		constants = Constants.getInstance();
		
		type = SpriteType.DEFAULT;
	}
	
	void initialise(List<Vector[]> polyImages, Vector position, double orientation, double spinRate, SpriteType type) {
		initialise(polyImages, position, orientation, spinRate);
		
		this.type = type;
	}
	
	public SpriteType getType() {
		return type;
	}
	
	public void setPosition(Vector position) {
		this.position = position;
	}
	
	void initialise(List<Vector[]> polyImages, Vector position, double orientation, double spinRate) {
		this.polyImages = polyImages;
		
		this.position = position;
		this.orientation = orientation;
		this.spinRate = spinRate;
		spin = 0.0;
		
		speed = new Vector(0.0, 0.0);
		
		positions = new ArrayList<>();
		polyCollidersOut = new ArrayList<>();
		
		lineThickness = 2.0f;
		colour = Color.WHITE;
		
		colliderRadius = Double.NEGATIVE_INFINITY;
		
		for(int i = 0; i < polyImages.size(); i++) {
			for(int j = 0; j < polyImages.get(i).length; j++) {
				if(colliderRadius < Math.sqrt((polyImages.get(i)[j].getX() * polyImages.get(i)[j].getX()) + (polyImages.get(i)[j].getY() * polyImages.get(i)[j].getY()))) {
					colliderRadius = Math.sqrt((polyImages.get(i)[j].getX() * polyImages.get(i)[j].getX()) + (polyImages.get(i)[j].getY() * polyImages.get(i)[j].getY()));
				}
			}
		}
		
		colliderRadius += lineThickness / 2.0;
	}
	
	public void render(Graphics2D g2d) {
		render(g2d, 1.0f);
	}
	
	public void render(Graphics2D g2d, float alpha) {
		positionIterator = positions.iterator();
		
		while(positionIterator.hasNext()) {
			pos = positionIterator.next();
			
			for(int i = 0; i < polyImages.size(); i++) {
				modifiedShape = new Vector[polyImages.get(i).length];
				
				for(int j = 0; j < polyImages.get(i).length; j++) {
					modifiedShape[j] = new Vector(polyImages.get(i)[j].getX() * Math.cos(orientation + spin) - polyImages.get(i)[j].getY() * Math.sin(orientation + spin),
							polyImages.get(i)[j].getY() * Math.cos(orientation + spin) + polyImages.get(i)[j].getX() * Math.sin(orientation + spin));
					modifiedShape[j].add(pos);
				}
				
				poly = new GeneralPath();
				
				poly.moveTo(modifiedShape[0].getX(), modifiedShape[0].getY());
				
				for(int j = 1; j < modifiedShape.length; j++) {
					poly.lineTo(modifiedShape[j].getX(), modifiedShape[j].getY());
				}
				
				poly.closePath();
				
				g2d.setColor(colour);
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
				g2d.setStroke(new BasicStroke(lineThickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
				g2d.draw(poly);
			}
		}
	}
	
	public void update(double rotation, double thrust) {
		orientation += rotation;
		
		if(orientation < 0.0) {
			orientation += Math.PI * 2;
		} else if(orientation > Math.PI * 2) {
			orientation -= Math.PI * 2;
		}
		
		spin += spinRate;
		
		if(Double.doubleToRawLongBits(thrust) == 0) {
			speed.multiply(0.99);
		} else {
			speed = new Vector(thrust * Math.cos(orientation), thrust * Math.sin(orientation));
		}
		
		position.add(speed);
		
		if(position.getX() < 0) {
			position.setX(position.getX() + constants.getWindowSize().width);
		} else if(position.getX() > constants.getWindowSize().width) {
			position.setX(position.getX() - constants.getWindowSize().width);
		}
		
		if(position.getY() < 0) {
			position.setY(position.getY() + constants.getWindowSize().height);
		} else if(position.getY() > constants.getWindowSize().height) {
			position.setY(position.getY() - constants.getWindowSize().height);
		}
		
		positions = this.getPositions();
	}
	
	public double getRadius() {
		return colliderRadius;
	}
	
	public Vector getPosition() {
		return position;
	}
	
	public List<Vector> getPositions() {
		positions.clear();
		
		positions.add(position);
		
		if(position.getX() - this.getRadius() < 0.0) {
			positions.add(new Vector(position.getX() + constants.getWindowSize().width, position.getY()));
			
			if(position.getY() - this.getRadius() < 0.0) {
				positions.add(new Vector(position.getX() + constants.getWindowSize().width, position.getY() + constants.getWindowSize().height));
			}
			
			if(position.getY() + this.getRadius() > constants.getWindowSize().height) {
				positions.add(new Vector(position.getX() + constants.getWindowSize().width, position.getY() - constants.getWindowSize().height));
			}
		}
		
		if(position.getX() + this.getRadius() > constants.getWindowSize().width) {
			positions.add(new Vector(position.getX() - constants.getWindowSize().width, position.getY()));
			
			if(position.getY() - this.getRadius() < 0.0) {
				positions.add(new Vector(position.getX() - constants.getWindowSize().width, position.getY() + constants.getWindowSize().height));
			}
			
			if(position.getY() + this.getRadius() > constants.getWindowSize().height) {
				positions.add(new Vector(position.getX() - constants.getWindowSize().width, position.getY() - constants.getWindowSize().height));
			}
		}
		
		if(position.getY() - this.getRadius() < 0.0) {
			positions.add(new Vector(position.getX(), position.getY() + constants.getWindowSize().height));
		}
		
		if(position.getY() + this.getRadius() > constants.getWindowSize().height) {
			positions.add(new Vector(position.getX(), position.getY() - constants.getWindowSize().height));
		}
		
		return positions;
	}
	
	public List<Vector[]> getColliders() {
		polyCollidersOut.clear();
		
		positionIterator = positions.iterator();
		
		while(positionIterator.hasNext()) {
			pos = positionIterator.next();
			
			for(int i = 0; i < polyImages.size(); i++) {
				modifiedShape = new Vector[polyImages.get(i).length];
				
				for(int j = 0; j < polyImages.get(i).length; j++) {
					modifiedShape[j] = new Vector(polyImages.get(i)[j].getX() * Math.cos(orientation + spin) - polyImages.get(i)[j].getY() * Math.sin(orientation + spin),
							polyImages.get(i)[j].getY() * Math.cos(orientation + spin) + polyImages.get(i)[j].getX() * Math.sin(orientation + spin));
					modifiedShape[j].add(pos);
				}
				
				polyCollidersOut.add(modifiedShape);
			}
		}
		
		return polyCollidersOut;
	}
}
