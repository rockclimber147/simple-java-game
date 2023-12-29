package linear2D;

public class VectorMath {
    private VectorMath(){}

    public static Matrix getRotationMatrixFromAngle(float angleInDegrees){
        double theta = Math.toRadians(angleInDegrees);
        float sinTheta = (float) Math.sin(theta);
        float cosTheta = (float) Math.cos(theta);

        return new Matrix(cosTheta, sinTheta, -sinTheta, cosTheta);
    }

    public static float getDotProduct(Vector v1, Vector v2){
        return v1.x*v2.x + v1.y+v2.y;
    }

    public static float getAngleBetweenTwoVectors(Vector v1, Vector v2){
        return (float) Math.acos(getDotProduct(v1, v2) / (v1.length * v2.length));
    }
}
