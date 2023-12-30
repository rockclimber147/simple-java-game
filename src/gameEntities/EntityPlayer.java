package gameEntities;

import input.MouseInputState;
import linear2D.Matrix;
import linear2D.Vector;
import linear2D.VectorMath;

import java.awt.*;

public class EntityPlayer implements EntityGeneric{
    private final MouseInputState input;
    private Vector position = new Vector(100,100);
    private Vector direction = new Vector(10,0);
    private Vector velocity = new Vector(1, 0);
    private float maxRotationStep = 0.1f;
    private float thrust = .50f;
    private float terminalVelocity = 20.0f;
    private float dragCoefficient = 0.99f;
    private Matrix clockwiseRotation;
    private Matrix counterClockwiseRotation;

    public EntityPlayer(MouseInputState input){
        this.input = input;
        this.clockwiseRotation = VectorMath.getRotationMatrixFromAngle(maxRotationStep);
        this.counterClockwiseRotation = VectorMath.getRotationMatrixFromAngle(-maxRotationStep);
    }
    @Override
    public void draw(Graphics2D g2) {
        g2.drawLine((int) position.getX(), (int) position.getY(), (int) (position.getX() + direction.getX()), (int) (position.getY() + direction.getY()));
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
        position.addWithVector(velocity);
    }

    private void rotate(){
        float playerMouseAngle = VectorMath.getAngleBetweenTwoVectors(direction, new Vector(input.mouseX - position.getX(), input.mouseY - position.getY()));
        if (Math.abs(playerMouseAngle) >= maxRotationStep){
            if (playerMouseAngle > 0){
                direction.applyMatrix(counterClockwiseRotation);
            } else {
                direction.applyMatrix(clockwiseRotation);
            }
        } else if(Math.abs(playerMouseAngle) >= 0.01){
            direction.applyMatrix(VectorMath.getRotationMatrixFromAngle(playerMouseAngle));
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
