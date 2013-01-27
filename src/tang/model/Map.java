package tang.model;

import static org.lwjgl.opengl.GL11.GL_LIGHT0;
import static org.lwjgl.opengl.GL11.GL_POSITION;
import static org.lwjgl.opengl.GL11.glLight;

import java.util.List;

import tang.entities.Entity;
import tang.helper.Model;
import tang.main.Camera;
import tang.main.Main;

public class Map {
	Model map;
	MapCollision mapCollision;
	List<Entity> entities;
	Camera camera;
	
	public Map() {
		map = null;
		mapCollision = new MapCollision();
	}
	
	public void setModel(Model m) {
		this.map = m;
	}
	
	public void setCollisionModel(Model cm) {
		this.mapCollision.setModel(cm);
	}
	
	public void update() {

		for(Entity entity : entities) {
			entity.update();
		}
		
		camera.update(); //TODO: this has to go after player.update, i should find a better solution

	}
	
	public void draw() {
		
		map.draw();
		
		for(Entity entity : entities) {
			entity.draw();
		}

	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}
	
	public void setCamera(Camera camera) {
		this.camera = camera;
	}
}
