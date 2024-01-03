package gameEntities;

import linear2D.Vector;
import linear2D.VectorMath;

import java.awt.*;

public class EntityGeneric implements Entity {
    protected Vector position = new Vector(300,300);
    protected Vector currentVelocity = new Vector (0,0.2f);
    protected float collisionRadius = 20;
    protected float mass = 1;

    public EntityGeneric(Vector position, Vector currentVelocity, float collisionRadius, float mass){
        this.position = position;
        this.currentVelocity = currentVelocity;
        this.collisionRadius = collisionRadius;
        this.mass = mass;
    }

    @Override
    public void draw(Graphics2D g2) {
        int radius = (int) this.collisionRadius;
        g2.drawOval((int) position.getX() - radius, (int) position.getY() - radius, 2*radius, 2*radius);
    }

    @Override
    public void update() {
        position.addVector(currentVelocity);
    }

    public boolean checkCollision(EntityGeneric e){
        float distance = VectorMath.getVectorDifference(position, e.position).getLength();
        return distance <= (collisionRadius + e.collisionRadius);
    }

    public void onOutOfBounds(){

    }
    public float getCollisionRadius() {return collisionRadius;}
    public float getMass(){return mass;}

    public Vector getPosition(){return position;}
    public Vector getVelocity(){return this.currentVelocity;}


}
