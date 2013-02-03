package tang.helper.input;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import tang.helper.Console;
import tang.helper.Updatable;
import tang.main.Game;

public class Input implements Updatable {
	//maybe this class should be a singleton?

	private static Map<Integer, String> bindings;

	private static Map<String, Boolean> actions;
	private static Map<String, Boolean> pressed;
	private static Map<String, Boolean> released;


	@Override
	public void init() {
		try {
			this.initMouse();
			this.initKeyboard();
		} catch (LWJGLException e) {
			Console.error("Could not initialize input!");
			e.printStackTrace();
			//			Game.exit();
		}

		bindings = new HashMap<Integer, String>();
		
		actions = new HashMap<String, Boolean>();
		
		//these could be sets? clear them before every update and check if the action exists when returning state
		pressed = new HashMap<String, Boolean>();
		released = new HashMap<String, Boolean>();
	}

	private void initMouse() throws LWJGLException {
		Mouse.create();
	}

	private void initKeyboard() throws LWJGLException {
		Keyboard.create();
	}

	public static void bind(int key, String action) {
		bindings.put(key, action);
	}

	public static void unbind(int key) {
		bindings.remove(key);
	}

	public static boolean pressed(String action) {
		return pressed.containsKey(action) && pressed.get(action);
	}

	public static boolean released(String action) {
		return released.containsKey(action) && released.get(action);
	}

	public static boolean state(String action) {
		//this implementation sucks probably
		//TODO spit out an error if an action isn't bound yet
		return actions.containsKey(action) && actions.get(action);
	}

	//TODO method for grabbing mouse projection in 3d space? maybe?
	//TODO mouse method getters

	@Override
	public void update() {
		//TODO get mouse delta methods
		
		pressed.clear();
		released.clear();
		
		while(Keyboard.next()) {
			for(Map.Entry<Integer, String> binding : bindings.entrySet()) {
				int key = binding.getKey();
				String action = binding.getValue();
				if(Keyboard.getEventKey() == key) {
					if(Keyboard.getEventKeyState()) {
						pressed.put(action, true);
						actions.put(action, true);
					} else {
						actions.put(action, false);
						released.put(action, true);
					}
				}
			}
		}
		//TODO add mouse events
//		while(Mouse.next()) {
//			
//		}
	}

	@Override
	public final void draw() {}

}

class KeyState {
	public int key;
	public State state;
}

/**
 * Stores the state of the key associated with an action
 * @author michael
 *
 */
enum State {
	PRESSED, RELEASED, DOWN, UP
}