package tang.entities;

import tang.helper.Axis;
import tang.helper.Heading;
import tang.helper.Model;
import tang.helper.Vector3;

public abstract class Entity {
	public Vector3 pos, vel, accel;
	public Vector3 posprev, velprev;
	public Vector3 size;
	public float friction;
	public Heading heading;
	public Model model;

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

		this.init();
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
		this.pos = this.pos.add(this.vel);
		this.vel = this.vel.add(this.accel.scale(0.5f));
		
		calculateFriction();
		
		this.posprev = pos;
		this.velprev = vel;

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
	
	public final void drawAxis() {
		Axis.draw(this.pos, this.heading);
	}

}
