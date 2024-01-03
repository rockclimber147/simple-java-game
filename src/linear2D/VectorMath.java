package linear2D;

import gameEntities.EntityGeneric;

import java.util.Arrays;

public class VectorMath {
    private VectorMath(){}

    public static Matrix getRotationMatrixFromAngle(float theta){

        float sinTheta = (float) Math.sin(theta);
        float cosTheta = (float) Math.cos(theta);

        return new Matrix(cosTheta, sinTheta, -sinTheta, cosTheta);
    }

    public static float getDotProduct(Vector v1, Vector v2){
        return v1.x*v2.x + v1.y+v2.y;
    }
    public static Vector getVectorSum(Vector v1, Vector v2){
        return new Vector(v1.x + v2.x, v1.y + v2.y);
    }
    public static Vector getVectorDifference(Vector v1, Vector v2){
        return new Vector(v1.x - v2.x, v1.y - v2.y);
    }
    public static float getAngleBetweenTwoVectors(Vector a, Vector b){
        return (float) Math.atan2( a.x*b.y - a.y*b.x, a.x*b.x + a.y*b.y );
    }
    public static void handleCollision(EntityGeneric entity1, EntityGeneric entity2){
        transferMomentum2D(entity1.getMass(), entity1.getVelocity(), entity2.getMass(), entity2.getVelocity());
        displace(entity1, entity2);
    }
    private static void transferMomentum2D(float m1, Vector v1, float m2, Vector v2){
        float[] finalXVelocities = getFinalVelocities(m1, v1.x, m2, v2.x);
        float[] finalYVelocities = getFinalVelocities(m1, v1.y, m2, v2.y);
        System.out.println(Arrays.toString(finalXVelocities));
        v1.setX(finalXVelocities[0]);  v1.setY(finalYVelocities[0]);
        v2.setX(finalXVelocities[1]);  v2.setY(finalYVelocities[1]);
    }

    private static float[] getFinalVelocities(float m1, float v1Initial, float m2, float v2Initial){
        final float systemMomentum = v1Initial*m1 + v2Initial*m2;
        System.out.println("System momentum:" + systemMomentum);
        float v2Final = (systemMomentum - m1*(v2Initial - v1Initial)) / (m1 + m2);
        System.out.println("v2Final: " + v2Final);
        float v1Final = v2Initial + v2Final - v1Initial;
        return new float[] {v1Final, v2Final};
    }

    private static void displace(EntityGeneric entity1, EntityGeneric entity2){
        Vector entity1Position = entity1.getPosition();
        Vector entity2Position = entity2.getPosition();
        Vector positionDelta = getVectorDifference(entity1Position, entity2Position);
        positionDelta.scaleTo(entity1.getCollisionRadius() + entity2.getCollisionRadius() - positionDelta.length);
        positionDelta.x /= 4;
        positionDelta.y /= 4;
        entity1Position.addVector(positionDelta);
        entity2Position.subtractVector(positionDelta);
    }

    public static void main(String[] args) {
        transferMomentum2D(5, new Vector(6, 0), 2, new Vector(0,0));
    }
}
