package core;

import java.util.Vector;

/**
 *
 * @author Michael Topsom
 */
public class StateManager {
	private static StateManager instance;
	
	private Vector<State> states = new Vector<>();
	
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
		
		State temp = states.lastElement();
		
		if(!temp.initialised()) {
			temp.initialise();
		}
	}
	
	public void push(State state, String args) {
		states.add(state);
		
		State temp = states.lastElement();
		
		if(!temp.initialised()) {
			temp.initialise(args);
		}
	}
	
	public void changeState(State state) {
		if(!states.isEmpty()) {
			State temp = states.elementAt(states.size() - 1);
			temp.cleanUp();
			
			states.removeElementAt(states.size() - 1);
		}
		
		states.add(state);
		
		State temp = states.lastElement();
		
		if(!temp.initialised()) {
			temp.initialise();
		}
	}
	
	public void changeState(State state, String args) {
		if(!states.isEmpty()) {
			State temp = states.elementAt(states.size() - 1);
			temp.cleanUp();
			
			states.removeElementAt(states.size() - 1);
		}
		
		states.add(state);
		
		State temp = states.lastElement();
		
		if(!temp.initialised()) {
			temp.initialise(args);
		}
	}
	
	public void pop() {
		if(!states.isEmpty()) {
			State temp = states.elementAt(states.size() - 1);
			temp.cleanUp();
			
			states.removeElementAt(states.size() - 1);
		}
		
		if(!states.isEmpty()) {
			State temp = states.lastElement();
			temp.resume();
		}
	}
	
	public void popAll() {
		while(!states.isEmpty()) {
			State temp = states.elementAt(states.size() - 1);
			temp.cleanUp();
			
			states.removeElementAt(states.size() - 1);
		}
	}
	
	public State currentState() {
		if(!states.isEmpty()) {
			return states.lastElement();
		} else {
			return null;
		}
	}
	
	public boolean isEmpty() {
		return states.isEmpty();
	}
}
