package states;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import collision.Polygon;
import collision.Vector;
import core.Constants;
import core.Constants.SpriteType;
import core.State;
import core.StateManager;
import core.Utilities;
import core.exception.FontLoadException;
import polysprite.Alien;
import polysprite.Ballistic;
import polysprite.Explosion;
import polysprite.HyperIndicator;
import polysprite.LargeMeteor;
import polysprite.MediumMeteor;
import polysprite.Ship;
import polysprite.SmallMeteor;
import polysprite.Sprite;

/**
 *
 * @author Michael Topsom
 */
class PlayState implements State {
	private boolean initialised = false;
	private boolean justLoaded;

	private Graphics2D g2d;
	private StateManager stateManager;

	private Utilities util;

	private Constants constants;

	private boolean keyExit = false;

	private boolean keyUp = false;
	private boolean keyDown = false;
	private boolean keyLeft = false;
	private boolean keyRight = false;

	private boolean keyFire = false;
	private int fireCount = 0;

	private boolean keyPause = false;
	private boolean keyEnter = false;

	private int hyperSpaceCount = 0;

	private boolean fadeIn = false;
	private float alpha = 1.0f;

	private Font font;
	private Font fontText;

	private int score = 0;

	private Ship ship;

	private double shipRotation = 0.0;
	private double shipThrust = 0.0;

	private ArrayList<Ballistic> shots;

	private ArrayList<Sprite> meteorites;

	private ArrayList<Explosion> explosions;

	private Polygon shipPoly;
	private Polygon meteorPoly;

	private int level;
	private int ships;

	private HyperIndicator hyperIndicator;

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Override
	public void initialise() throws FontLoadException {
		log.log(Level.INFO, "PlayState - initialising.");

		stateManager = StateManager.getInstance();

		util = Utilities.getInstance();

		constants = Constants.getInstance();

		font = util.getFont("BPneon.ttf");
		fontText = font.deriveFont(42.0f);

		fadeIn = true;

		initialised = true;
		justLoaded = true;

		ship = new Ship(new Vector(constants.getWindowSize().width / 2.0,
				constants.getWindowSize().height / 2.0));

		shots = new ArrayList<>();

		meteorites = new ArrayList<>();

		explosions = new ArrayList<>();

		level = 1;
		ships = 4;

		hyperIndicator = new HyperIndicator(new Vector(100, 435));

		setMeteors();
	}

	@Override
	public void initialise(String arg) throws FontLoadException {
		log.log(Level.INFO, "PlayState - initialising with arguments.");

		initialise();
	}

	@Override
	public boolean initialised() {
		return initialised;
	}

	@Override
	public boolean justLoaded() {
		if (justLoaded) {
			justLoaded = false;
			return !justLoaded;
		} else {
			return justLoaded;
		}

	}

	@Override
	public void update() {
		if (keyExit) {
			stateManager.popAll();
		}

		if (keyPause) {
			stateManager.push(new PauseState());
		}

		if (fadeIn) {
			alpha -= 1.0f / 90.0f;

			if (alpha < 0.0f) {
				alpha = 0.0f;
				fadeIn = false;
			}
		}

		if (keyLeft && !keyRight) {
			shipRotation = -0.1;
		} else if (!keyLeft && keyRight) {
			shipRotation = 0.1;
		} else {
			shipRotation = 0.0;
		}

		if (keyUp && !keyDown) {
			shipThrust = constants.getThrustSpeed();
		} else if (!keyUp && keyDown) {
			shipThrust = -constants.getThrustSpeed();
		} else {
			shipThrust = 0.0;
		}

		fireCount--;

		if (keyFire && fireCount < 0) {
			shots.add(new Ballistic(ship.getFirePosition(),
					ship.getOrientation()));
			fireCount = 20;
		}

		for (int i = 0; i < shots.size(); i++) {
			shots.get(i).update();

			if (shots.get(i).kill()) {
				shots.remove(i);
			}
		}

		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).update();

