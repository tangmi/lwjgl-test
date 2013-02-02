package tang.entities;

import java.util.List;

import tang.helper.Axis;
import tang.helper.Console;
import tang.helper.obj.Model;
import tang.helper.obj.Updatable;
import tang.helper.struct.Heading;
import tang.helper.struct.Vector3;
import tang.main.Game;
import tang.model.CollisionResult;
import tang.model.MapCollision;

import static org.lwjgl.opengl.GL11.*;

public abstract class Entity implements Updatable {
	public Vector3 pos, vel, accel;
	public Vector3 posprev, velprev;
	public Vector3 size;
	public float friction;
	public Heading heading;
	public Model model;
	
	public boolean standing; //for games with gravity; could be factored out

	public Entity(Vector3 pos) {
		this.pos = pos;
		this.vel = new Vector3();
		this.accel = new Vector3();
		this.heading = new Heading();
		this.friction = 0.0f;
		
		this.posprev = this.pos;
		this.velprev = this.vel;
		
		this.size = new Vector3();
		
		this.model = null;

	}
	
	public Vector3 getPos() {
		return this.pos;
	}

	public Heading getHeading() {
		return this.heading;
	}
	
	public void setHeading(Heading h) {
		this.heading = h;
	}

	public abstract void init();


	public void update() {
		this.calculatePosition();
	}

	private void calculatePosition() {
		
		this.vel = this.vel.add(this.accel.scale(0.5f));
//		this.pos = this.pos.add(this.vel);
		this.handleMovementTrace();
		
		this.vel = this.vel.add(this.accel.scale(0.5f));
		
		calculateFriction();
		
		this.posprev = pos;
		this.velprev = vel;
		
		//testing collisions with entities
		for(Entity entity : Game.getLoadedMap().getEntityList()) {
			if(!entity.getClass().isAssignableFrom(this.getClass())) {
				if(this.touches(entity)) {
					Console.debug(entity.getClass().toString());
				}
			}
		}
	}

	private void calculateFriction() {
		if(this.accel.x == 0) {
			this.vel.x = this.vel.x - this.friction * (this.pos.x - this.posprev.x)/2;
		}
		if(this.accel.y == 0) {
			this.vel.y = this.vel.y - this.friction * (this.pos.y - this.posprev.y)/2;
		}
		if(this.accel.z == 0) {
			this.vel.z = this.vel.z - this.friction * (this.pos.z - this.posprev.z)/2;
		}
		//TODO this is slightly buggy
//		if(this.accel.x == 0) {
//			if(this.vel.x - this.friction > 0) {
//				this.vel.x = this.vel.x - this.friction;
//			} else if(this.vel.x + this.friction < 0) {
//				this.vel.x = this.vel.x + this.friction;
//			} else {
//				this.vel.x = 0;
//			}
//		}
//		if(this.accel.y == 0) {
//			if(this.vel.y - this.friction > 0) {
//				this.vel.y = this.vel.y - this.fric	tion;
//			} else if(this.vel.y + this.friction < 0) {
//				this.vel.y = this.vel.y + this.friction;
//			} else {
//				this.vel.y = 0;
//			}
//		}
//		if(this.accel.z == 0) {
//			if(this.vel.z - this.friction > 0) {
//				this.vel.z = this.vel.z - this.friction;
//			} else if(this.vel.z + this.friction < 0) {
//				this.vel.z = this.vel.z + this.friction;
//			} else {
//				this.vel.z = 0;
//			}
//		}
	}

	public abstract void draw();
	
	public void handleMovementTrace() {
		//resolve collisions based on position and velocity of both things
		
		CollisionResult res = Game.getLoadedMap().getCollisionMap().trace(this.pos, this.vel, this.size);
		this.pos = res.getResolution();
//		System.out.println( res.toString());
		this.standing = res.getCollisionY();
//		Console.debug(this.standing);
		if(res.getCollisionX()) {
			this.vel.x = 0.0f;
		}
		if(res.getCollisionY()) {
			this.vel.y = 0.0f;
		}
		if(res.getCollisionZ()) {
			this.vel.z = 0.0f;
		}
		
		
	}
	
	public boolean touches(Entity other) {
		return touches(this, other);
	}
	
	private boolean touches(Entity a, Entity b) {
		boolean notColliding = 
				(a.pos.x + (a.size.x / 2) < b.pos.getX() - (b.size.getX() / 2)) ||
				(a.pos.y + a.size.y < b.pos.getY()) ||
				(a.pos.z + (a.size.z / 2) < b.pos.getZ() - (b.size.getZ() / 2)) ||
				
				(a.pos.x - (a.size.x / 2) > b.pos.getX() + (b.size.getX() / 2)) ||
				(a.pos.y > b.pos.getY() + b.size.getY()) ||
				(a.pos.z - (a.size.z / 2) > b.pos.getZ() + (b.size.getZ() / 2))
				;
		return !notColliding;
	}
	
	public final void drawAxis() {
		Axis.draw(this.pos, this.heading);
	}
	
	public final void drawBoundingBox() {
		//TODO make this not have to worry about lighting
		
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		glLineWidth(1.0f);

		glBegin(GL_QUADS);
			glColor3f(1f, 1f, 1f);
			
			//-z
			glVertex3f(this.pos.x - (this.size.x / 2), this.pos.y + this.size.y, this.pos.z - (this.size.z / 2));
			glVertex3f(this.pos.x + (this.size.x / 2), this.pos.y + this.size.y, this.pos.z - (this.size.z / 2));
			glVertex3f(this.pos.x + (this.size.x / 2), this.pos.y, this.pos.z - (this.size.z / 2));
			glVertex3f(this.pos.x - (this.size.x / 2), this.pos.y, this.pos.z - (this.size.z / 2));
			
			//+z
			glVertex3f(this.pos.x - (this.size.x / 2), this.pos.y, this.pos.z + (this.size.z / 2));			
			glVertex3f(this.pos.x + (this.size.x / 2), this.pos.y, this.pos.z + (this.size.z / 2));
			glVertex3f(this.pos.x + (this.size.x / 2), this.pos.y + this.size.y, this.pos.z + (this.size.z / 2));
			glVertex3f(this.pos.x - (this.size.x / 2), this.pos.y + this.size.y, this.pos.z + (this.size.z / 2));
			
			//-x
			glVertex3f(this.pos.x - (this.size.x / 2), this.pos.y, this.pos.z - (this.size.z / 2));			
			glVertex3f(this.pos.x - (this.size.x / 2), this.pos.y, this.pos.z + (this.size.z / 2));			
			glVertex3f(this.pos.x - (this.size.x / 2), this.pos.y + this.size.y, this.pos.z + (this.size.z / 2));			
			glVertex3f(this.pos.x - (this.size.x / 2), this.pos.y + this.size.y, this.pos.z - (this.size.z / 2));			
			
			//+x
			glVertex3f(this.pos.x + (this.size.x / 2), this.pos.y + this.size.y, this.pos.z - (this.size.z / 2));			
			glVertex3f(this.pos.x + (this.size.x / 2), this.pos.y + this.size.y, this.pos.z + (this.size.z / 2));			
			glVertex3f(this.pos.x + (this.size.x / 2), this.pos.y, this.pos.z + (this.size.z / 2));			
			glVertex3f(this.pos.x + (this.size.x / 2), this.pos.y, this.pos.z - (this.size.z / 2));			
			
			//+ and - y are automagically set
			
		glEnd();
		
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
		
	}

}
