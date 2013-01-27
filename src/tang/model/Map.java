package tang.model;

import java.util.List;

import tang.entities.Entity;
import tang.helper.Model;
import tang.main.Camera;

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
	
	
	public void draw() {
		for(Entity entity : entities) {
			entity.update();
		}

		camera.update(); //TODO: this has to go after player.update, i should find a better solution
		
		map.draw();
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}
	
	public void setCamera(Camera camera) {
		this.camera = camera;
	}
}
