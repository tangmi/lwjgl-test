package tang.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;

import tang.entities.Entity;
import tang.entities.EntityNpc;
import tang.entities.EntityPlayer;
import tang.helper.obj.Model;
import tang.helper.obj.OBJLoader;
import tang.helper.struct.Heading;
import tang.helper.struct.Vector3;
import tang.main.Camera;
import tang.model.Map;

public class MapLoader {
	public static Map loadMap(File f) {
		Map m = null;
		
		//TODO: figure out a file format for containing maps
		
		List<Entity> mapEntities = new ArrayList<Entity>();
		
//		readEntities.add(new Player());
		
		
		//this map is hardcoded for now
		m = new Map();
		m.setName("testmap");
		
		
		Camera camera = new Camera();
		EntityPlayer player = new EntityPlayer(new Vector3(-8, 10, 0));
		player.setHeading(new Heading(90.0f, 0.0f));
		camera.setTarget(player);
		
		mapEntities.add(player);
		mapEntities.add(new EntityNpc(new Vector3(10, 0, 10)));
		Console.debug("Enemy added");
		
		m.setEntityList(mapEntities);
		m.setCamera(camera);
		
		Model mapModel = null;
		try {
			mapModel = OBJLoader.loadModel(new File("assets/levels/collisiontest.obj"));
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
		
		Console.info("Map \"" + m.getName() + "\" loaded");
		
		return m;
	}
}
