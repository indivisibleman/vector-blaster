package states;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

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
class MenuState implements State {
	private boolean initialised = false;
	private boolean justLoaded;
	
	private Graphics2D g2d;
	private StateManager stateManager;
	
	private Utilities util;
	
	private Constants constants;
	
	private boolean keyExit = false;
	
	private boolean keyUp = false;
	private boolean keyDown = false;
	private boolean keyEnter = false;
	private boolean keyEnterWas = false;
	
	private boolean fadeIn = false;
	private boolean fadeOut = false;
	private float alpha = 1.0f;
	
	private ArrayList<PolySprite> meteors;
	
	private ArrayList<SmallMeteor> choiceMarkers;
	
	private Font font;
	private Font fontTitle;
	private Font fontText;
	
	private String[] menuOptions;
	private int choice;
	private int choiceCooldown;
	
	@Override
	public void initialise() {
		System.out.println("MenuState - initialising.");
		
		stateManager = StateManager.getInstance();
		
		util = Utilities.getInstance();
		
		constants = Constants.getInstance();
		
		font = util.getFont("BPneon.ttf");
		fontTitle = font.deriveFont(60.0f);
		fontText = font.deriveFont(42.0f);
		
		menuOptions = new String[5];
		menuOptions[0] = "Play";
		menuOptions[1] = "Help";
		menuOptions[2] = "Options";
		menuOptions[3] = "Credits";
		menuOptions[4] = "Exit";
		
		choice = 0;
		choiceCooldown = 0;
		
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
		
		choiceMarkers = new ArrayList<>();
		
		choiceMarkers.add(new SmallMeteor(new Vector2D(0.0, 0.0)));
		choiceMarkers.add(new SmallMeteor(new Vector2D(0.0, 0.0)));
		
		fadeIn = true;
		fadeOut = false;
		
		initialised = true;
		justLoaded = true;
	}
	
	@Override
	public void initialise(String arg) {
		System.out.println("MenuState - initialising with arguments.");
		
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
		
		if(keyEnterWas && !keyEnter && !fadeOut) {
			fadeIn = false;
			fadeOut = true;
		}
		
		if(fadeOut) {
			alpha += 1.0f / 90.0f;
			
			if(alpha > 1.0f) {
				alpha = 1.0f;
				
				switch(choice) {
					case 0:
						stateManager.changeState(new PlayState(), "level1");
						break;
					case 1:
						stateManager.changeState(new HelpState());
						break;
					case 2:
						stateManager.changeState(new OptionsState());
						break;
					case 3:
						stateManager.changeState(new CreditsState());
						break;
					case 4:
						stateManager.popAll();
						break;
					default:
						break;
				}
			}
		}
		
		if(choiceCooldown != 0) {
			choiceCooldown--;
		}
		
		for(int i = 0; i < meteors.size(); i++) {
			meteors.get(i).update(0.0, 2.0);
		}
		
		for(int i = 0; i < choiceMarkers.size(); i++) {
			choiceMarkers.get(i).update(0.0, 0.0);
		}
		
		if(!fadeOut && keyUp && choiceCooldown == 0) {
			choice--;
			
			if(choice < 0) {
				choice = 4;
			}
			
			choiceCooldown = 20;
		}
		
		if(!fadeOut && keyDown && choiceCooldown == 0) {
			choice++;
			
			if(choice > 4) {
				choice = 0;
			}
			
			choiceCooldown = 20;
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
		g2d.drawString("Vector Blaster", (constants.getWindowSize().width - g2d.getFontMetrics().stringWidth("Vector Blaster")) / 2.0f, 110.0f);
		
		g2d.setFont(fontText);
		for(int i = 0; i < menuOptions.length; i++) {
			g2d.drawString(menuOptions[i], (constants.getWindowSize().width - g2d.getFontMetrics().stringWidth(menuOptions[i])) / 2.0f, 215.0f + 50 * i);
		}
		
		choiceMarkers.get(0)
				.setPosition(new Vector2D((constants.getWindowSize().width - g2d.getFontMetrics().stringWidth(menuOptions[choice])) / 2.0f - 20.0f, 200.0f + 50 * choice));
		choiceMarkers.get(1)
				.setPosition(new Vector2D((constants.getWindowSize().width + g2d.getFontMetrics().stringWidth(menuOptions[choice])) / 2.0f + 20.0f, 200.0f + 50 * choice));
		
		for(int i = 0; i < choiceMarkers.size(); i++) {
			choiceMarkers.get(i).render(g2d);
		}
		
		g2d.setColor(Color.BLACK);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2d.fillRect(0, 0, constants.getWindowSize().width, constants.getWindowSize().height);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_UP:
				keyUp = true;
				break;
			case KeyEvent.VK_DOWN:
				keyDown = true;
				break;
			case KeyEvent.VK_ENTER:
				keyEnter = true;
				keyEnterWas = true;
				break;
			default:
				break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_ESCAPE:
				keyExit = true;
				break;
			case KeyEvent.VK_UP:
				keyUp = false;
				break;
			case KeyEvent.VK_DOWN:
				keyDown = false;
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
		System.out.println("MenuState - cleaning up.");
	}
}
