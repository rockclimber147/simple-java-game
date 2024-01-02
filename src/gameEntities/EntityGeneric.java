package gameEntities;

import linear2D.Matrix;
import linear2D.Vector;

import java.awt.*;

public class EntityGeneric implements Entity{
    protected Vector position = new Vector(300,300);
    protected Vector currentVelocity = new Vector (0,1);
    protected float collisionRadius = 20;
    protected float mass = 1;

    @Override
    public void draw(Graphics2D g2) {
        int radius = (int) this.collisionRadius;
        g2.drawOval((int) position.getX() - radius, (int) position.getY() - radius, 2*radius, 2*radius);
    }

    @Override
    public void update() {

    }
}
