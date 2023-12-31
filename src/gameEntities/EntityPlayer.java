package gameEntities;

import input.MouseInputState;
import linear2D.Matrix;
import linear2D.Vector;
import linear2D.VectorMath;

import java.awt.*;

public class EntityPlayer implements Entity {
    RegularPolygon body;
    private final MouseInputState input;
    private Vector position = new Vector(200,200);
    private Vector direction;
    private Vector velocity = new Vector(1, 0);
    private float maxRotationStep = 0.2f;
    private float thrust = .90f;
    private float terminalVelocity = 10.0f;
    private float dragCoefficient = 0.97f;
    private Matrix clockwiseRotation;
    private Matrix counterClockwiseRotation;

    public EntityPlayer(MouseInputState input){
        this.input = input;
        this.clockwiseRotation = VectorMath.getRotationMatrixFromAngle(maxRotationStep);
        this.counterClockwiseRotation = VectorMath.getRotationMatrixFromAngle(-maxRotationStep);
        this.body = new RegularPolygon(position,20,10);
        this.direction = body.vertices[0];
    }
    @Override
    public void draw(Graphics2D g2) {
        g2.drawLine((int) position.getX(), (int) position.getY(), (int) (position.getX() + direction.getX()), (int) (position.getY() + direction.getY()));
        g2.drawPolygon(body.getDrawableVertices()[0], body.getDrawableVertices()[1], body.vertexCount);
    }

    @Override
    public void update() {
        rotate();
        if (input.rightMousePressed){
            changeVelocity();
        }
        applyDrag();
        if (velocity.getLength() > terminalVelocity){
            velocity.scaleTo(terminalVelocity);
        }
        position.addVector(velocity);
    }

    private void rotate(){
        float playerMouseAngle = VectorMath.getAngleBetweenTwoVectors(direction, VectorMath.getVectorDifference(input.mouseVector, position));
        if (Math.abs(playerMouseAngle) >= maxRotationStep){
            if (playerMouseAngle > 0){
                body.applyMatrix(counterClockwiseRotation);
            } else {
                body.applyMatrix(clockwiseRotation);
            }
        } else {
            body.applyMatrix(VectorMath.getRotationMatrixFromAngle(-playerMouseAngle));
        }
    }
    private void changeVelocity(){
        velocity.changeX(thrust * direction.getUnitX());
        velocity.changeY(thrust * direction.getUnitY());
        velocity.updateLength();
    }
    private void applyDrag(){
        velocity.scaleBy(dragCoefficient);
    }

    public Vector getPosition(){return position;}
    public String toString(){
        return "POSITION:\n" + position.toString() + "\nVELOCITY:\n" + velocity.toString();
    }
}
