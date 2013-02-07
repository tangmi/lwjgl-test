package tang.helper.entities;

import java.util.List;

import tang.helper.Updatable;
import tang.helper.game.Game;
import tang.helper.obj.Model;
import tang.helper.struct.Heading;
import tang.helper.struct.Vector3;
import tang.helper.utils.Axis;
import tang.helper.utils.Console;
import tang.helper.world.CollisionMap;
import tang.helper.world.CollisionResult;
import tang.testgame.Main;

import static org.lwjgl.opengl.GL11.*;

public abstract class Entity implements Updatable {
	private static int nextId;
	private int id;
	public Vector3 pos, vel, accel;
	public Vector3 posprev, velprev;
	public Vector3 size;
	public float friction;
	public Heading heading;
	public Model model;
	
	public boolean standing; //for games with gravity; could be factored out
	public String name;

	public Entity(Vector3 pos) {
		this.id = nextId++; //sequentially assign ids
		
		this.pos = pos;
		this.vel = new Vector3();
		this.accel = new Vector3();
		this.heading = new Heading().withDefaults();
		this.friction = 0.0f;
		
		this.posprev = this.pos;
		this.velprev = this.vel;
		
		this.size = new Vector3();
		
		this.model = null;
		this.name = this.getClass().getSimpleName();
	}
	
	public Vector3 getPos() {
		return this.pos;
	}
	
	public void setPos(Vector3 pos) {
		this.pos = pos;
	}

	public Heading getHeading() {
		return this.heading;
	}
	
	public void setHeading(Heading h) {
		this.heading = h;
	}
	
	/**
	 * Update method that should be called by the world
	 */
	public void updateEntity() {
		//this basically forces this.calculatePosition() to be called (in World.java), and be un-preventable by the client
		this.calculatePosition();
		this.update();
	}

	public abstract void init();
	
	public abstract void update();
	
	public abstract void draw();

	private void calculatePosition() {
		this.posprev = pos;
		this.velprev = vel;
		
		this.vel = this.vel.add(this.accel);

//		this.pos = this.pos.add(this.vel);
		CollisionResult res = Game.getLoadedWorld().getCollisionMap().trace(this.pos, this.vel, this.size);
		this.handleMovementTrace(res);
		
		this.calculateFriction();
		
	}

	//Friction, like most things, is arbitrary
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
	}
	
	public void handleMovementTrace(CollisionResult res) {
		//resolve collisions based on position and velocity of both things
		
		this.pos = res.getResolution();
		
		if(res.getCollisionX()) {
			this.vel.x = 0.0f;
		}
		if(res.getCollisionY()) {
			this.vel.y = 0.0f;
		}
		if(res.getCollisionZ()) {
			this.vel.z = 0.0f;
		}
		
		//TODO add anything that needs to be done in 3d
		this.standing = res.getCollisionY();
		
	}

	public void resolveCollision(Entity other) {
		//TODO add 'weighs' on certain collision types (FIXED, ACTIVE, etc)
		if(this.overlapY(posprev, other) && this.overlapZ(posprev, other)) {
			//x-collision
			
			float resolution;
//			resolution = (other.getPos().getX() - (other.size.x/2)) - (this.getPos().getX());
			
			if(this.pos.x < other.pos.x) {
				resolution = other.pos.x - other.size.x/2 - this.size.x/2;
			} else if(this.pos.x > other.pos.x) {
				resolution = other.pos.x + other.size.x/2 + this.size.x/2;
			} else {
				resolution = this.pos.x;
			}
			
			Main.text = "x collision";

			
			CollisionResult res = new CollisionResult();
			res.setResolution(resolution, this.pos.y, this.pos.z);
			
			res.setCollision(this.pos.x != res.getResolution().x, false, false);
			this.handleMovementTrace(res);
			
		} else if(this.overlapX(posprev, other) && this.overlapZ(posprev, other)) {
			//y-collision
			
			this.vel.y = 0.4f;
			CollisionResult res = new CollisionResult();
			res.setResolution(this.pos.x, other.pos.y + other.size.y, this.pos.z);
			
			res.setCollision(false, false, false);
			this.handleMovementTrace(res);
			
			
		} else if(this.overlapX(posprev, other) && this.overlapY(posprev, other)) {
			//z-collision
			
			float resolution;
			
			if(this.pos.z < other.pos.z) {
				resolution = other.pos.z - other.size.z/2 - this.size.z/2;
			} else if(this.pos.z > other.pos.z) {
				resolution = other.pos.z + other.size.z/2 + this.size.z/2;
			} else {
				resolution = this.pos.z;
			}
			
			Main.text = "z collision";
			
			CollisionResult res = new CollisionResult();
			res.setResolution(this.pos.x, this.pos.y, resolution);
			
			res.setCollision(false, false, this.pos.x != res.getResolution().x);
			this.handleMovementTrace(res);
		}
		
	}
	
	public boolean touches(Entity other) {
		return this.touchesAt(this.pos, other);
	}
	
	//should these be private?
	protected boolean touchesAt(Vector3 pos, Entity b) {
		boolean notColliding = !this.overlapX(pos, b) || !this.overlapY(pos, b) || !this.overlapZ(pos, b);
		return !notColliding;
	}
	
	protected boolean overlapX(Vector3 pos, Entity other) {
		boolean notOverlapping =
				(pos.x + (size.x / 2) <= other.pos.getX() - (other.size.getX() / 2)) ||
				(pos.x - (size.x / 2) >= other.pos.getX() + (other.size.getX() / 2));	
		return !notOverlapping;
	}
	
	protected boolean overlapY(Vector3 pos, Entity other) {
		boolean notOverlapping =
				(pos.y 			>= other.pos.getY() + other.size.getY()) ||
				(pos.y + size.y <= other.pos.getY());
		return !notOverlapping;
	}
	
	protected boolean overlapZ(Vector3 pos, Entity other) {
		boolean notOverlapping =
				(pos.z + (size.z / 2) <= other.pos.getZ() - (other.size.getZ() / 2)) ||
				(pos.z - (size.z / 2) >= other.pos.getZ() + (other.size.getZ() / 2));
		return !notOverlapping;
	}
	
	
	
	public final void drawAxis() {
		Axis.draw(this.pos, this.heading);
	}
	
	public final void drawBoundingBox() {
		//TODO make this not have to worry about lighting
		
		glDisable(GL_LIGHTING);
		glDisable(GL_COLOR_MATERIAL);
		
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

		glEnable(GL_COLOR_MATERIAL);
		glEnable(GL_LIGHTING);
		
	}

	public String toString() {
		return "Entity(id: " + this.getId() + ", name: " + this.name + ")";
	}

	public int getId() {
		return id;
	}

}
