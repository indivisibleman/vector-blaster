package core;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 *
 * @author Michael Topsom
 */
public interface State {
	public void initialise();
	
	public void initialise(String arg);
	
	public boolean initialised();
	
	public boolean justLoaded();
	
	public void update();
	
	public void render(Graphics g);
	
	public void resume();
	
	public void keyPressed(KeyEvent e);
	
	public void keyReleased(KeyEvent e);
	
	public void cleanUp();
}
