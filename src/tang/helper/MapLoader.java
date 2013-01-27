package tang.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;

import tang.entities.Entity;
import tang.entities.Player;
import tang.main.Camera;
import tang.model.Map;

public class MapLoader {
	public static Map loadMap(File f) {
		Map m = null;
		
		//TODO: figure out a file format for containing maps
		
		List<Entity> readEntities = new ArrayList<Entity>();
		
//		readEntities.add(new Player());
		
		
		//this map is hardcoded for now
		m = new Map();
		
		
		Camera camera = new Camera();
		Player player = new Player(new Vector3(-11, 0, 1));
		camera.setTarget(player);
		
		readEntities.add(player);
		
		m.setEntities(readEntities);
		m.setCamera(camera);
		
		Model mapModel = null;
		try {
			mapModel = OBJLoader.loadModel(new File("assets/levels/test.obj"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		
		
		
		m.setModel(mapModel);
		m.setCollisionModel(mapModel);
		
		return m;
	}
}