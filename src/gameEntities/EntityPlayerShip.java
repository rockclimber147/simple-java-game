package gameEntities;

import input.MouseInputState;
import linear2D.Matrix;
import linear2D.Vector;
import linear2D.VectorMath;

import java.awt.*;

public class EntityPlayerShip extends EntityGeneric {
    private RegularPolygon body;
    private final MouseInputState input;
    private final Vector direction;
    private float maxRotationStep = 0.2f;
    private float thrust = .90f;
    private float terminalVelocity = 10.0f;
    private float dragCoefficient = 0.97f;
    private Matrix clockwiseRotation;
    private Matrix counterClockwiseRotation;

    public EntityPlayerShip(MouseInputState input){
        this.input = input;
        this.clockwiseRotation = VectorMath.getRotationMatrixFromAngle(maxRotationStep);
        this.counterClockwiseRotation = VectorMath.getRotationMatrixFromAngle(-maxRotationStep);
        this.body = new RegularPolygon(position,collisionRadius,5);
        this.direction = body.vertices[0];
    }
    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        g2.drawLine((int) position.getX(), (int) position.getY(), (int) (position.getX() + direction.getX()), (int) (position.getY() + direction.getY()));
        g2.drawPolygon(body.getDrawableVertices()[0], body.getDrawableVertices()[1], body.vertexCount);
    }

    @Override
    public void update() {
        rotate(input.mouseVector);
        if (input.rightMousePressed){
            changeVelocity();
        }
        applyDrag();
        if (currentVelocity.getLength() > terminalVelocity){
            currentVelocity.scaleTo(terminalVelocity);
        }
        position.addVector(currentVelocity);
    }

    private void rotate(Vector target){
        float targetAngle = VectorMath.getAngleBetweenTwoVectors(direction, VectorMath.getVectorDifference(target, position));
        if (Math.abs(targetAngle) >= maxRotationStep){
            if (targetAngle > 0){
                body.applyMatrix(counterClockwiseRotation);
            } else {
                body.applyMatrix(clockwiseRotation);
            }
        } else {
            body.applyMatrix(VectorMath.getRotationMatrixFromAngle(-targetAngle));
        }
    }
    private void changeVelocity(){
        currentVelocity.changeX(thrust * direction.getUnitX());
        currentVelocity.changeY(thrust * direction.getUnitY());
        currentVelocity.updateLength();
    }
    private void applyDrag(){
        currentVelocity.scaleBy(dragCoefficient);
    }

    public Vector getPosition(){return position;}
    public String toString(){
        return "POSITION:\n" + position.toString() + "\nVELOCITY:\n" + currentVelocity.toString();
    }
}
