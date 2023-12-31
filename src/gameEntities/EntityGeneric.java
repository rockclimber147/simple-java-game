package gameEntities;

import linear2D.Matrix;
import linear2D.Vector;

import java.awt.*;

public class EntityGeneric implements Entity{
    Vector position;
    Vector direction;
    Vector currentVelocity;
    RegularPolygon body;
    float thrusterAcceleration;
    float maxVelocity;
    float maxAngularVelocity;
    float dragCoefficient;
    Matrix rightRotation;
    Matrix leftRotation;

    public EntityGeneric(){

    }
    @Override
    public void draw(Graphics2D g2) {

    }

    @Override
    public void update() {

    }
}
