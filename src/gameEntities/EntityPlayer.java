package gameEntities;

import input.MouseInputState;
import linear2D.Matrix;
import linear2D.Vector;
import linear2D.VectorMath;

import java.awt.*;

public class EntityPlayer extends EntityGeneric{
    private final MouseInputState input;
    private Vector position = new Vector(100,100);
    private Vector direction = new Vector(10,0);
    private Vector velocity = new Vector(1, 0);
    private float maxRotationStep = 0.1f;
    private float speed = 1.001f;
    private float dragCoefficient = 0.9f;
    private Matrix clockwiseRotation;
    private Matrix counterClockwiseRotation;

    public EntityPlayer(MouseInputState input){
        this.input = input;
        this.clockwiseRotation = VectorMath.getRotationMatrixFromAngle(maxRotationStep);
        this.counterClockwiseRotation = VectorMath.getRotationMatrixFromAngle(-maxRotationStep);
    }
    @Override
    public void draw(Graphics2D g2) {
        g2.drawLine((int) position.x, (int) position.y, (int) (position.x + direction.x), (int) (position.y + direction.y));
    }

    @Override
    public void update() {
        rotate();
        if (input.rightMousePressed){
            changeVelocity();
        }
        applyDrag();
        position.x += velocity.x;
        position.y += velocity.y;
    }

    private void rotate(){
        float playerMouseAngle = VectorMath.getAngleBetweenTwoVectors(direction, new Vector(input.mouseX - position.x, input.mouseY - position.y));
        if (Math.abs(playerMouseAngle) >= maxRotationStep){
            if (playerMouseAngle > 0){
                direction.applyMatrix(counterClockwiseRotation);
            } else {
                direction.applyMatrix(clockwiseRotation);
            }
        } else {
            direction.applyMatrix(VectorMath.getRotationMatrixFromAngle(playerMouseAngle));
        }
    }
    private void changeVelocity(){
        velocity.x += speed * direction.x;
        velocity.y += speed * direction.y;
        velocity.updateLength();
    }
    private void applyDrag(){
        velocity.scale(dragCoefficient);

    }
}
