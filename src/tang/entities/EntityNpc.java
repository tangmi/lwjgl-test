package tang.entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.input.Keyboard;

import tang.helper.Heading;
import tang.helper.OBJLoader;
import tang.helper.Vector3;

public class EntityNpc extends Entity {
	
	
	public EntityNpc(Vector3 pos) {
		super(pos);
		
		
	}

	@Override
	public void init() {
		try {
			this.model = OBJLoader.loadModel(new File("assets/objects/person.obj"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		super.update();
		
//		this.heading.addYaw(1.0f);
//		this.pos = this.pos.add(heading.getMovementVector(Heading.DIRECTION_FORWARD).scale(0.1f));
		
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)) heading.pitch++;
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) heading.pitch--;
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) heading.yaw++;
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) heading.yaw--;
		
	}

	public void draw() {		
		this.model.draw(this.pos, this.heading);
	}
}
