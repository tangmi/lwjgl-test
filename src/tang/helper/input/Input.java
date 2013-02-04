package tang.helper.input;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import tang.helper.Console;
import tang.helper.Updatable;
import tang.helper.game.Game;
import tang.helper.struct.Vector2;

/**
 * Input handler that abstracts keyboard and mouse input into actions that a client can check against, allowing easy rebinding of keys
 * @author michael
 *
 */
public class Input implements Updatable {
	//maybe this class should be a singleton?

	private static Map<Integer, String> bindings = new HashMap<Integer, String>();

	private static Map<String, Boolean> actions = new HashMap<String, Boolean>();
	
	//these could be sets? clear them before every update and check if the action exists when returning state
	private static Map<String, Boolean> pressed = new HashMap<String, Boolean>();
	private static Map<String, Boolean> released = new HashMap<String, Boolean>();

	private static int mouseDX;
	private static int mouseDY;
	private static Map<Integer, String> bindingsMouse = new HashMap<Integer, String>();
	private static Map<String, Boolean> actionsMouse = new HashMap<String, Boolean>();

	private static Map<String, Boolean> pressedMouse = new HashMap<String, Boolean>();
	private static Map<String, Boolean> releasedMouse = new HashMap<String, Boolean>();


	@Override
	/**
	 * Initialize the input handler. Doesn't necessarily need to be called, but I'm exceeding expectations
	 */
	public void init() {
		try {
			this.initMouse();
			this.initKeyboard();
		} catch (LWJGLException e) {
			Console.error("Could not initialize input!");
			e.printStackTrace();
			Game.exit();
		}
	}

	private void initMouse() throws LWJGLException {
		Mouse.create();
	}

	/**
	 * Allows the display to "grab" the mouse, useful for mouse-controlled camera views
	 */
	public static void grabMouse() {
		Mouse.setCursorPosition(Display.getWidth()/2, Display.getHeight()/2); //force the mouse to become "grabbable" (inside window)
		Mouse.setGrabbed(true);
	}
	
	/**
	 * Releases the mouse and places it at it's last location (probably the center of the screen)
	 */
	public static void releaseMouse() {
		Input.releaseMouse(Mouse.getX(), Mouse.getY());
	}
	
	/**
	 * Releases the mouse and places it at the specified location
	 * @param x
	 * @param y
	 */
	public static void releaseMouse(int x, int y) {
		Mouse.setCursorPosition(x, y);
		Mouse.setGrabbed(false);
	}
	
	/**
	 * Releases the mouse and places it at the specified location
	 * @param mousePos
	 */
	public static void releaseMouse(Vector2 mousePos) {
		Mouse.setCursorPosition((int) mousePos.getX(), (int) mousePos.getY());
		Mouse.setGrabbed(false);
	}
	
	//TODO method for grabbing mouse projection in 3d space? maybe?
	//TODO mouse method getters
	public static int getMouseDX() {
		return mouseDX;
	}
	
	public static int getMouseDY() {
		return mouseDY;
	}
	
	
	

	private void initKeyboard() throws LWJGLException {
		Keyboard.create();
	}

	/**
	 * Bind a key to an action, so you can check the state of the action, rather than the key
	 * @param key
	 * @param action
	 */
	public static void bind(int key, String action) {
		bindings.put(key, action);
	}

	/**
	 * Release the bind from the key
	 * @param key
	 */
	public static void unbind(int key) {
		bindings.remove(key);
	}

	/**
	 * Checks whether the key associated with an action is pressed in the last tick
	 * @param action
	 * @return pressed state of the key
	 */
	public static boolean pressed(String action) {
		return pressed.containsKey(action) && pressed.get(action);
	}
	
	/**
	 * Checks whether the key associated with an action is released in the last tick
	 * @param action
	 * @return pressed state of the key
	 */
	public static boolean released(String action) {
		return released.containsKey(action) && released.get(action);
	}

	/**
	 * Checks whether the key associated with an action is held down currently
	 * @param action
	 * @return state of the key
	 */
	public static boolean state(String action) {
		//this implementation sucks probably
		//TODO spit out an error if an action isn't bound yet
		return actions.containsKey(action) && actions.get(action);
	}

	@Override
	/**
	 * Updates the state of the input handler
	 */
	public void update() {
		
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
		
		//TODO get mouse delta methods

		//TODO add mouse events
		while(Mouse.next()) {
			int eventButton = Mouse.getEventButton();
			if(eventButton >= 0) {
				
			} else {
				mouseDX = Mouse.getEventDX();
				mouseDY = Mouse.getEventDY();
			}
		}
	}

	@Override
	/**
	 * Lol, you can't draw the input handler
	 */
	public final void draw() {}

}