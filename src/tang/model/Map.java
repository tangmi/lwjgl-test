package tang.model;

import static org.lwjgl.opengl.GL11.GL_LIGHT0;
import static org.lwjgl.opengl.GL11.GL_POSITION;
import static org.lwjgl.opengl.GL11.glLight;

import java.util.List;

import tang.entities.Entity;
import tang.helper.obj.Model;
import tang.helper.obj.Updatable;
import tang.main.Camera;
import tang.testgame.Main;

public class Map implements Updatable {
	private Model map;
	private MapCollision mapCollision;
	private List<Entity> entities;
	private Camera camera;
	private String name;
	
	public Map() {
		map = null;
		mapCollision = new MapCollision();
	}
	
	public void init() {
		
		//TODO loadEntities() into map
		
		for(Entity entity : entities) {
			entity.init();
		}
		
	}
	
	public void setModel(Model m) {
		this.map = m;
	}
	
	public void setCollisionModel(Model cm) {
		this.mapCollision.setModel(cm);
	}
	
	public MapCollision getCollisionMap() {
		return this.mapCollision;
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

	public void setEntityList(List<Entity> entities) {
		this.entities = entities;
	}

	public List<Entity> getEntityList() {
		return this.entities;
	}
	
	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
