package tang.main;

import tang.helper.obj.Updatable;
import tang.model.Map;

public abstract class Game implements Updatable {
	//TODO abstract getters and setters for display properties
	private static final String DISPLAY_TITLE = "tangmi/lwjgl-test";
	private static final int DISPLAY_WIDTH = 800;
	private static final int DISPLAY_HEIGHT = 600;

	public static Map loadedMap;

	
	
	public int getDisplayWidth() {
		return DISPLAY_WIDTH;
	}
	public int getDisplayHeight() {
		return DISPLAY_HEIGHT;
	}

	public String getDisplayTitle() {
		return DISPLAY_TITLE;
	}
	
	public static Map getLoadedMap() {
		return Game.loadedMap;
	}

	public static void setLoadedMap(Map loadedMap) {
		Game.loadedMap = loadedMap;
	}
	
	@Override
	public abstract void init();
	@Override
	public abstract void update();
	@Override
	public abstract void draw();

	
}
