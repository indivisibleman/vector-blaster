package core;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import core.exception.FontLoadException;

/**
 *
 * @author Michael Topsom
 */
public interface State {
	public void initialise() throws FontLoadException ;
	public void initialise(String arg) throws FontLoadException;
	public boolean initialised();
	public boolean justLoaded();
	public void update();
	public void render(Graphics g);
	public void keyReleased(KeyEvent e);
	public void cleanUp();
	default void keyPressed(KeyEvent e) {};
	default void resume() {};
}
