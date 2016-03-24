package core;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import core.exception.FontLoadException;

/**
 *
 * @author Michael Topsom
 */
public class StateManager {
	private static StateManager instance;
	
	private ArrayList<State> states = new ArrayList<>();
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	private StateManager() {
	}
	
	public static StateManager getInstance() {
		if(instance == null) {
			instance = new StateManager();
		}
		
		return instance;
	}
	
	public void push(State state) {
		states.add(state);
		
		State temp = states.get(states.size() - 1);
		
		if(!temp.initialised()) {
			try {
				temp.initialise();
			} catch(FontLoadException fle) {
				log.log(Level.SEVERE, "Unable to initialise", fle);
				popAll();
			}
		}
	}
	
	public void changeState(State state) {
		if(!states.isEmpty()) {
			State temp = states.get(states.size() - 1);
			temp.cleanUp();
			
			states.remove(states.size() - 1);
		}
		
		states.add(state);
		
		State temp = states.get(states.size() - 1);
		
		if(!temp.initialised()) {
			try {
				temp.initialise();
			} catch(FontLoadException fle) {
				log.log(Level.SEVERE, "Unable to initialise", fle);
				popAll();
			}
		}
	}
	
	public void changeState(State state, String args) {
		if(!states.isEmpty()) {
			State temp = states.get(states.size() - 1);
			temp.cleanUp();
			
			states.remove(states.size() - 1);
		}
		
		states.add(state);
		
		State temp = states.get(states.size() - 1);
		
		if(!temp.initialised()) {
			try {
				temp.initialise(args);
			} catch(FontLoadException fle) {
				log.log(Level.SEVERE, "Unable to initialise", fle);
				popAll();
			}
		}
	}
	
	public void pop() {
		if(!states.isEmpty()) {
			State temp = states.get(states.size() - 1);
			temp.cleanUp();
			
			states.remove(states.size() - 1);
		}
		
		if(!states.isEmpty()) {
			State temp = states.get(states.size() - 1);
			temp.resume();
		}
	}
	
	public void popAll() {
		while(!states.isEmpty()) {
			State temp = states.get(states.size() - 1);
			temp.cleanUp();
			
			states.remove(states.size() - 1);
		}
	}
	
	State currentState() {
		if(!states.isEmpty()) {
			return states.get(states.size() - 1);
		} else {
			return null;
		}
	}
	
	public boolean isEmpty() {
		return states.isEmpty();
	}
}