			if (explosions.get(i).kill()) {
				explosions.remove(i);
			}
		}

		hyperSpaceCount--;

		if (keyEnter && hyperSpaceCount < 0) {
			hyperSpace();

			hyperSpaceCount = 100;
		}

		hyperIndicator.update(0.0, 0.0);

		ship.update(shipRotation, shipThrust);

		for (int i = 0; i < meteorites.size(); i++) {
			meteorites.get(i).update(0.0, 1.2);
		}

		shipPoly = ship.getColliders().get(0);

		for (int i = 0; i < meteorites.size(); i++) {
			if (meteorites.get(i).getType().equals(SpriteType.ALIEN)) {
				Alien tmp = (Alien) meteorites.get(i);
				if (!tmp.alive()) {
					meteorites.remove(i);
				}
			}
		}

		for (int i = 0; i < meteorites.size(); i++) {
			for (int j = 0; j < meteorites.get(i).getColliders().size(); j++) {
				meteorPoly = meteorites.get(i).getColliders().get(j);

				if (shipPoly.intersects(meteorPoly)) {
					ships--;

					if (ships < 0) {
						stateManager.changeState(new GameOverState(),
								Integer.toString(score));
					}

					reset();
					break;
				}
			}
		}

		List<Sprite> newMeteorites = new ArrayList<>();

		shotLoop: for (Iterator<Ballistic> shotsIterator = shots
				.iterator(); shotsIterator.hasNext();) {
			Ballistic shot = shotsIterator.next();

			for (Iterator<Sprite> meteoritesIterator = meteorites
					.iterator(); meteoritesIterator.hasNext();) {
				Sprite meteorite = meteoritesIterator.next();

				if (shot.intersects(meteorite)) {
					newMeteorites.addAll(
							hit(meteorite.getType(), meteorite.getPosition()));

					explosions.add(new Explosion(
							new Vector(meteorite.getPosition().getX(),
									meteorite.getPosition().getY())));

					shotsIterator.remove();
					meteoritesIterator.remove();

					continue shotLoop;
				}
			}
		}

		if (!newMeteorites.isEmpty()) {
			meteorites.addAll(newMeteorites);

			newMeteorites.clear();
		}

		if (meteorites.isEmpty()) {
			level++;
			if (level % 4 == 0) {
				ships++;
			}
			setMeteors();

			if (constants.getRandomInt(4) == 1) {
				double radian = (Math.PI * 2.0 * constants.getRandomDouble())
						- Math.PI;

				meteorites.add(new Alien(new Vector(
						ship.getPosition().getX() + (200 * Math.sin(radian)),
						ship.getPosition().getY() + (200 * Math.cos(radian)))));
			}
		}
	}

	private List<Sprite> hit(SpriteType spriteType, Vector position) {
		List<Sprite> newMeteorites = new ArrayList<>();

		switch (spriteType) {
		case ALIEN:
			score += 50;
			break;
		case LARGE:
			newMeteorites.add(new MediumMeteor(
					new Vector(position.getX(), position.getY())));
			newMeteorites.add(new MediumMeteor(
					new Vector(position.getX(), position.getY())));
			score += 15;
			break;
		case MEDIUM:
			newMeteorites.add(new SmallMeteor(
					new Vector(position.getX(), position.getY())));
			newMeteorites.add(new SmallMeteor(
					new Vector(position.getX(), position.getY())));
			score += 20;
			break;
		default:
			score += 30;
			break;
		}

		return newMeteorites;
	}

	@Override
	public void render(Graphics g) {
		g2d = (Graphics2D) g;

		g2d.setColor(Color.BLACK);
		g2d.setComposite(
				AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2d.fillRect(0, 0, constants.getWindowSize().width,
				constants.getWindowSize().height);

		g2d.setColor(Color.WHITE);

		g2d.setComposite(AlphaComposite.Src);

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setFont(fontText);
		g2d.drawString("Score: " + score, 80, 60);

		g2d.setFont(fontText);
		g2d.drawString("Ships: " + ships,
				640 - g2d.getFontMetrics().stringWidth("Ships: " + ships) - 80,
				60);

		g2d.setFont(fontText);
		g2d.drawString("Level: " + level,
				(640 - g2d.getFontMetrics().stringWidth("Level: " + level)) / 2,
				445);

		if (hyperSpaceCount < 0) {
			hyperIndicator.render(g2d);
		} else {
			hyperIndicator.render(g2d, 1.0f - (hyperSpaceCount / 100.0f));
		}

		ship.render(g2d);

		for (int i = 0; i < shots.size(); i++) {
			shots.get(i).render(g2d);
		}

		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).render(g2d);
		}

		for (int i = 0; i < meteorites.size(); i++) {
			meteorites.get(i).render(g2d);
		}
	}

	private void reset() {
		shots.clear();

		ship = new Ship(new Vector(constants.getWindowSize().width / 2.0,
				constants.getWindowSize().height / 2.0));

		double radian;

		for (int i = 0; i < meteorites.size(); i++) {
			radian = Math.PI * 2 * constants.getRandomDouble();
			meteorites.get(i).setPosition(new Vector(
					ship.getPosition().getX() + (200 * Math.sin(radian)),
					ship.getPosition().getY() + (200 * Math.cos(radian))));
		}
	}

	private void hyperSpace() {
		ship = new Ship(new Vector(
				constants.getWindowSize().width * constants.getRandomDouble(),
				constants.getWindowSize().height
						* constants.getRandomDouble()));

		double radian;

		for (int i = 0; i < meteorites.size(); i++) {
			radian = Math.PI * 2 * constants.getRandomDouble();
			meteorites.get(i).setPosition(new Vector(
					ship.getPosition().getX() + (200 * Math.sin(radian)),
					ship.getPosition().getY() + (200 * Math.cos(radian))));
		}
	}

	private void setMeteors() {
		double radian;

		for (int i = 0; i < level + 4; i++) {
			radian = Math.PI * 2 * constants.getRandomDouble();

			meteorites.add(new LargeMeteor(new Vector(
					ship.getPosition().getX() + (200 * Math.sin(radian)),
					ship.getPosition().getY() + (200 * Math.cos(radian)))));
		}
	}

	@Override
	public void resume() {
		keyPause = false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			keyUp = true;
			break;
		case KeyEvent.VK_DOWN:
			keyDown = true;
			break;
		case KeyEvent.VK_LEFT:
			keyLeft = true;
			break;
		case KeyEvent.VK_RIGHT:
			keyRight = true;
			break;
		case KeyEvent.VK_SPACE:
			keyFire = true;
			break;
		case KeyEvent.VK_P:
			keyPause = true;
			break;
		case KeyEvent.VK_ENTER:
			keyEnter = true;
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			keyExit = true;
			break;
		case KeyEvent.VK_UP:
			keyUp = false;
			break;
		case KeyEvent.VK_DOWN:
			keyDown = false;
			break;
		case KeyEvent.VK_LEFT:
			keyLeft = false;
			break;
		case KeyEvent.VK_RIGHT:
			keyRight = false;
			break;
		case KeyEvent.VK_SPACE:
			keyFire = false;
			break;
		case KeyEvent.VK_P:
			keyPause = false;
			break;
		case KeyEvent.VK_ENTER:
			keyEnter = false;
			break;
		default:
			break;
		}
	}

	@Override
	public void cleanUp() {
		log.log(Level.INFO, "PlayState - cleaning up.");
	}
}
