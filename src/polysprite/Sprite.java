package polysprite;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import collision.Polygon;
import collision.Vector;
import core.Constants;
import core.Constants.SpriteType;

public class Sprite {
	protected List<List<Vector>> polyImages;
	
	protected Color colour;
	protected float lineThickness;
	
	private double colliderRadius;
	
	private List<Vector> offsetPositions;
	private Iterator<Vector> offsetPositionsIterator;
	
	protected Vector position;
	private Vector speed;
	
	protected double orientation;
	
	protected double spin;
	private double spinRate;
	
	protected Constants constants = Constants.getInstance();
	
	private SpriteType type;
	
	public Sprite() {
		type = SpriteType.DEFAULT;
	}
	
	void initialise(List<List<Vector>> polyImages, Vector position, double orientation, double spinRate, SpriteType type) {
		initialise(polyImages, position, orientation, spinRate);
		
		this.type = type;
	}
	
	void initialise(List<List<Vector>> polyImages, Vector position, double orientation, double spinRate) {
		this.polyImages = polyImages;
		
		this.position = position;
		this.orientation = orientation;
		this.spinRate = spinRate;
		spin = 0.0;
		
		speed = new Vector(0.0, 0.0);
		
		offsetPositions = new ArrayList<>();
		
		lineThickness = 2.0f;
		colour = Color.WHITE;
		
		colliderRadius = Double.NEGATIVE_INFINITY;
		
		for(List<Vector> polyImage : polyImages) {
			for(Vector point : polyImage) {
				double radius = Math.sqrt((point.getX() * point.getX()) + (point.getY() * point.getY()));
				
				if(colliderRadius < radius) {
					colliderRadius = radius;
				}
			}
		}
		
		colliderRadius += lineThickness / 2.0;
	}
	
	public SpriteType getType() {
		return type;
	}
	
	public void setPosition(Vector position) {
		this.position = position;
	}
	
	public void render(Graphics2D g2d) {
		render(g2d, 1.0f);
	}
	
	public void render(Graphics2D g2d, float alpha) {
		offsetPositionsIterator = offsetPositions.iterator();
		
		while(offsetPositionsIterator.hasNext()) {
			Vector pos = offsetPositionsIterator.next();
			
			for(List<Vector> polyImage : polyImages) {
				List<Vector> modifiedShape = new ArrayList<>();
				
				for(Vector point : polyImage) {
					Vector modifiedPoint = new Vector(point.getX() * Math.cos(orientation + spin) - point.getY() * Math.sin(orientation + spin),
							point.getY() * Math.cos(orientation + spin) + point.getX() * Math.sin(orientation + spin));
					modifiedPoint.add(pos);
					
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
		
		offsetPositions = this.getPositions();
	}
	
	public double getRadius() {
		return colliderRadius;
	}
	
	public Vector getPosition() {
		return position;
	}
	
	public List<Vector> getPositions() {
		offsetPositions.clear();
		
		offsetPositions.add(position);
		
		if(position.getX() - this.getRadius() < 0.0) {
			offsetPositions.add(new Vector(position.getX() + constants.getWindowSize().width, position.getY()));
			
			if(position.getY() - this.getRadius() < 0.0) {
				offsetPositions.add(new Vector(position.getX() + constants.getWindowSize().width, position.getY() + constants.getWindowSize().height));
			}
			
			if(position.getY() + this.getRadius() > constants.getWindowSize().height) {
				offsetPositions.add(new Vector(position.getX() + constants.getWindowSize().width, position.getY() - constants.getWindowSize().height));
			}
		}
		
		if(position.getX() + this.getRadius() > constants.getWindowSize().width) {
			offsetPositions.add(new Vector(position.getX() - constants.getWindowSize().width, position.getY()));
			
			if(position.getY() - this.getRadius() < 0.0) {
				offsetPositions.add(new Vector(position.getX() - constants.getWindowSize().width, position.getY() + constants.getWindowSize().height));
			}
			
			if(position.getY() + this.getRadius() > constants.getWindowSize().height) {
				offsetPositions.add(new Vector(position.getX() - constants.getWindowSize().width, position.getY() - constants.getWindowSize().height));
			}
		}
		
		if(position.getY() - this.getRadius() < 0.0) {
			offsetPositions.add(new Vector(position.getX(), position.getY() + constants.getWindowSize().height));
		}
		
		if(position.getY() + this.getRadius() > constants.getWindowSize().height) {
			offsetPositions.add(new Vector(position.getX(), position.getY() - constants.getWindowSize().height));
		}
		
		return offsetPositions;
	}
	
	public List<Polygon> getColliders() {
		List<Polygon> polyCollidersOut = new ArrayList<>();
		
		offsetPositionsIterator = offsetPositions.iterator();
		
		while(offsetPositionsIterator.hasNext()) {
			Vector pos = offsetPositionsIterator.next();
			
			for(List<Vector> polyImage : polyImages) {
				List<Vector> modifiedShape = new ArrayList<>(polyImage.size());
				
				for(Vector point : polyImage) {
					Vector modifiedPoint = new Vector(point.getX() * Math.cos(orientation + spin) - point.getY() * Math.sin(orientation + spin),
							point.getY() * Math.cos(orientation + spin) + point.getX() * Math.sin(orientation + spin));
					
					modifiedPoint.add(pos);
					
					modifiedShape.add(modifiedPoint);
				}
				
				polyCollidersOut.add(new Polygon(modifiedShape));
			}
		}
		
		return polyCollidersOut;
	}
	
	public boolean intersects(Sprite sprite) {
		for(Polygon polygon1 : this.getColliders()) {
			for(Polygon polygon2 : sprite.getColliders()) {
				if(polygon1.intersects(polygon2)) {
					return true;
				}
			}
		}
		
		return false;
	}
}
