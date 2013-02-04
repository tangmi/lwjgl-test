package tang.helper.world;


import java.util.ArrayList;
import java.util.List;

import tang.helper.Updatable;
import tang.helper.entities.Entity;
import tang.helper.obj.Model;
import tang.testgame.Main;

/**
 * Contains information about the currently loaded world in the game
 * @author michael
 *
 */
public class World implements Updatable {
	private Model map;
	private CollisionMap mapCollision;
	private List<Entity> entities;
	private Camera camera;
	private String name;
	
	public World() {
		map = null;
		mapCollision = new CollisionMap();
	}
	
	/**
	 * Automatically called when a world is loaded
	 */
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
	
	public CollisionMap getCollisionMap() {
		return this.mapCollision;
	}
	
	/**
	 * Automatically called in game loop, if world exists
	 */
	public void update() {

		for(Entity entity : entities) {
			entity.update();
			this.checkEntityCollision(entity);
		}
		
		//this has to go after entityCameraIsFollowing.update();
		camera.update();

	}
	
	private void checkEntityCollision(Entity entity) {
		for(Entity other : entities) {
			entity.resolveCollision(other);
			//TODO entity collision resolution
			//mark collision as checked already?
		}
	}

	/**
	 * Automatically called in game loop, if world exists
	 */
	public void draw() {
		
		map.draw();
		
		for(Entity entity : entities) {
			entity.draw();
		}

	}

	public void setEntityList(List<Entity> entities) {
		this.entities = entities;
	}

	public List<Entity> getEntities() {
		return this.entities;
	}
	
	public List<Entity> getEntitiesByClass(Class<Entity> c) {
		List<Entity> classEntities = new ArrayList<Entity>();
		for(Entity entity : entities) {
			if(entity.getClass().isAssignableFrom(c)) {
				classEntities.add(entity);
			}
		}
		return classEntities;
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
