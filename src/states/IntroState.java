package states;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import collision.Vector2D;
import core.Constants;
import core.State;
import core.StateManager;
import core.Utilities;
import polysprite.LargeMeteor;
import polysprite.MediumMeteor;
import polysprite.PolySprite;
import polysprite.SmallMeteor;

/**
 *
 * @author Michael Topsom
 */
public class IntroState implements State {
	private boolean initialised = false;
	private boolean justLoaded = false;
	
	private boolean keyChange = false;
	private boolean keyExit = false;
	
	private float alpha = 1.0f;
	
	private ArrayList<PolySprite> meteors;
	
	private boolean fadeIn = false;
	private boolean fadeOut = false;
	
	private Font font;
	private Font fontTitle;
	private Font fontText;
	
	private Graphics2D g2d;
	private StateManager stateManager;
	
	private Utilities util;
	
	private Constants constants;
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	@Override
	public void initialise() {
		log.log(Level.INFO, "IntroState - initialising.");
		
		stateManager = StateManager.getInstance();
		
		util = Utilities.getInstance();
		
		constants = Constants.getInstance();
		
		font = util.getFont("BPneon.ttf");
		fontTitle = font.deriveFont(60.0f);
		fontText = font.deriveFont(42.0f);
		
		meteors = new ArrayList<>();
		
		for(int i = 0; i < 9; i++) {
			meteors.add(
					new SmallMeteor(new Vector2D(constants.getWindowSize().width * constants.getRandomDouble(), constants.getWindowSize().height * constants.getRandomDouble())));
		}
		
		for(int i = 0; i < 6; i++) {
			meteors.add(
					new MediumMeteor(new Vector2D(constants.getWindowSize().width * constants.getRandomDouble(), constants.getWindowSize().height * constants.getRandomDouble())));
		}
		
		for(int i = 0; i < 3; i++) {
			meteors.add(
					new LargeMeteor(new Vector2D(constants.getWindowSize().width * constants.getRandomDouble(), constants.getWindowSize().height * constants.getRandomDouble())));
		}
		
		fadeIn = true;
		
		initialised = true;
		justLoaded = true;
	}
	
	@Override
	public void initialise(String arg) {
		log.log(Level.INFO, "IntroState - initialising with arguments.");
		
		initialise();
	}
	
	@Override
	public boolean initialised() {
		return initialised;
	}
	
	@Override
	public boolean justLoaded() {
		if(justLoaded) {
			justLoaded = false;
			return !justLoaded;
		} else {
			return justLoaded;
		}
		
	}
	
	@Override
	public void update() {
		if(keyExit) {
			stateManager.popAll();
		}
		
		if(fadeIn) {
			alpha -= 1.0f / 90.0f;
			
			if(alpha < 0.0f) {
				alpha = 0.0f;
				fadeIn = false;
			}
		}
		
		if(keyChange && !fadeIn) {
			fadeOut = true;
		}
		
		if(fadeOut) {
			alpha += 1.0f / 90.0f;
			
			if(alpha > 1.0f) {
				alpha = 1.0f;
				stateManager.changeState(new MenuState());
			}
		}
		
		for(int i = 0; i < meteors.size(); i++) {
			meteors.get(i).update(0.0, 2.0);
		}
	}
	
	@Override
	public void render(Graphics g) {
		g2d = (Graphics2D) g;
		
		g2d.setColor(Color.BLACK);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		g2d.fillRect(0, 0, constants.getWindowSize().width, constants.getWindowSize().height);
		
		g2d.setColor(Color.WHITE);
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		for(int i = 0; i < meteors.size(); i++) {
			meteors.get(i).render(g2d, 0.5f);
		}
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		
		g2d.setFont(fontTitle);
		g2d.drawString("Vector Blaster", (640 - g2d.getFontMetrics().stringWidth("Vector Blaster")) / 2.0f, 110.0f);
		
		g2d.setFont(fontText);
		g2d.drawString("Press any key", (640 - g2d.getFontMetrics().stringWidth("Press any Key")) / 2.0f, 270.0f);
		g2d.drawString("to start", (640 - g2d.getFontMetrics().stringWidth("to start")) / 2.0f, 310.0f);
		
		g2d.setColor(Color.BLACK);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2d.fillRect(0, 0, constants.getWindowSize().width, constants.getWindowSize().height);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			keyExit = true;
		} else {
			keyChange = true;
		}
	}
	
	@Override
	public void cleanUp() {
		log.log(Level.INFO, "IntroState - cleaning up.");
	}
}
